package com.example.productdemo.models;

/**
 * Created by SANJEET KUMAR on 18,March,2021, sk698166@gmail.com
 **/
public class TabModels {

    String category,products;

    public TabModels() {
    }

    public TabModels(String category, String products) {
        this.category = category;
        this.products = products;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
