package com.example.productdemo.models;

import android.graphics.Bitmap;

/**
 * Created by SANJEET KUMAR on 18,March,2021, sk698166@gmail.com
 **/
public class ProductItems {
    String name, price;
    int img;
    Bitmap bitmap;

    public ProductItems() {
    }

    public ProductItems(String name, int img, String price) {
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public ProductItems(String name, Bitmap bitmap, String price) {
        this.name = name;
        this.bitmap = bitmap;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
