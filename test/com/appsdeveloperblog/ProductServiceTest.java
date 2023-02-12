package com.appsdeveloperblog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(2)
public class ProductServiceTest {

    @BeforeAll
    static void setup()
    {
        System.out.println("product");
    }

    @Test
    void test()
    {
        System.out.println("hey");
    }
}
