package com.service.impl.storage;

import com.alibaba.fastjson.JSON;
import com.dto.storage.StorageAgentChatRequest;
import com.dto.storage.StorageAgentChatResponse;
import com.dto.storage.StorageAgentParseRequest;
import com.dto.storage.StorageAgentParseResult;
import com.entity.storage.StorageAgentRequestEntity;
import com.service.storage.StorageAgentFacadeService;
import com.service.storage.StorageAgentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service("storageAgentFacadeService")
public class StorageAgentFacadeServiceImpl implements StorageAgentFacadeService {
    private static final String AGENT_URL = "http://localhost:8090/api/agents/storage/parse";
    private static final String AGENT_CHAT_URL = "http://localhost:8090/api/agents/storage/chat";

    @Autowired
    private StorageAgentRequestService storageAgentRequestService;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public StorageAgentParseResult parse(String text) {
        StorageAgentParseRequest request = new StorageAgentParseRequest();
        request.setText(text);
        StorageAgentParseResult result = restTemplate.postForObject(AGENT_URL, request, StorageAgentParseResult.class);
        saveRequestRecord(text, result);
        return result;
    }

    @Override
    public StorageAgentChatResponse chat(StorageAgentChatRequest request) {
        return restTemplate.postForObject(AGENT_CHAT_URL, request, StorageAgentChatResponse.class);
    }

    private void saveRequestRecord(String text, StorageAgentParseResult result) {
        StorageAgentRequestEntity row = new StorageAgentRequestEntity();
        row.setRawInput(text);
        row.setIntent(result == null ? "UNKNOWN" : result.getIntent());
        row.setParsedJson(JSON.toJSONString(result));
        row.setMissingFields(result == null ? "" : JSON.toJSONString(result.getMissingFields()));
        row.setConfidence(toBigDecimal(result == null ? null : result.getConfidence()));
        row.setNeedConfirm(result == null || Boolean.TRUE.equals(result.getNeedConfirm()) ? 1 : 0);
        storageAgentRequestService.insert(row);
    }

    private BigDecimal toBigDecimal(Double value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(String.valueOf(value));
    }
}
