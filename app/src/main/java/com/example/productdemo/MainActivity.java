package com.example.productdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.productdemo.Adapters.CategoryAdapter;
import com.example.productdemo.Adapters.FragmentAdapter;
import com.example.productdemo.Adapters.ProductAdapter;
import com.example.productdemo.Fragmets.CategoryFragment;
import com.example.productdemo.Fragmets.ProductFragment;
import com.example.productdemo.Helper.DatabaseHelper;
import com.example.productdemo.models.CategorieItem;
import com.example.productdemo.models.ProductItems;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.example.productdemo.DbBitmapUtility.getBytes;
import static com.example.productdemo.Fragmets.CategoryFragment.recyclerViewCategory;
import static com.example.productdemo.Fragmets.ProductFragment.recyclerViewProduct;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> fragments;
    public static ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    File mPhotoFile;
    ImageView edit_productImageView;
    ArrayList<ProductItems> productItemsArrayList;
    ArrayList<CategorieItem> categorieItemArrayList;
    DatabaseHelper databaseHelper;
    byte[] image;
    ProductAdapter productAdapter;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(MainActivity.this);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        fragments = new ArrayList<>();
        fragments.add(new CategoryFragment());
        fragments.add(new ProductFragment());
        FragmentAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), getApplicationContext(), fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                //add the function to perform here
                Toast.makeText(this, "Hello Add !", Toast.LENGTH_SHORT).show();
                showAlertDialog();
//            dialogView();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    private void showAlertDialog() {
        final CharSequence[] items = {
                "Add Category", "Add Product",
                "Cancel"
        };
        ViewGroup viewGroup = findViewById(android.R.id.content);
        TextView addCategorie, addProduct;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_alert_dialog, viewGroup, false);
//        builder.setView(view);
        builder.setCancelable(false);
        builder.setTitle("Add Data").
                setIcon(R.drawable.add_data);
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Add Category")) {
                Toast.makeText(this, "Hello Category !", Toast.LENGTH_SHORT).show();
                addCategoryDialog();
            } else if (items[item].equals("Add Product")) {
                addProductDialog();
                Toast.makeText(this, "Hello Product !", Toast.LENGTH_SHORT).show();
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void addCategoryDialog() {
        EditText edtiCategory;
        Button button;
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_category_dialog, viewGroup, false);
        edtiCategory = dialogView.findViewById(R.id.categoryEditText_dl);
        button = dialogView.findViewById(R.id.addCategoryBtn_dl);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cate = edtiCategory.getText().toString();
                if (cate.isEmpty()) {
                    edtiCategory.setError("Enter Category !");
                    edtiCategory.requestFocus();
                    return;
                } else {
                    databaseHelper.addCategory(cate);
                    categorieItemArrayList = new ArrayList<>();
                    categorieItemArrayList = databaseHelper.gelCategoryAllDat();
                    categoryAdapter = new CategoryAdapter(categorieItemArrayList, MainActivity.this);
                    categoryAdapter.notifyDataSetChanged();
                    recyclerViewCategory.setAdapter(categoryAdapter);
                    viewPager.setCurrentItem(1, true);
                    alertDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Hua Add", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.show();
    }

    private void addProductDialog() {
        EditText edit_prodctName, edit_product_price, editCategory;
        ImageView add_imageView;
        Button addProduct;
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_product_dialog, viewGroup, false);
        edit_prodctName = dialogView.findViewById(R.id.productName_dl);
        edit_product_price = dialogView.findViewById(R.id.productPrice_dl);
        edit_productImageView = dialogView.findViewById(R.id.productImage_dl);
        add_imageView = dialogView.findViewById(R.id.add_product_image_dl);
        addProduct = dialogView.findViewById(R.id.addProductBtn_dl);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        add_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(i, 2);
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = edit_prodctName.getText().toString();
                String productPrice = edit_product_price.getText().toString();
                if (productName.isEmpty()) {
                    edit_prodctName.setError("Please Enter Name !");
                    edit_prodctName.requestFocus();
                    return;
                } else if (productPrice.isEmpty()) {
                    edit_product_price.setError("Enter Product Price !");
                    edit_product_price.requestFocus();
                    return;
                } else {
                    databaseHelper.addEntry(productName, productPrice, image);
                    productItemsArrayList = new ArrayList<>();
                    productItemsArrayList = databaseHelper.gelProductAllDat();
                    productAdapter = new ProductAdapter(productItemsArrayList, MainActivity.this);
                    productAdapter.notifyDataSetChanged();
                    recyclerViewProduct.setAdapter(productAdapter);
                    viewPager.setCurrentItem(1, true);
                    alertDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Hua Add", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                image = getBytes(bitmap);
                edit_productImageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}