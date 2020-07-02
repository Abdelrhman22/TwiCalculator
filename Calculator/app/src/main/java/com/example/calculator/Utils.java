package com.example.calculator;

import java.math.BigDecimal;

public class Utils {

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
}
