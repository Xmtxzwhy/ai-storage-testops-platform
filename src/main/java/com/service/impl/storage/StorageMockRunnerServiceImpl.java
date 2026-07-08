package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dto.storage.CreateStorageTaskRequest;
import com.entity.storage.StorageSampleEntity;
import com.entity.storage.StorageTestCaseEntity;
import com.entity.storage.StorageTestNodeEntity;
import com.entity.storage.StorageTestResultEntity;
import com.entity.storage.StorageTestTaskEntity;
import com.service.storage.StorageMockRunnerService;
import com.service.storage.StorageSampleService;
import com.service.storage.StorageTestCaseService;
import com.service.storage.StorageTestNodeService;
import com.service.storage.StorageTestResultService;
import com.service.storage.StorageTestTaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("storageMockRunnerService")
public class StorageMockRunnerServiceImpl implements StorageMockRunnerService {
    @Autowired
    private StorageTestTaskService storageTestTaskService;
    @Autowired
    private StorageTestNodeService storageTestNodeService;
    @Autowired
    private StorageTestCaseService storageTestCaseService;
    @Autowired
    private StorageTestResultService storageTestResultService;
    @Autowired
    private StorageSampleService storageSampleService;

    @Override
    public StorageTestTaskEntity createTask(CreateStorageTaskRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("任务请求不能为空");
        }
        if (request.getSampleId() == null) {
            throw new IllegalArgumentException("样品不能为空");
        }
        if (request.getNodeId() == null) {
            throw new IllegalArgumentException("节点不能为空");
        }
        if (request.getTestSuites() == null || request.getTestSuites().isEmpty()) {
            throw new IllegalArgumentException("测试套件不能为空");
        }
        if (request.getScenes() == null || request.getScenes().isEmpty()) {
            throw new IllegalArgumentException("测试场景不能为空");
        }

        StorageSampleEntity sample = storageSampleService.selectById(request.getSampleId());
        if (sample == null) {
            throw new IllegalArgumentException("样品不存在");
        }

