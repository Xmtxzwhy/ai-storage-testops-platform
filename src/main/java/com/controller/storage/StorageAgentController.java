package com.controller.storage;

import com.dto.storage.StorageAgentParseRequest;
import com.service.storage.StorageAgentFacadeService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage-agent")
public class StorageAgentController {
    @Autowired
    private StorageAgentFacadeService storageAgentFacadeService;

    @RequestMapping("/parse")
    public R parse(@RequestBody StorageAgentParseRequest request) {
        return R.ok().put("data", storageAgentFacadeService.parse(request.getText()));
    }
}
