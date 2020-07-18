package com.example.calculator;

import com.example.calculator.models.OperationItem;
import com.example.calculator.utils.Operation;
import com.example.calculator.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Calculations {

    public static double handleOperation(double firstOperand, Operation operation, double secondOperand) {
        return operation.calculate(firstOperand, secondOperand);
    }
    public static double handleUndo(double firstOperand , List <OperationItem> list){
       OperationItem operationItem = list.get(list.size()-1);
        Operation operation = Utils.getOperationByCode((operationItem.getCode() * -1));
        double result = handleOperation(firstOperand,operation,Utils.parseDouble(operationItem.getValue()));
        return result;
    }
    public static double handleRedo(double firstOperand , List <OperationItem> list){
       OperationItem operationItem = list.get(list.size()-1);
        Operation operation = Utils.getOperationByCode((operationItem.getCode()));
        double result = handleOperation(firstOperand,operation,Utils.parseDouble(operationItem.getValue()));
        return result;
    }
}
