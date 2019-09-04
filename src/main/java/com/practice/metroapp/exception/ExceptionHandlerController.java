package com.practice.metroapp.exception;

import com.practice.metroapp.model.ResponseWrapper;
import com.practice.metroapp.util.Constants;
import com.practice.metroapp.util.Constants.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MetroAPIException.class)
    public ResponseEntity<ResponseWrapper<String>> getMetroAPIException(MetroAPIException exception) {

        ResponseWrapper<String> response = new ResponseWrapper<>();
        response.setHttpStatus(exception.getHttpStatus());
        response.setStatus(Status.failure);
        response.setData(exception.toString());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<String>> unknownException(Exception exception) {
        ResponseWrapper<String> response = new ResponseWrapper<>();
        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setData(exception.getMessage());
        response.setStatus(Status.failure);
        return ResponseEntity.ok(response);
    }
}
