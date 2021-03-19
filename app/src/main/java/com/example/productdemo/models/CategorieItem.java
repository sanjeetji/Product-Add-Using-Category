package com.example.productdemo.models;

/**
 * Created by SANJEET KUMAR on 18,March,2021, sk698166@gmail.com
 **/

public class CategorieItem {

    String categorieName;
    public CategorieItem() {}

    public CategorieItem(String categorieName) {
        this.categorieName = categorieName;
    }

    public String getCategorieName() {
        return categorieName.toString();
    }

    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }

    @Override
    public String toString(){
        return this.categorieName;
    }
}
