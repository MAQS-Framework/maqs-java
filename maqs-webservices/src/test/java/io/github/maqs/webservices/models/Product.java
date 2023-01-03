/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.webservices.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;

@JsonPropertyOrder({"Id", "Name", "Category", "Price"})
public class Product {

    @JacksonXmlProperty(isAttribute = true)
    private final String xmlns = "http://schemas.datacontract.org/2004/07/MainTestService.Models";

    @JsonAlias({"Id", "id"})
    @JacksonXmlProperty(localName = "Id")
    private int id;

    @JacksonXmlProperty(localName = "Name")
    @JsonAlias({"Name", "name"})
    private String name;

    @JacksonXmlProperty(localName = "Category")
    @JsonAlias({"Category", "category"})
    private String category;

    @JacksonXmlProperty(localName = "Price")
    @JsonAlias({"Price", "price"})
    private BigDecimal price;

    public Product(int id, String name, String category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    /**
     * Used to populate the product class.
     * Needed to populate the class object, don't delete.
     */
    private Product() {
    }

    public String getXmlns() {
        return xmlns;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String toString() {
        return String.format("%s:%d\n", "Id", this.getId())
                + String.format("%s:%s\n", "Name", this.getName())
                + String.format("%s:%s\n", "Category", this.getCategory())
                + String.format("%s:%s\n", "Price", this.getPrice())
                + String.format("%s:%s\n", "XMLNS", this.getXmlns());
    }
}