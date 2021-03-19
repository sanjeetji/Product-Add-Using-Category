package com.example.productdemo.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productdemo.R;
import com.example.productdemo.models.ProductItems;

import java.util.ArrayList;

/**
 * Created by SANJEET KUMAR on 18,March,2021, sk698166@gmail.com
 **/

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> implements Filterable {

    ArrayList<ProductItems> recipeModelArrayList;
    Activity context;
    ArrayList<ProductItems> filRecipe;

    public ProductAdapter(ArrayList<ProductItems> recipeModelArrayList, Activity context) {
        this.recipeModelArrayList = recipeModelArrayList;
        this.context = context;
        this.filRecipe = new ArrayList<>(recipeModelArrayList);
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_row_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
       /* Glide.with(context)
                .load(recipeModelArrayList.get(position).getImg())
                .centerCrop()
                .placeholder(R.drawable.no_photo)
                .into(holder.imageView);*/
        holder.imageView.setImageBitmap(recipeModelArrayList.get(position).getBitmap());
        holder.text.setText(recipeModelArrayList.get(position).getName());
        holder.recipePrice.setText(recipeModelArrayList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return recipeModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text, recipePrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_img);
            text = itemView.findViewById(R.id.productName);
            recipePrice = itemView.findViewById(R.id.productPrice);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<ProductItems> filterData = new ArrayList<>();
            if (keyword.toString().isEmpty()) {
                filterData.addAll(filRecipe);
            } else {
                for (ProductItems obj : filRecipe) {
                    if (obj.getName().toString().toLowerCase().contains(keyword.toString().toLowerCase())) {
                        filterData.add(obj);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterData;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipeModelArrayList.clear();
            recipeModelArrayList.addAll((ArrayList<ProductItems>) results.values);
            notifyDataSetChanged();
        }
    };
}
