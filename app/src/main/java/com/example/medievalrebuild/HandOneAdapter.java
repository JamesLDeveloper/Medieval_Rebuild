package com.example.medievalrebuild;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medievalrebuild.Equipable.Equipable;

import java.util.ArrayList;

public class HandOneAdapter extends RecyclerView.Adapter<HandOneAdapter.ViewHolder> {
    private ArrayList<Equipable> equipablesArrayList;
    private OnItemClickListener listener;

    private OnBuyClickListener buyClickListener;


    public HandOneAdapter(ArrayList<Equipable> equipablesArrayList) {
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
        holder.itemView.setOnClickListener(v -> {
            if (listener!= null) {
                listener.onItemClick(equipable);
            }
        });

//        holder.btnBuyItem



        holder.equipableInListDescription.setText(equipable.toString());
        holder.equipableInListGoldCost.setText("Gold Cost" + equipable.getGoldPurchaseCost());
        holder.equipableInListImage.setImageResource(equipable.getImageId());

    }


    public interface OnItemClickListener{
        void onItemClick(Equipable equipable);
    }

    public interface OnBuyClickListener {
        void onBuyClick(Equipable equipable);
    }




    public void setOnEquipableClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public void setOnBuyClickListener(OnBuyClickListener buyClickListener){
        this.buyClickListener = buyClickListener;
    }

    @Override
    public int getItemCount() {
        return equipablesArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // Declare your item views here (e.g., TextView, ImageView)
        // Example: TextView textView;

        Button btnBuyItem;

        Button equipableInListDescription;

        Button equipableInListGoldCost;

        ImageButton equipableInListImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize your item views here
            // Example: textView = itemView.findViewById(R.id.textView);

            equipableInListDescription = itemView.findViewById(R.id.btn_item_in_shop_equipable_description);
            equipableInListGoldCost = itemView.findViewById(R.id.btn_item_in_shop_equipable_goldCost);
            equipableInListImage = itemView.findViewById(R.id.imbtn_item_in_shop_equipable_image);

        }
    }
}
