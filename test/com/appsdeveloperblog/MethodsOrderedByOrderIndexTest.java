package com.appsdeveloperblog;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance (TestInstance.Lifecycle.PER_METHOD) // this is default
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodsOrderedByOrderIndexTest {

    StringBuilder completed = new StringBuilder("");

    @Order(1)
    @Test
    void testD() {
        System.out.println("running test D");
        completed.append("1");
        System.out.println(completed);
    }

    @Order(2)
    @Test
    void testA() {
        System.out.println("running test A");
        completed.append("2");
        System.out.println(completed);
    }

    @Order(4)
    @Test
    void testB() {
        System.out.println("running test B");
        completed.append("4");
        System.out.println(completed);
    }

    @Order(3)
    @Test
    void testC() {
        System.out.println("running test C");
        completed.append("3");
        System.out.println(completed);
    }
}
