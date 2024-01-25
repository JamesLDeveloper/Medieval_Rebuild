package com.example.medievalrebuild;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medievalrebuild.Equipable.Equipable;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private ArrayList<Equipable> equipablesArrayList;

    public ShopAdapter(ArrayList<Equipable> equipablesArrayList) {
        this.equipablesArrayList = equipablesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipable_in_shop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Equipable equipable = equipablesArrayList.get(position);
        // Bind data to views in the ViewHolder
        // Example: holder.textView.setText(item.getName());

        holder.equipableInListDescription.setText(equipable.toString());
        holder.equipableInListGoldCost.setText("Gold Cost: " + equipable.getGoldPurchaseCost());
        holder.equipableInListImage.setImageResource(equipable.getImageId());

    }

    @Override
    public int getItemCount() {
        return equipablesArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // Declare your item views here (e.g., TextView, ImageView)
        // Example: TextView textView;

        Button equipableInListDescription;

        Button equipableInListGoldCost;

        ImageButton equipableInListImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize your item views here
            // Example: textView = itemView.findViewById(R.id.textView);

            int autoSizeMinTextSize = 6;
            int autoSizeMaxTextSize = 30;
            int autoSizeStepGranularity = 1;
            int unit = TypedValue.COMPLEX_UNIT_SP;


            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    equipableInListDescription, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    equipableInListGoldCost, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

            equipableInListDescription = itemView.findViewById(R.id.btn_item_in_shop_equipable_description);
            equipableInListGoldCost = itemView.findViewById(R.id.btn_item_in_shop_equipable_goldCost);
            equipableInListImage = itemView.findViewById(R.id.imbtn_item_in_shop_equipable_image);







        }
    }
}
