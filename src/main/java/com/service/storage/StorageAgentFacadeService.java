package com.service.storage;

import com.dto.storage.StorageAgentParseResult;
import com.dto.storage.StorageAgentChatRequest;
import com.dto.storage.StorageAgentChatResponse;

public interface StorageAgentFacadeService {
    StorageAgentParseResult parse(String text);

    StorageAgentChatResponse chat(StorageAgentChatRequest request);
}
