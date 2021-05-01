package com.QuasarFireOperationMS.web.error;

import com.QuasarFireOperationMS.web.model.base.ResponseCode;
import com.QuasarFireOperationMS.web.model.base.ResponseModel;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author jiz10
 * 30/4/21
 */
@ControllerAdvice
public class GlobalErrorResponseHandler {

    public static final String REQUEST_BODY_ERROR_MESSAGE = "Body object format error!";
    public static final String REQUEST_OBJECT_ERROR_MESSAGE = "Invalid argument!";
    public static final String REQUEST_MISSING_PARAMS_MESSAGE = "Missing parameter(s)!";
    public static final String REQUEST_METHOD_NOT_ALLOWED_MESSAGE = "Request method not supported";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error!";
    public static final String TYPE_MISMATCH_MESSAGE = "Invalid value for the argument";
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "Illegal value for the argument";
    public static final String NULL_POINTER_MESSAGE = "Null pointer";
    public static final String SATELLITE_DOES_NOT_EXIST = "Satellite doesn't exist";

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> handleBindException(BindException ex) {
        return new ResponseEntity(ex.getAllErrors(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public void badRequestObjectFormat(final HttpServletRequest request, final HttpServletResponse response, Exception ex) throws IOException {
        sendError(request, response, HttpServletResponse.SC_NOT_FOUND, REQUEST_BODY_ERROR_MESSAGE, ex);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public void missingFieldInRequestObject(final HttpServletRequest request, final HttpServletResponse response, Exception ex) throws IOException {
        sendError(request, response, HttpServletResponse.SC_NOT_FOUND, REQUEST_OBJECT_ERROR_MESSAGE, ex);
    }

    @ExceptionHandler({UnsatisfiedServletRequestParameterException.class})
    public void missingRequestParameters(final HttpServletRequest request, final HttpServletResponse response, Exception ex) throws IOException {
        sendError(request, response, HttpServletResponse.SC_NOT_FOUND, REQUEST_MISSING_PARAMS_MESSAGE, ex);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public void methodNotSupported(final HttpServletRequest request, final HttpServletResponse response, Exception ex) throws IOException {
        sendError(request, response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, REQUEST_METHOD_NOT_ALLOWED_MESSAGE, ex);
    }

    @ExceptionHandler({Exception.class})
    public void internalServerError(final HttpServletRequest request, final HttpServletResponse response, Exception ex) throws IOException {
        sendError(request, response, HttpServletResponse.SC_NOT_FOUND, INTERNAL_SERVER_ERROR_MESSAGE, ex);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public void methodArgumentTypeMismatchException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        sendError(request, response, HttpServletResponse.SC_NOT_FOUND, TYPE_MISMATCH_MESSAGE, ex);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public void illegalArgumentException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        sendError(request, response, HttpServletResponse.SC_NOT_FOUND, ILLEGAL_ARGUMENT_MESSAGE, ex);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void missingServletRequestParameterException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        sendError(request, response, HttpServletResponse.SC_NOT_FOUND, REQUEST_MISSING_PARAMS_MESSAGE, ex);
    }

    @ExceptionHandler(NoSatelliteFoundException.class)
    public void noSatelliteFoundException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        sendError(request, response, HttpServletResponse.SC_BAD_REQUEST, SATELLITE_DOES_NOT_EXIST, ex);
    }

    private void sendError(HttpServletRequest request, HttpServletResponse response, int statusCode, String message, Exception ex) throws IOException {
        ex.printStackTrace();
        request.setAttribute(DefaultErrorAttributes.class.getName() + ".ERROR", null);
        response.sendError(statusCode, message);
    }

    private ResponseEntity sendResponse(ResponseCode responseCode) {
        return ResponseEntity.ok(ResponseModel.info(responseCode.getCode(), responseCode.getMessage()));
    }

    private ResponseEntity sendResponse(ResponseCode responseCode, String param) {
        final String message = MessageFormat.format(responseCode.getMessage(), param);
        return ResponseEntity.ok(ResponseModel.info(responseCode.getCode(), message));
    }


}
