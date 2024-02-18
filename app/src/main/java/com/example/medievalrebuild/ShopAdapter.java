
package com.example.medievalrebuild;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medievalrebuild.Equipable.Equipable;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private ArrayList<Equipable> equipablesArrayList;
    private OnItemClickListener listener;
    private OnBuyClickListener buyClickListener;

    private ShopActivity shopActivity;

//    private Button backButton;
//    private Button btnBuyItem;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout btnBuyItem;
        Button equipableInListDescription;
        Button equipableInListGoldCost;
        ImageButton equipableInListImage;
        Button backButton;
        ShopAdapter adapter;

        LinearLayout itemViewBuyButton;


        public ViewHolder(@NonNull View itemView, ShopAdapter adapter) {
            super(itemView);



            equipableInListDescription = itemView.findViewById(R.id.btn_item_in_shop_equipable_description);
            equipableInListGoldCost = itemView.findViewById(R.id.btn_item_in_shop_equipable_goldCost);
            equipableInListImage = itemView.findViewById(R.id.imbtn_item_in_shop_equipable_image);
            backButton = itemView.findViewById(R.id.btn_dialog_equipable_in_shop_back);
            btnBuyItem = itemView.findViewById(R.id.btn_dialog_equipable_in_shop_buy_item);
            itemViewBuyButton = itemView.findViewById(R.id.itemView);


            int autoSizeMinTextSize = 6;
            int autoSizeMaxTextSize = 30;
            int autoSizeStepGranularity = 1;
            int unit = TypedValue.COMPLEX_UNIT_SP;

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    equipableInListDescription, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    equipableInListGoldCost, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);


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

    public ShopAdapter(ArrayList<Equipable> equipablesArrayList, ShopActivity shopActivity) {
        this.equipablesArrayList = equipablesArrayList;
        this.shopActivity = shopActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipable_in_shop, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Equipable equipable = equipablesArrayList.get(position);

        holder.btnBuyItem.setOnClickListener(v -> {

            if (buyClickListener == null) {
                System.out.println("buyClickListener is Null");
            }


            if (buyClickListener != null) {
                System.out.println("buyClickListener is not Null");
                buyClickListener.onBuyClick(equipable);
            }
        });

        holder.itemViewBuyButton.setOnClickListener(v -> {

            if (buyClickListener == null) {
                System.out.println("buyClickListener is Null");
            }


            if (buyClickListener != null) {
                System.out.println("buyClickListener is not Null");
                buyClickListener.onBuyClick(equipable);
            }
        });

        
        holder.equipableInListDescription.setText(equipable.toString());
        holder.equipableInListGoldCost.setText("Gold Cost: \n" + equipable.getGoldPurchaseCost());
        holder.equipableInListImage.setImageResource(equipable.getImageId());

    }

    public interface OnItemClickListener {
        void onItemClick(Equipable equipable);
    }

    public interface OnBuyClickListener {
        void onBuyClick(Equipable equipable);
    }

    public void setOnEquipableClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnBuyClickListener(OnBuyClickListener buyClickListener) {
        this.buyClickListener = buyClickListener;
    }

    @Override
    public int getItemCount() {
        return equipablesArrayList.size();
    }
}
