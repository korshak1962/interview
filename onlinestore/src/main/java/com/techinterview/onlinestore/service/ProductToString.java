package com.techinterview.onlinestore.service;

import com.techinterview.onlinestore.domain.Product;

/**
 * interface to convert a Product to a descriptive string for printing.
 * Could be implemented with different services and injected according to config by Spring or Guice.
 */
public interface ProductToString {
    /**
     * selector for String output format.
     */
    enum Format {
        NAME_TO_VALUE,
        JSON,
        XML
    }

    /**
     * @param product Product to describe
     * @return a descriptive string for printing.
     */
    String getDescription(Product product);

    /**
     *
     * @param format to print description.
     */
    default void setFormat(Format format) {
    }
}
