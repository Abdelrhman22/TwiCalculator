package com.example.calculator.models;

import com.example.calculator.utils.Operation;

public class OperationItem {
    private Operation operation;
    private String value;
    private int code;

    public OperationItem(Operation operation, String value, int code) {
        this.operation = operation;
        this.value = value;
        this.code = code;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
