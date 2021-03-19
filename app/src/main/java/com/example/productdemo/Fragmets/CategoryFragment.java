package com.example.productdemo.Fragmets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productdemo.Adapters.CategoryAdapter;
import com.example.productdemo.Helper.DatabaseHelper;
import com.example.productdemo.R;
import com.example.productdemo.models.CategorieItem;

import java.util.ArrayList;

/**
 * Created by SANJEET KUMAR on 18,March,2021, sk698166@gmail.com
 **/




public class CategoryFragment extends Fragment {

    View view;
    public static RecyclerView recyclerViewCategory;
    CategoryAdapter categoryAdapter;
    DatabaseHelper databaseHelper;
    public static ArrayList<CategorieItem> categorieItemArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_category, container, false);
        databaseHelper = new DatabaseHelper(getActivity());
        recyclerViewCategory = view.findViewById(R.id.categorieRecyclerView);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        getCategorieData();
        return view;
    }

    private void getCategorieData() {
        categorieItemArrayList = new ArrayList<>();
        categorieItemArrayList = databaseHelper.gelCategoryAllDat();
        categoryAdapter = new CategoryAdapter(categorieItemArrayList,getActivity());
        recyclerViewCategory.setAdapter(categoryAdapter);
    }

}
