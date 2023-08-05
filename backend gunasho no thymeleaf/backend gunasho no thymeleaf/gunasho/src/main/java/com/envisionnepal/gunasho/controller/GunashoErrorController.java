/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.controller;

import com.envisionnepal.gunasho.responseandregister.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 *
 * @author User
 */
@ControllerAdvice
public class GunashoErrorController extends ResponseEntityExceptionHandler {
        @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(HttpClientErrorException.Forbidden ex) {
        ErrorResponse errorResponse = new ErrorResponse("Password Incorrect", "You do not have permission to access this resource.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    
}
