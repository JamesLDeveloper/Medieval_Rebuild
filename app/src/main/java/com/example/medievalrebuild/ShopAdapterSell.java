
package com.example.medievalrebuild;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medievalrebuild.Equipable.Equipable;

import java.util.ArrayList;

public class ShopAdapterSell extends RecyclerView.Adapter<ShopAdapterSell.ViewHolder> {
    private ArrayList<Equipable> equipablesArrayList;
    private OnItemClickListener listener;
    private OnSellClickListener sellClickListener;

    private ShopActivity shopActivity;

//    private Button backButton;
//    private Button btnBuyItem;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout btnSellItem;
        Button equipableInListDescription;
        Button equipableInListSellValue;
        ImageButton equipableInListImage;
        Button backButton;
        ShopAdapterSell adapter;


        public ViewHolder(@NonNull View itemView, ShopAdapterSell adapter) {
            super(itemView);



            equipableInListDescription = itemView.findViewById(R.id.btn_equipable_to_sell_description);
            equipableInListSellValue = itemView.findViewById(R.id.btn_equipable_to_sell_gold_value);
            equipableInListImage = itemView.findViewById(R.id.imbtn_equipable_to_sell_image);
            backButton = itemView.findViewById(R.id.btn_dialog_equipable_to_sell_back);
            btnSellItem = itemView.findViewById(R.id.btn_dialog_equipable_to_sell_sell_item);


            int autoSizeMinTextSize = 6;
            int autoSizeMaxTextSize = 30;
            int autoSizeStepGranularity = 1;
            int unit = TypedValue.COMPLEX_UNIT_SP;

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    equipableInListDescription, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    equipableInListSellValue, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);


            this.adapter = adapter;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    System.out.println("itemView clicked");

                    if (adapter != null && adapter.listener != null) {
                        adapter.listener.onItemClick(adapter.equipablesArrayList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public ShopAdapterSell(ArrayList<Equipable> equipablesArrayList, ShopActivity shopActivity) {
        this.equipablesArrayList = equipablesArrayList;
        this.shopActivity = shopActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipable_in_shop_sell, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Equipable equipable = equipablesArrayList.get(position);


        if (holder.btnSellItem != null) {

            holder.btnSellItem.setOnClickListener(v -> {

                if (sellClickListener == null) {
                    System.out.println("sellClickListener is Null");
                }


                if (sellClickListener != null) {
                    System.out.println("buyClickListener is not Null");
                    sellClickListener.onSellClick(equipable);
                }
            });
        } else {

            System.out.println("holder.btnSellItem = null");
        }

        holder.equipableInListDescription.setText(equipable.toString());
        holder.equipableInListSellValue.setText("Gold Value: " + equipable.getGoldSellValue());
        holder.equipableInListImage.setImageResource(equipable.getImageId());

    }

    public interface OnItemClickListener {
        void onItemClick(Equipable equipable);
    }

    public interface OnSellClickListener {
        void onSellClick(Equipable equipable);
    }

    public void setOnEquipableClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnSellClickListener(OnSellClickListener buyClickListener) {
        this.sellClickListener = buyClickListener;
    }

    @Override
    public int getItemCount() {
        return equipablesArrayList.size();
    }
}
