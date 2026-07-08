package com.controller.storage;

import com.dto.storage.MetricQueryRequest;
import com.service.storage.StorageMetricQueryService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage-metrics")
public class StorageMetricQueryController {
    @Autowired
    private StorageMetricQueryService storageMetricQueryService;

    @RequestMapping("/query")
    public R query(@RequestBody MetricQueryRequest request) {
        return R.ok().put("data", storageMetricQueryService.query(request));
    }
}
