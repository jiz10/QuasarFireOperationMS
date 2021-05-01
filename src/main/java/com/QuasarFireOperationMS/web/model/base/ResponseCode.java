package com.QuasarFireOperationMS.web.model.base;

public enum ResponseCode {
    OK("0000", "OK");
    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
