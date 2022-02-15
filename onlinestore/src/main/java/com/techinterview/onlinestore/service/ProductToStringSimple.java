package com.techinterview.onlinestore.service;

import com.techinterview.onlinestore.domain.Product;

import java.lang.reflect.Field;

public class ProductToStringSimple implements ProductToString {
    Format format = Format.NAME_TO_VALUE;

    @Override
    public String getDescription(Product product) {
        if (format == Format.JSON) return getJsonDescription(product);
        return getNameToValueDescription(product);
    }

    private String getNameToValueDescription(Product product) {
        Field[] allFields = product.getClass().getDeclaredFields();
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(product.getName()).append(" (").append(product.getGuid()).append(") , ");
        for (Field field : allFields) {
            try {
                field.setAccessible(true);
                sBuilder.append(field.getName()).append(":").append(field.get(product)).append(", ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sBuilder.toString();
    }

    private String getJsonDescription(Product product) {
        return null;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
}
