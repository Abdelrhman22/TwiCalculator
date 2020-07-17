package com.example.calculator;

import com.example.calculator.models.OperationItem;
import com.example.calculator.utils.Operation;

public class Calculations {

    public static double handleOperation(double firstOperand, Operation operation, double secondOperand) {
        return operation.calculate(firstOperand, secondOperand);
    }

}
