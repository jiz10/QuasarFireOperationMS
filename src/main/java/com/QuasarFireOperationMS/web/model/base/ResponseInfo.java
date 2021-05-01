package com.QuasarFireOperationMS.web.model.base;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseInfo {

    protected String code = ResponseCode.OK.getCode();
    protected String message = ResponseCode.OK.getMessage();

}
