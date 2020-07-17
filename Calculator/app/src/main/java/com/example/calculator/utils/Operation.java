package com.example.calculator.utils;

public enum Operation {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE;

    public double calculate(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case MULTIPLY:
                return x * y;
            case DIVIDE:
                return x / y;
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }
}