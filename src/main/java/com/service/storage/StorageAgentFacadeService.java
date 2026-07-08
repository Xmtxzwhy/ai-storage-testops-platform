package com.service.storage;

import com.dto.storage.StorageAgentParseResult;

public interface StorageAgentFacadeService {
    StorageAgentParseResult parse(String text);
}
