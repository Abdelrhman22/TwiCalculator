package com.example.calculator.utils;

import java.math.BigDecimal;

public class Utils {

    public static final int PLUS_OPERATION_CODE = 1;
    public static final int MINUS_OPERATION_CODE = -1;
    public static final int MULTIPLY_OPERATION_CODE = 2;
    public static final int DIVIDE_OPERATION_CODE = -2;

    // parse double
    public static Double parseDouble(String value) {
        double x = 0.00;
        x = Double.parseDouble(value);
        return x;
    }

    // round double value
    public static String round(double value, int precision) {
        BigDecimal bd = new BigDecimal(value);
        BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
        return rounded.toString();
    }

    public static Operation getOperationByCode(int code){
        Operation operation = Operation.PLUS;
        switch (code) {
            case 1:
                operation = Operation.PLUS;
                break;
            case -1:
                operation = Operation.MINUS;
                break;
            case 2:
                operation = Operation.MULTIPLY;
                break;
            case -2:
                operation=  Operation.DIVIDE;
                break;
        }
        return operation;
    }
    public static String getOperationAsString (Operation operation){
        String op = "";
        switch (operation) {
            case PLUS:
                op = "+";
                break;
            case MINUS:
                op = "-";
                break;
            case MULTIPLY:
                op = "*";
                break;
            case DIVIDE:
                op = "/";
                break;
        }
        return op;
    }
}
