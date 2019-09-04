package com.practice.metroapp.exception;

import com.practice.metroapp.util.Constants.FailureCode;
import org.springframework.http.HttpStatus;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */
public class MetroAPIException extends RuntimeException {
    private FailureCode errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;


    public MetroAPIException(FailureCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public MetroAPIException(FailureCode errorCode, String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public FailureCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("errorCode=").append(errorCode);
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", httpStatus=").append(httpStatus);
        sb.append('}');
        return sb.toString();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