        StorageTestTaskEntity task = new StorageTestTaskEntity();
        task.setTaskName(StringUtils.defaultIfBlank(request.getTaskName(),
                sample.getProjectName() + " " + sample.getFwVersion() + " storage test"));
        task.setRawUserInput(request.getRawUserInput());
        task.setProjectName(StringUtils.defaultIfBlank(request.getProjectName(), sample.getProjectName()));
        task.setTargetVersion(StringUtils.defaultIfBlank(request.getTargetVersion(), sample.getFwVersion()));
        task.setSampleId(request.getSampleId());
        task.setNodeId(request.getNodeId());
        task.setTestSuites(joinValues(request.getTestSuites()));
        task.setScenes(joinValues(request.getScenes()));
        task.setTaskStatus("DRAFT");
        task.setErrorMessage(null);
        storageTestTaskService.insert(task);
        return task;
    }

    @Override
    public StorageTestTaskEntity runTask(Long taskId) {
        StorageTestTaskEntity task = storageTestTaskService.selectById(taskId);
        if (task == null) {
            throw new IllegalArgumentException("任务不存在");
        }

        try {
            StorageTestNodeEntity node = storageTestNodeService.selectById(task.getNodeId());
            validateNode(node);

            task.setTaskStatus("RUNNING");
            task.setStartedTime(new Date());
            task.setFinishedTime(null);
            task.setErrorMessage(null);
            storageTestTaskService.updateById(task);

            List<String> suites = splitValues(task.getTestSuites());
            List<String> scenes = splitValues(task.getScenes());
            List<StorageTestCaseEntity> cases = storageTestCaseService.selectList(
                    new EntityWrapper<StorageTestCaseEntity>()
                            .eq("enabled", 1)
                            .in("suite", suites)
                            .in("scene", scenes)
            );
            if (cases == null || cases.isEmpty()) {
                throw new IllegalArgumentException("未找到匹配的测试用例");
            }

            storageTestResultService.delete(new EntityWrapper<StorageTestResultEntity>().eq("task_id", taskId));

            List<StorageTestResultEntity> results = new ArrayList<StorageTestResultEntity>();
            Date executedTime = new Date();
            for (StorageTestCaseEntity testCase : cases) {
                BigDecimal round1 = mockValue(testCase.getSuite(), testCase.getScene(), testCase.getMetricName(), 1);
                BigDecimal round2 = mockValue(testCase.getSuite(), testCase.getScene(), testCase.getMetricName(), 2);
                BigDecimal round3 = mockValue(testCase.getSuite(), testCase.getScene(), testCase.getMetricName(), 3);

                StorageTestResultEntity result = new StorageTestResultEntity();
                result.setSourceType("MOCK_RUNNER");
                result.setTaskId(task.getId());
                result.setSampleId(task.getSampleId());
                result.setNodeId(task.getNodeId());
                result.setSuite(testCase.getSuite());
                result.setScene(testCase.getScene());
                result.setMetricName(testCase.getMetricName());
                result.setRound1Value(round1);
                result.setRound2Value(round2);
                result.setRound3Value(round3);
                result.setAverageValue(round1.add(round2).add(round3)
                        .divide(new BigDecimal("3"), 3, BigDecimal.ROUND_HALF_UP));
                result.setUnit(StringUtils.defaultIfBlank(testCase.getUnit(), "MB/s"));
                result.setResultStatus("PASS");
                result.setLogSummary("mock runner: " + StringUtils.defaultString(testCase.getCommandTemplate()));
                result.setErrorReason(null);
                result.setExecutedTime(executedTime);
                results.add(result);
            }

            storageTestResultService.insertBatch(results);

            task.setTaskStatus("COMPLETED");
            task.setFinishedTime(new Date());
            task.setErrorMessage(null);
            storageTestTaskService.updateById(task);
            return task;
        } catch (RuntimeException ex) {
            task.setTaskStatus("FAILED");
            task.setFinishedTime(new Date());
            task.setErrorMessage(ex.getMessage());
            storageTestTaskService.updateById(task);
            throw ex;
        }
    }

    @Override
    public List<StorageTestResultEntity> getTaskResults(Long taskId) {
        return storageTestResultService.selectList(
                new EntityWrapper<StorageTestResultEntity>()
                        .eq("task_id", taskId)
                        .orderBy("id", true)
        );
    }

    private void validateNode(StorageTestNodeEntity node) {
        if (node == null) {
            throw new IllegalArgumentException("节点不存在");
        }
        if (!"IDLE".equals(node.getNodeStatus())) {
            throw new IllegalArgumentException("节点不可用，要求 nodeStatus=IDLE，当前为 " + node.getNodeStatus());
        }
        if (!"CONNECTED".equals(node.getPhoneStatus())) {
            throw new IllegalArgumentException("节点不可用，要求 phoneStatus=CONNECTED，当前为 " + node.getPhoneStatus());
        }
        if (!"DEVICE".equals(node.getAdbState())) {
            throw new IllegalArgumentException("节点不可用，要求 adbState=DEVICE，当前为 " + node.getAdbState());
        }
    }

    private BigDecimal mockValue(String suite, String scene, String metricName, int round) {
        int seed = Math.abs((suite + scene + metricName).hashCode());
        BigDecimal base = new BigDecimal(50 + seed % 250);
        if ("CDM".equals(suite) && metricName != null && metricName.contains("SEQ R")) {
            base = new BigDecimal("330");
        }
        if ("dirty".equals(scene)) {
            base = base.multiply(new BigDecimal("0.88"));
        }
        return base.add(new BigDecimal(round * 2)).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    private String joinValues(List<String> values) {
        List<String> normalized = new ArrayList<String>();
        for (String value : values) {
            if (StringUtils.isNotBlank(value)) {
                normalized.add(value.trim());
            }
        }
        return StringUtils.join(normalized, ",");
    }

    private List<String> splitValues(String values) {
        if (StringUtils.isBlank(values)) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<String>();
        for (String value : Arrays.asList(values.split(","))) {
            if (StringUtils.isNotBlank(value)) {
                result.add(value.trim());
            }
        }
        return result;
    }
}
