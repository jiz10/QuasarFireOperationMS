package com.QuasarFireOperationMS.web.model.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseModel<T> extends ResponseInfo {

    T data;

    public ResponseModel(T data) {
        this.data = data;
    }

    private ResponseModel(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseModel<T> success(T data) {
        return new ResponseModel<>(data);
    }

    public static <T> ResponseModel<T> success() {
        return new ResponseModel<>();
    }

    public static <T> ResponseModel<T> info(String code, String message) {
        return new ResponseModel<>(code, message);
    }
}
