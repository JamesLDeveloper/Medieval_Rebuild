
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

public class EquipableAdapter extends RecyclerView.Adapter<EquipableAdapter.ViewHolder> {
    private ArrayList<Equipable> equipablesArrayList;
    private OnItemClickListener listener;
    private OnEquipClickListener equipClickListener;

//    private ShopActivity shopActivity;

    private MainActivity mainActivity;

//    private Button backButton;
//    private Button btnBuyItem;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout btnEquipItem;
        Button equipableInListDescription;
        Button equipableInListGoldValue;
        ImageButton equipableInListImage;
        Button backButton;
        EquipableAdapter adapter;

        LinearLayout itemViewEquipButton;


        public ViewHolder(@NonNull View itemView, EquipableAdapter adapter) {
            super(itemView);



            equipableInListDescription = itemView.findViewById(R.id.btn_equipable_in_player_inventory_description);
            equipableInListGoldValue = itemView.findViewById(R.id.btn_equipable_in_player_inventory_gold_value);
            equipableInListImage = itemView.findViewById(R.id.imbtn_equipable_in_player_inventory_image);
            backButton = itemView.findViewById(R.id.btn_dialog_equipable_in_player_inventory_back);
            btnEquipItem = itemView.findViewById(R.id.btn_equipable_in_player_inventory_equip);
            itemViewEquipButton = itemView.findViewById(R.id.itemView);


            int autoSizeMinTextSize = 6;
            int autoSizeMaxTextSize = 30;
            int autoSizeStepGranularity = 1;
            int unit = TypedValue.COMPLEX_UNIT_SP;

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    equipableInListDescription, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                    equipableInListGoldValue, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);


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

    public EquipableAdapter(ArrayList<Equipable> equipablesArrayList, MainActivity mainActivity) {
        this.equipablesArrayList = equipablesArrayList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipable_in_player_inventory, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Equipable equipable = equipablesArrayList.get(position);

        holder.btnEquipItem.setOnClickListener(v -> {

            if (equipClickListener == null) {
                System.out.println("buyClickListener is Null");
            }


            if (equipClickListener != null) {
                System.out.println("buyClickListener is not Null");
                equipClickListener.onEquipClick(equipable);
            }
        });

        holder.itemViewEquipButton.setOnClickListener(v -> {

            if (equipClickListener == null) {
                System.out.println("buyClickListener is Null");
            }


            if (equipClickListener != null) {
                System.out.println("buyClickListener is not Null");
                equipClickListener.onEquipClick(equipable);
            }
        });


        holder.equipableInListDescription.setText(equipable.toString());
        holder.equipableInListGoldValue.setText("Gold Cost: \n" + equipable.getGoldPurchaseCost());
        holder.equipableInListImage.setImageResource(equipable.getImageId());

    }

    public interface OnItemClickListener {
        void onItemClick(Equipable equipable);
    }

    public interface OnEquipClickListener {
        void onEquipClick(Equipable equipable);
    }

    public void setOnEquipableClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnEquipClickListener(OnEquipClickListener equipClickListener) {
        this.equipClickListener = equipClickListener;
    }

    @Override
    public int getItemCount() {
        return equipablesArrayList.size();
    }
}
