package com.QuasarFireOperationMS.web.error;

/**
 * @author jiz10
 * 1/5/21
 */
public class InsufficientInformationException extends RuntimeException {

    public InsufficientInformationException() {
        super();
    }

    public InsufficientInformationException(String message) {
        super(message);
    }
}
