package com.appsdeveloperblog;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoRepeatedTest {
    Calculator calculator;
    @BeforeEach
    void beforeEachTestMethod() {
        System.out.println("doing something before each method");
        calculator = new Calculator();
    }

    //@Disabled("Still need to work on it")
    @DisplayName("Division by zero")
    @RepeatedTest(value=3, name="{displayName}. Repitition {currentRepetition} of {totalRepetitions} ")
    void testIntegerDivision_WhenDividentIsDividedByZero_ShouldThrowArithmeticException(RepetitionInfo repetitionInfo, TestInfo testInfo) {

        System.out.println(testInfo.getTestMethod().get().getName());
        System.out.println(repetitionInfo.getCurrentRepetition());
        System.out.println(repetitionInfo.getTotalRepetitions());
        //Given
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

        //When & Then

        ArithmeticException ae = assertThrows(ArithmeticException.class, () -> {
            calculator.integerDivision(dividend, divisor);
        });

        //Then
        assertEquals(expectedExceptionMessage, ae.getMessage());

    }
}
