package com.practice.metroapp.model;

import com.practice.metroapp.util.Constants.Status;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */
@Data
public class ResponseWrapper<T> {
    T data;
    Status status;
    HttpStatus httpStatus;
}
