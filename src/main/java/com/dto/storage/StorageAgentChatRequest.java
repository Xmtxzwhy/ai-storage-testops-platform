package com.dto.storage;

public class StorageAgentChatRequest {
    private String sessionId;
    private String message;
    private String confirmActionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConfirmActionId() {
        return confirmActionId;
    }

    public void setConfirmActionId(String confirmActionId) {
        this.confirmActionId = confirmActionId;
    }
}
