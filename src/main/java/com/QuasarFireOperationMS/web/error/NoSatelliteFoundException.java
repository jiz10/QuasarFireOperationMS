package com.QuasarFireOperationMS.web.error;

/**
 * @author jiz10
 * 1/5/21
 */
public class NoSatelliteFoundException extends RuntimeException {

    public NoSatelliteFoundException() {
        super();
    }

    public NoSatelliteFoundException(String message) {
        super(message);
    }

}
