package com.example.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    @DisplayName("Test addition of two numbers")
    void testAdd() {
        int result = calculatorService.add(5, 7);
        assertEquals(12, result, "5 + 7 should equal 12");
    }

    @Test
    @DisplayName("Test subtraction of two numbers")
    void testSubtract() {
        int result = calculatorService.subtract(10, 4);
        assertEquals(6, result, "10 - 4 should equal 6");
    }

    @Test
    @DisplayName("Test multiplication of two numbers")
    void testMultiply() {
        int result = calculatorService.multiply(3, 4);
        assertEquals(12, result, "3 * 4 should equal 12");
    }

    @Test
    @DisplayName("Test division of two numbers")
    void testDivide() {
        double result = calculatorService.divide(10, 2);
        assertEquals(5.0, result, "10 / 2 should equal 5.0");
    }

    @Test
    @DisplayName("Test division by zero throws IllegalArgumentException")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculatorService.divide(10, 0));
    }
}
