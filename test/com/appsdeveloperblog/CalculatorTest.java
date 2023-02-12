package com.appsdeveloperblog;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math operations in Calculator class")
class CalculatorTest {

    Calculator calculator;

    @BeforeAll
    static void setup() {
        System.out.println("executing before all method");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("executing after all method");
    }

    @BeforeEach
    void beforeEachTestMethod() {
        System.out.println("doing something before each method");
        calculator = new Calculator();
    }

    @AfterEach
    void afterEachTest() {
        System.out.println("after each method completes");
    }

    @DisplayName("Test 4/2 = 2")
    //test<System Under Test>_<Condition or Stage Change>_<Expected Result>
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo() {

        //Arrange   //Given

        int divident = 4;
        int divisor = 2;
        int expectedResult = 2;
        //Act  //When
        int actualResult = calculator.integerDivision(divident, divisor);
        //Assert  //Then
        assertEquals(expectedResult, actualResult, "did not produce expected results!");
    }

    //@Disabled("Still need to work on it")
    @DisplayName("Division by zero")
    @Test
    void testIntegerDivision_WhenDividentIsDividedByZero_ShouldThrowArithmeticException() {

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

    @DisplayName("Test 5-8 = -3")
    @Test
    void integerSubtraction() {

        int minuend = 5;
        int subtrahend = 8;
        int expectedResult = -3;
        int result = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(expectedResult, result, () -> minuend + "-" + subtrahend + "did not produce the right result" + expectedResult);
    }

    @DisplayName("Test Integer subtraction [minuend, subtrahend, expectedresult]")
    @ParameterizedTest
    //@MethodSource
    //@CsvSource({"33,1,32","24,2,22","54,3,51"})
   //@CsvSource("apple,orange")
    @CsvFileSource(resources = "/integerSubtraction.csv")
    void integerSubtractionParam(int minuend, int subtrahend, int expectedResult) {


        int result = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(expectedResult, result, () -> minuend + "-" + subtrahend + "did not produce the right result" + expectedResult);
    }

    private static Stream<Arguments> integerSubtractionParam()
    {
       return Stream.of(Arguments.of(5,8,-3),
        Arguments.of(24,8,16));
    }

    @ParameterizedTest
    @ValueSource(strings={"John","Kate"})
    void valueSourceDemo(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }

}