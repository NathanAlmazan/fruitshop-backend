package com.ntea.fruitshop.global.errors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomErrorResponse {
    private List<String> errorMessage;
    private final LocalDateTime timestamp;
    private final int statusCode;

    public CustomErrorResponse(int code) {
        this.statusCode = code;
        this.timestamp = LocalDateTime.now();
    }

    public void setErrorMessage(List<String> message) {
        this.errorMessage = message;
    }

    public void addErrorMessage(String message) {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(message);
        this.errorMessage = errorMessages;
    }

    public Object getErrorObject() {
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("timestamp", this.timestamp.toString());
        errorBody.put("errors", this.errorMessage);
        errorBody.put("status", this.statusCode);

        return errorBody;
    }
}
