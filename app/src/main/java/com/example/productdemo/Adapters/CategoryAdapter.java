package com.example.productdemo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productdemo.R;
import com.example.productdemo.models.CategorieItem;

import java.util.ArrayList;

/**
 * Created by SANJEET KUMAR on 18,March,2021, sk698166@gmail.com
 **/
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    ArrayList<CategorieItem> categorieItemArrayList;
    Context context;

    public CategoryAdapter(ArrayList<CategorieItem> categorieItemArrayList, Context context) {
        this.categorieItemArrayList = categorieItemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categorie_row_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        holder.categorieItem.setText(categorieItemArrayList.get(position).getCategorieName());
    }

    @Override
    public int getItemCount() {
        return categorieItemArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categorieItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categorieItem = itemView.findViewById(R.id.categorieItem);
        }
    }
}
