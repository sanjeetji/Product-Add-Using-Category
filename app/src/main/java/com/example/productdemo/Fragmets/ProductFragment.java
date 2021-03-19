package com.example.productdemo.Fragmets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productdemo.Adapters.ProductAdapter;
import com.example.productdemo.Helper.DatabaseHelper;
import com.example.productdemo.MainActivity;
import com.example.productdemo.R;
import com.example.productdemo.models.CategorieItem;
import com.example.productdemo.models.ProductItems;

import java.util.ArrayList;

import static com.example.productdemo.Fragmets.CategoryFragment.categorieItemArrayList;

/**
 * Created by SANJEET KUMAR on 18,March,2021, sk698166@gmail.com
 **/

public class ProductFragment extends Fragment {

    DatabaseHelper databaseHelper;

    View view;
    ArrayList<ProductItems> productItemsArrayList;
    public static RecyclerView recyclerViewProduct;
    ProductAdapter productAdapter;
    Spinner spinner;
    String item;
    String[] plants;
    ArrayList<String> ar;
    int i =0;
    String array[];
    ArrayList<CategorieItem> categorieItemArrayListnew,finalcategorieItemArrayListnew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product, container, false);
        databaseHelper = new DatabaseHelper(getActivity());
        recyclerViewProduct = view.findViewById(R.id.productRecyclerView);
        spinner = view.findViewById(R.id.chooseOption);
        getSpinnserValue();
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getActivity(),2));
        getProductList();
        return view;
    }

    private void getSpinnserValue() {
        categorieItemArrayListnew = new ArrayList<>();
        categorieItemArrayListnew.addAll(categorieItemArrayList);
        array = new String[categorieItemArrayListnew.size()];
        int g = categorieItemArrayListnew.size();
        finalcategorieItemArrayListnew = new ArrayList<>();
            for (i=0;i<g;i++){
                item = categorieItemArrayListnew.get(i).getCategorieName();
                finalcategorieItemArrayListnew.add(new CategorieItem(item));
                Log.e("====","Item is :"+item);
                Log.e("====","LinalcategorieItemArrayListnew is :"+finalcategorieItemArrayListnew);
            }
        final ArrayAdapter<CategorieItem> spinnerArrayAdapter =
                new ArrayAdapter<CategorieItem>(getActivity(),R.layout.spinner_item,finalcategorieItemArrayListnew);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        /*String[] plants = new String[]{
                item
        };*/
//        final ArrayList<CategorieItem> plantsList = new ArrayList<>();
        /*final ArrayAdapter<CategorieItem> spinnerArrayAdapter =
                new ArrayAdapter<CategorieItem>(getActivity(),R.layout.spinner_item,finalcategorieItemArrayListnew);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);*/
    }

    private void getProductList() {
        databaseHelper.gelProductAllDat();
        productItemsArrayList = new ArrayList<>();
        productItemsArrayList = databaseHelper.gelProductAllDat();
        productAdapter = new ProductAdapter(productItemsArrayList,getActivity());
        recyclerViewProduct.setAdapter(productAdapter);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.getFilter();
                return true;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                          }
                                      }
        );
    }



}
