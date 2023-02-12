package com.appsdeveloperblog;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodsOrderedByOrderIndexTest {

    @Order(1)
    @Test
    void testD() {
        System.out.println("running test D");
    }

    @Order(2)
    @Test
    void testA() {
        System.out.println("running test A");
    }

    @Order(4)
    @Test
    void testB() {
        System.out.println("running test B");
    }

    @Order(3)
    @Test
    void testC() {
        System.out.println("running test C");
    }
}
