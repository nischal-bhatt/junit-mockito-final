package com.appsdeveloperblog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(3)
public class OrderServiceTest {

    @BeforeAll
    static void setup()
    {
        System.out.println("order");
    }

    @Test
    void test()
    {
        System.out.println("hey");
    }
}
