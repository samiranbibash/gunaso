package com.envisionnepal.gunasho.exception;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long feildValue;
    String stringFieldValue;


    public ResourceNotFoundException(String resourceName, String fieldName, long feildValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, feildValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.feildValue = feildValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String stringFieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, stringFieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.stringFieldValue = stringFieldValue;
    }}
