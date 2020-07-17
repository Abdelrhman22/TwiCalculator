package com.example.calculator;

import com.example.calculator.utils.Operation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculationsTest {
    Calculations calculations;
    @Before
    public void init(){
        calculations = new Calculations();
    }
    @Test
    public void testAddition(){
        assertEquals(8.0, calculations.handleOperation(3.0, Operation.PLUS,5.0),0.0);
    }
    @Test
    public void testSubtraction(){
        assertEquals(4.0, calculations.handleOperation(7.0, Operation.MINUS,3.0),0.0);
    }
    @Test
    public void testMultiplication(){
        assertEquals(36, calculations.handleOperation(6.0, Operation.MULTIPLY,6.0),0.0);
    }
    @Test
    public void testDivision(){
        assertEquals(8.0, calculations.handleOperation(16.0, Operation.DIVIDE,2.0),0.0);
    }

}
