package com.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.controller.storage.StorageTestTaskController;
import com.dto.storage.CreateStorageTaskRequest;
import com.entity.storage.StorageSampleEntity;
import com.entity.storage.StorageTestCaseEntity;
import com.entity.storage.StorageTestNodeEntity;
import com.entity.storage.StorageTestResultEntity;
import com.entity.storage.StorageTestTaskEntity;
import com.service.impl.storage.StorageMockRunnerServiceImpl;
import com.service.storage.StorageMockRunnerService;
import com.service.storage.StorageSampleService;
import com.service.storage.StorageTestCaseService;
import com.service.storage.StorageTestNodeService;
import com.service.storage.StorageTestResultService;
import com.service.storage.StorageTestTaskService;
import com.utils.R;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StorageMockRunnerServiceTest {

    @Test
    public void createAndRunTaskGeneratesDeterministicResults() {
        StorageTestTaskService taskService = mock(StorageTestTaskService.class);
        StorageTestNodeService nodeService = mock(StorageTestNodeService.class);
        StorageTestCaseService caseService = mock(StorageTestCaseService.class);
        StorageTestResultService resultService = mock(StorageTestResultService.class);
        StorageSampleService sampleService = mock(StorageSampleService.class);

        StorageSampleEntity sample = new StorageSampleEntity();
        sample.setId(2L);
        sample.setProjectName("Project-A");
        sample.setFwVersion("FW-v2");
        when(sampleService.selectById(2L)).thenReturn(sample);

        doAnswer(invocation -> {
            StorageTestTaskEntity entity = invocation.getArgument(0);
            if (entity.getId() == null) {
                entity.setId(100L);
            }
            return true;
        }).when(taskService).insert(any(StorageTestTaskEntity.class));
        when(taskService.updateById(any(StorageTestTaskEntity.class))).thenReturn(true);

        StorageTestTaskEntity storedTask = new StorageTestTaskEntity();
        storedTask.setId(100L);
        storedTask.setTaskName("Project-A task");
        storedTask.setSampleId(2L);
        storedTask.setNodeId(3L);
        storedTask.setProjectName("Project-A");
        storedTask.setTargetVersion("FW-v2");
        storedTask.setTestSuites("CDM,FIO");
        storedTask.setScenes("clean,dirty");
        storedTask.setTaskStatus("DRAFT");
        when(taskService.selectById(100L)).thenReturn(storedTask);

        StorageTestNodeEntity node = new StorageTestNodeEntity();
        node.setId(3L);
        node.setNodeStatus("IDLE");
        node.setPhoneStatus("CONNECTED");
        node.setAdbState("DEVICE");
        when(nodeService.selectById(3L)).thenReturn(node);

        StorageTestCaseEntity case1 = new StorageTestCaseEntity();
        case1.setId(1L);
        case1.setSuite("CDM");
        case1.setScene("clean");
        case1.setMetricName("SEQ R 1M Q8T1");
        case1.setUnit("MB/s");
        case1.setEnabled(1);

        StorageTestCaseEntity case2 = new StorageTestCaseEntity();
        case2.setId(2L);
        case2.setSuite("FIO");
        case2.setScene("dirty");
        case2.setMetricName("rand_write_4k");
        case2.setUnit("MB/s");
        case2.setEnabled(1);

        when(caseService.selectList(any(EntityWrapper.class))).thenReturn(Arrays.asList(case1, case2));
        when(resultService.insertBatch(anyList())).thenReturn(true);

        StorageMockRunnerServiceImpl service = new StorageMockRunnerServiceImpl();
        ReflectionTestUtils.setField(service, "storageTestTaskService", taskService);
        ReflectionTestUtils.setField(service, "storageTestNodeService", nodeService);
        ReflectionTestUtils.setField(service, "storageTestCaseService", caseService);
        ReflectionTestUtils.setField(service, "storageTestResultService", resultService);
        ReflectionTestUtils.setField(service, "storageSampleService", sampleService);

        CreateStorageTaskRequest request = new CreateStorageTaskRequest();
        request.setTaskName("Project-A task");
        request.setSampleId(2L);
        request.setNodeId(3L);
        request.setTestSuites(Arrays.asList("CDM", "FIO"));
        request.setScenes(Arrays.asList("clean", "dirty"));

        StorageTestTaskEntity created = service.createTask(request);
        assertEquals(100L, created.getId());
        assertEquals("DRAFT", created.getTaskStatus());
        assertEquals("CDM,FIO", created.getTestSuites());
        assertEquals("clean,dirty", created.getScenes());
        assertEquals("Project-A", created.getProjectName());
        assertEquals("FW-v2", created.getTargetVersion());

        StorageTestTaskEntity completed = service.runTask(100L);
        assertEquals("COMPLETED", completed.getTaskStatus());
        assertTrue(completed.getStartedTime() instanceof Date);
        assertTrue(completed.getFinishedTime() instanceof Date);

        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<StorageTestResultEntity>> resultCaptor = ArgumentCaptor.forClass(List.class);
        verify(resultService).insertBatch(resultCaptor.capture());
        List<StorageTestResultEntity> savedResults = resultCaptor.getValue();
        assertEquals(2, savedResults.size());
        assertEquals(new BigDecimal("332.000"), savedResults.get(0).getRound1Value());
        assertEquals(new BigDecimal("334.000"), savedResults.get(0).getAverageValue());
        assertEquals(new BigDecimal("141.920"), savedResults.get(1).getRound1Value());
        assertEquals(new BigDecimal("143.920"), savedResults.get(1).getAverageValue());
        verify(taskService, times(2)).updateById(any(StorageTestTaskEntity.class));
    }

    @Test
    public void runTaskRejectsUnavailableNode() {
        StorageTestTaskService taskService = mock(StorageTestTaskService.class);
        StorageTestNodeService nodeService = mock(StorageTestNodeService.class);
        StorageTestCaseService caseService = mock(StorageTestCaseService.class);
        StorageTestResultService resultService = mock(StorageTestResultService.class);
        StorageSampleService sampleService = mock(StorageSampleService.class);

        StorageTestTaskEntity task = new StorageTestTaskEntity();
        task.setId(101L);
        task.setSampleId(2L);
        task.setNodeId(9L);
        task.setTestSuites("CDM");
        task.setScenes("clean");
        task.setTaskStatus("DRAFT");
        when(taskService.selectById(101L)).thenReturn(task);
        when(taskService.updateById(any(StorageTestTaskEntity.class))).thenReturn(true);

        StorageTestNodeEntity node = new StorageTestNodeEntity();
        node.setId(9L);
        node.setNodeStatus("BUSY");
        node.setPhoneStatus("CONNECTED");
        node.setAdbState("DEVICE");
        when(nodeService.selectById(9L)).thenReturn(node);

        StorageMockRunnerServiceImpl service = new StorageMockRunnerServiceImpl();
        ReflectionTestUtils.setField(service, "storageTestTaskService", taskService);
        ReflectionTestUtils.setField(service, "storageTestNodeService", nodeService);
        ReflectionTestUtils.setField(service, "storageTestCaseService", caseService);
        ReflectionTestUtils.setField(service, "storageTestResultService", resultService);
        ReflectionTestUtils.setField(service, "storageSampleService", sampleService);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.runTask(101L));
        assertTrue(exception.getMessage().contains("IDLE"));
        assertEquals("FAILED", task.getTaskStatus());
        assertTrue(task.getErrorMessage().contains("IDLE"));
        verify(taskService, times(1)).updateById(task);
    }

    @Test
    public void runEndpointReturnsOuterResponseWithFlatTaskData() {
        StorageMockRunnerService runnerService = mock(StorageMockRunnerService.class);
        StorageTestTaskController controller = new StorageTestTaskController();
        ReflectionTestUtils.setField(controller, "storageMockRunnerService", runnerService);

        StorageTestTaskEntity task = new StorageTestTaskEntity();
        task.setId(200L);
        task.setTaskStatus("COMPLETED");
        when(runnerService.runTask(200L)).thenReturn(task);

        R response = controller.run(200L);

        Object data = response.get("data");
        assertTrue(data instanceof java.util.Map);
        assertFalse(data instanceof R);
        @SuppressWarnings("unchecked")
        java.util.Map<String, Object> payload = (java.util.Map<String, Object>) data;
        assertEquals(200L, payload.get("taskId"));
        assertEquals("COMPLETED", payload.get("status"));
    }
}
