package com.techinterview.onlinestore.service;

import com.techinterview.onlinestore.domain.BackPack;
import com.techinterview.onlinestore.domain.Product;
import com.techinterview.onlinestore.domain.SmartPhone;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductListProcessorTest {
    private ProductToString productToString = new ProductToStringSimple();
    private ProductListProcessor processor = new ProductListProcessor(productToString);

    @Test
    public void test() {
        Product backPack = new BackPack("11-222-333", "Cool Backpack");
        Product smartPhone = new SmartPhone("444-555-666", "phone");
        List<Product> products = new ArrayList<>();
        products.add(backPack);
        products.add(smartPhone);
        final String descriptions = processor.productListToString(products);
        System.out.println(descriptions);
    }
}
