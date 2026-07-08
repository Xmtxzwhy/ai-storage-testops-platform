package com.controller.storage;

import com.dto.storage.CreateStorageTaskRequest;
import com.entity.storage.StorageTestTaskEntity;
import com.service.storage.StorageMockRunnerService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/storage-tasks")
public class StorageTestTaskController {
    @Autowired
    private StorageMockRunnerService storageMockRunnerService;

    @RequestMapping("/create")
    public R create(@RequestBody CreateStorageTaskRequest request) {
        StorageTestTaskEntity task = storageMockRunnerService.createTask(request);
        return R.ok().put("data", task);
    }

    @RequestMapping("/{id}/run")
    public R run(@PathVariable("id") Long id) {
        StorageTestTaskEntity task = storageMockRunnerService.runTask(id);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("taskId", task.getId());
        data.put("status", task.getTaskStatus());
        return R.ok().put("data", data);
    }

    @RequestMapping("/{id}/results")
    public R results(@PathVariable("id") Long id) {
        return R.ok().put("data", storageMockRunnerService.getTaskResults(id));
    }
}
