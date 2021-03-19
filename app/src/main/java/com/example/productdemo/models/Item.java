package com.example.productdemo.models;

/**
 * Created by SANJEET KUMAR on 18,March,2021, sk698166@gmail.com
 **/
public class Item {
    public final String text;
    public final int icon;
    public Item(String text, Integer icon) {
        this.text = text;
        this.icon = icon;
    }
    @Override
    public String toString() {
        return text;
    }
}
