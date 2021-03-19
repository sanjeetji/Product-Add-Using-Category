package com.example.productdemo.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.productdemo.models.CategorieItem;
import com.example.productdemo.models.ProductItems;

import java.util.ArrayList;

import static com.example.productdemo.DbBitmapUtility.getImage;

/**
 * Created by SANJEET KUMAR on 19,March,2021, sk698166@gmail.com
 **/


public class DatabaseHelper extends SQLiteOpenHelper {


    ArrayList<ProductItems> productItemsArrayList;
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "database_name.db";
    // Table Names
    private static final String DB_TABLE = "prodcut_table";
    private static final String DB_TABLE_CATEGORY = "category_table";
    // column names
    private static final String KEY_NAME = "product_name";
    private static final String KEY_NAME_CATEGORY = "category_name";
    private static final String KEY_PRICE = "product_price";
    private static final String KEY_IMAGE = "image_data";
    String name, price;
    byte[] image;
    Bitmap bitmap;

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "(" +
            KEY_NAME + " TEXT," +
            KEY_PRICE + " TEXT," +
            KEY_IMAGE + " BLOB);";
    // Table create statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + DB_TABLE_CATEGORY + "(" +
            KEY_NAME_CATEGORY + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating table
        db.execSQL(CREATE_TABLE_IMAGE);
        db.execSQL(CREATE_TABLE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        // create new table
        onCreate(db);
    }

    public void addEntry(String name, String price, byte[] image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_PRICE, price);
        cv.put(KEY_IMAGE, image);
        database.insert(DB_TABLE, null, cv);
    }

    public void addCategory(String name) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_CATEGORY, name);
        database.insert(DB_TABLE_CATEGORY, null, cv);
    }

    public Cursor fetch() {
        productItemsArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {DatabaseHelper.KEY_NAME, DatabaseHelper.KEY_PRICE, DatabaseHelper.KEY_IMAGE};
        Cursor cursor = db.query(DatabaseHelper.DB_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToNext();
            for (int i = 0; i < cursor.getCount(); i++) {
                int index = cursor.getColumnIndex(DatabaseHelper.KEY_IMAGE);
                Log.e("=====", "Cursor is :" + cursor.getString(cursor.getColumnIndex("product_name")));
                name = cursor.getString(cursor.getColumnIndex("product_name"));
                price = cursor.getString(cursor.getColumnIndex("product_price"));
                image = cursor.getBlob(index);
                bitmap = getImage(image);
                Log.e("========", "Name is Data is :" + name);
                Log.e("========", "Price is Data is :" + price);
                Log.e("========", "Image is Data is :" + image);
                Log.e("========", "Image is Data is :" + bitmap);
                productItemsArrayList.add(new ProductItems(name, bitmap, price));
                Log.e("========", "productItemsArrayList is Data is :" + productItemsArrayList);
                cursor.moveToNext();
            }
        }
        Log.e("========", "Image is Data is :" + productItemsArrayList);
        return cursor;
    }

    public ArrayList<ProductItems> gelProductAllDat() {
        ArrayList<ProductItems> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {DatabaseHelper.KEY_NAME, DatabaseHelper.KEY_PRICE, DatabaseHelper.KEY_IMAGE};
        Cursor cursor = db.query(DatabaseHelper.DB_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToNext();
            for (int i = 0; i < cursor.getCount(); i++) {
                int index = cursor.getColumnIndex(DatabaseHelper.KEY_IMAGE);
                Log.e("=====", "Cursor is :" + cursor.getString(cursor.getColumnIndex("product_name")));
                name = cursor.getString(cursor.getColumnIndex("product_name"));
                price = cursor.getString(cursor.getColumnIndex("product_price"));
                image = cursor.getBlob(index);
                bitmap = getImage(image);
                Log.e("========", "Name is Data is :" + name);
                Log.e("========", "Price is Data is :" + price);
                Log.e("========", "Image is Data is :" + image);
                Log.e("========", "Image is Data is :" + bitmap);
                list.add(new ProductItems(name, bitmap, price));
                Log.e("========", "productItemsArrayList is Data is :" + list);
                cursor.moveToNext();
            }
        }
        Log.e("========", "productItemsArrayList is Data Outer is :" + list);
        return list;

    }

    public ArrayList<CategorieItem> gelCategoryAllDat() {
        ArrayList<CategorieItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {DatabaseHelper.KEY_NAME_CATEGORY};
        Cursor cursor = db.query(DatabaseHelper.DB_TABLE_CATEGORY, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToNext();
            for (int i = 0; i < cursor.getCount(); i++) {
                name = cursor.getString(cursor.getColumnIndex("category_name"));
                Log.e("========", "Name is Data is :" + name);
                list.add(new CategorieItem(name));
                Log.e("========", "productItemsArrayList is Data is :" + list);
                cursor.moveToNext();
            }
        }
        Log.e("========", "productItemsArrayList is Data Outer is :" + list);
        return list;

    }
}
