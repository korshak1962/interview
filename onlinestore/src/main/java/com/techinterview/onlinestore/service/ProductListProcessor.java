package com.techinterview.onlinestore.service;

import com.techinterview.onlinestore.domain.Product;

import java.util.List;

import static java.lang.System.lineSeparator;

/**
 * Implement the function productListToString that does conversion of provided list of products to text representation
 * of this list in this way.
 * - Result string is \n - separated string. E.g.
 * <p>
 * product 1 details
 * product 2 details
 * ...
 * product 3 details
 * <p>
 * - Each line contains details of one product
 * - Product description line should look like this:
 * Product name (product GUID), product attribute 1 name:product attribute1 value; ... product attribute n name:product attributen value;
 * For example. List contains BackPack definition:
 * BackPack {
 * guid: 111-222-333
 * name: Cool Backpack
 * maxContentWeight: 15
 * color: "Black"
 * capacity: 20
 * }
 * This becka pakc description string should look like this:
 * Cool Backpack (111-222-333), maxContentWeight: 15, color: "Black", capacity: 20
 * <p>
 * Keep in mind these requirements:
 * - String reorientation can be modified in future.
 * - There is a possibility to support multiply ways to convert list of products to string. E.g. it it is possible that in future
 * you will need to implement support of different formats of output string (e.g. json instead of \n-separated string).
 * The basic idea is to make your current implementation flexible and modifiable in the future.
 * <p>
 * You can use any build system to build the sources (maven, gralde, ant).
 * You can use any 3rd party libs in your application.
 * Any java version (>=8).
 * Code must be tested (framework is up to you).
 * <p>
 * PLEASE KEEP IN MIND THAT IT CAN BE THOUSANDS OF PRODUCT IMPLEMENTATIONS, NOT JUST 2!!! INSTANCE OF SOLUTION IS BAD AND IS NOT GOING TO
 * WORK IN A REAL WORLD!
 * <p>
 * Product classes (base class and implementations) can be changed to resolve the task,
 * Any new methods, properties or anything else can be added there.
 * <p>
 * If you are familiar with Git, please do work in a separate branch and create a pull request with your changes.
 */
public class ProductListProcessor {

    private ProductToString productToString;

    public ProductListProcessor(ProductToString productToString) {
        this.productToString = productToString;
    }

    /**
     * Make String representation of provided product list.
     *
     * @param products list of the products that needs to be converted to String
     * @return String representation of the provided list.
     */
    public String productListToString(List<Product> products) {
        StringBuilder sBuilder = new StringBuilder();
        for (Product product : products) {
            final String description = productToString.getDescription(product);
            sBuilder.append(description).append(lineSeparator());
        }
        return sBuilder.toString();
    }
}
