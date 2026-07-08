package com.controller.storage;

import com.entity.storage.StorageTestReportEntity;
import com.service.storage.StorageReportGeneratorService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/storage-reports")
public class StorageReportController {
    @Autowired
    private StorageReportGeneratorService storageReportGeneratorService;

    @RequestMapping("/create")
    public R create(@RequestBody com.dto.storage.CreateStorageReportRequest request) {
        StorageTestReportEntity report = storageReportGeneratorService.createReport(request);
        return R.ok().put("data", report);
    }

    @RequestMapping("/{id}/generate")
    public R generate(@PathVariable("id") Long id) {
        StorageTestReportEntity report = storageReportGeneratorService.generate(id);
        return R.ok().put("data", report);
    }

    @RequestMapping("/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", storageReportGeneratorService.getReportDetail(id));
    }

    @RequestMapping("/{id}/download")
    public void download(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        File file = storageReportGeneratorService.getReportFile(id);
        String fileName = URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20");
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentLengthLong(file.length());

        FileInputStream inputStream = new FileInputStream(file);
        try {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, length);
            }
            response.getOutputStream().flush();
        } finally {
            inputStream.close();
        }
    }
}
