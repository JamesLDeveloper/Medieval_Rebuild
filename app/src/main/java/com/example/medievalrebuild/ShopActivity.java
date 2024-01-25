package com.example.medievalrebuild;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medievalrebuild.Equipable.Equipable;
import com.example.medievalrebuild.Game.Player;
import com.example.medievalrebuild.HandHeldItems.Shield;
import com.example.medievalrebuild.HandHeldItems.Weapon;
import com.example.medievalrebuild.Item.UpgradeItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ShopActivity extends AppCompatActivity implements Serializable {


    ImageView shopImage;

//    TextView shopStats;

    TextView shopName;

    TextView characterHealth;

    TextView characterGold;

    TextView characterStrength;

    TextView characterAccuracy;

    TextView characterReactions;

    TextView characterSpeed;

    TextView characterIntelligence;

    Button shopHandOneDescription;

    ImageButton shopHandOneImage;

    Button shopHandTwoDescription;

    ImageButton shopHandTwoImage;

    Button shopHelmetDescription;

    ImageButton shopHelmetImage;

    Button shopChestArmourDescription;

    ImageButton shopChestArmourImage;



    Button shopShirtDescription;

    ImageButton shopShirtImage;

    Button shopTrousersDescription;

    ImageButton shopTrousersImage;

    Button shopShoesDescription;

    ImageButton shopShoesImage;

//    TextView characterLevelName;

//    TextView characterLevelDescription;



    Button switchToStory;

    Equipable equipable;

    ArrayList<Equipable> shopEquipableItemsForPurchase = new ArrayList<>();


    private static final long serialVersionUID = 1L;

    /* Instance Variables */

    private transient MainActivity mainActivity;

//    private transient CharacterActivity characterActivity;

    private transient ShopActivity shopActivity;
    private Player player;

//    private Enemy enemy;

//    private Enemy currentEnemy;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.medieval_marvels_and_might_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_shop);

        int autoSizeMinTextSize = 6;
        int autoSizeMaxTextSize = 14;
        int autoSizeStepGranularity = 1;
        int unit = TypedValue.COMPLEX_UNIT_SP;


        shopImage = findViewById(R.id.iv_shop_shop_image);

        shopName = findViewById(R.id.tv_shop_shop_name);


        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                shopName, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);


//        shopHealth = findViewById(R.id.tv_shop_health);
//
//        shopGold = findViewById(R.id.tv_shop_gold);
//
//        shopStrength = findViewById(R.id.tv_shop_strength);
//
//        shopAccuracy = findViewById(R.id.tv_shop_accuracy);
//
//        shopReactions = findViewById(R.id.tv_shop_reactions);
//
//        shopSpeed = findViewById(R.id.tv_shop_speed);
//
//        shopIntelligence = findViewById(R.id.tv_character_intelligence);

        shopHandOneDescription = findViewById(R.id.btn_shop_hand_item1_name);

        shopHandOneImage = findViewById(R.id.imbtn_shop_hand_item1_image);

        shopHandTwoDescription = findViewById(R.id.btn_shop_hand_item2_name);

        shopHandTwoImage = findViewById(R.id.imbtn_shop_hand_item2_image);

        shopChestArmourDescription = findViewById(R.id.btn_shop_torso_armour_item_name);

        shopChestArmourImage = findViewById(R.id.imbtn_shop_torso_armour_item_image);

        shopHelmetDescription = findViewById(R.id.btn_shop_head_item_name);

        shopHelmetImage = findViewById(R.id.imbtn_shop_head_item_image);

        shopShirtDescription = findViewById(R.id.btn_shop_torso_underArmour_item_name);

        shopShirtImage = findViewById(R.id.imbtn_shop_torso_underArmour_item_image);

        shopTrousersDescription = findViewById(R.id.btn_shop_legs_item_name);

        shopTrousersImage = findViewById(R.id.imbtn_shop_legs_item_image);

        shopShoesDescription = findViewById(R.id.btn_shop_feet_item_name);

        shopShoesImage = findViewById(R.id.imbtn_shop_feet_item_image);

//        characterLevelName = findViewById(R.id.tv_character_level_name);
//
//        characterLevelDescription = findViewById(R.id.tv_character_level_description);


        switchToStory = findViewById(R.id.btn_shop_story);
//        switchToCharacter= findViewById(R.id.btn_enemy_character);


        if (getIntent().hasExtra("player")) {
            player = (Player) getIntent().getSerializableExtra("player");


            shopImage.setImageResource(R.drawable.shop1);

            shopHandOneDescription.setText("Hand One");
            shopHandOneImage.setImageResource(R.drawable.right_hand);
            shopHandTwoDescription.setText("Hand Two");
            shopHandTwoImage.setImageResource(R.drawable.left_hand);
            shopHelmetDescription.setText("Head");
            shopHelmetImage.setImageResource(R.drawable.head);
            shopChestArmourDescription.setText("Torso Armour");
            shopChestArmourImage.setImageResource(R.drawable.material_armour_torso_metal);
            shopShirtDescription.setText("Torso Under Armour");
            shopShirtImage.setImageResource(R.drawable.material_torso_underarmour);
            shopTrousersDescription.setText("Legs");
            shopTrousersImage.setImageResource(R.drawable.legs);
            shopShoesDescription.setText("Feet");
            shopShoesImage.setImageResource(R.drawable.feet);

            mainActivity = MyApplication.getMainActivityInstance();

        } else {
            Log.e("player", "Player object not found in Intent");
        }

        if (getIntent().hasExtra("shopInventory")) {
            shopEquipableItemsForPurchase = (ArrayList<Equipable>) getIntent().getSerializableExtra("shopInventory");

            System.out.println(shopEquipableItemsForPurchase);


        } else {
            Log.e("shopInventory", "shopInventory object not found in Intent");
        }

        if (getIntent().hasExtra("shopName")) {
            String retrievedShopName = (String) getIntent().getSerializableExtra("shopName");

            shopName.setText(retrievedShopName);
        } else {
            Log.e("shopName", "shopName object not found in Intent");
        }


//        switchToStory = findViewById(R.id.btn_character_story);

        //   Log.d("MainActivity", "mainTextView: " + mainTextView);


//        userTextInput = findViewById(R.id.ett_main_user_text);
        //       TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
        //               userTextInput, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);


        // TODO 4-E: set onClickListener for each answer Button


        shopHandOneDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEquipablesDialog(shopEquipableItemsForPurchase);
            }
        });

        switchToStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(CharacterActivity.this, MainActivity.class);
                //startActivity(intent);
                finish();
                mainActivity = MyApplication.getMainActivityInstance();


                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

            }

        });
    }



        private void showEquipablesDialog(ArrayList<Equipable> equipables) {

                AlertDialog.Builder chooseItemFromEquipablesBuilder = new AlertDialog.Builder(ShopActivity.this);

                        View dialogView = getLayoutInflater().inflate(R.layout.dialog_equipable_in_shop, null);
                chooseItemFromEquipablesBuilder.setView(dialogView);

            RecyclerView recyclerView = dialogView.findViewById(R.id.recyclerViewHandOne);

            ArrayList<Equipable> generatedHandOneEquipables = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));




                        for (Equipable equipable: shopEquipableItemsForPurchase) {

                            if (equipable instanceof Shield || equipable instanceof Weapon) {
                            generatedHandOneEquipables.add(equipable);
                            }

                        }


                        ArrayList<Equipable> handOneEquipablesList = generatedHandOneEquipables;

                        HandOneAdapter handOneAdapter = new HandOneAdapter(handOneEquipablesList);
                        recyclerView.setAdapter(handOneAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ShopActivity.this));

                        AlertDialog alertDialog = chooseItemFromEquipablesBuilder.create();
                        alertDialog.show();
                    }
























//public void chooseItemFromShop(){
//
//        }


//    public void confirmPurchase() {
//
//        }













/*
        switchToCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                if (mainActivity != null) {
                    mainActivity.switchToCharacterStats();
                  //  finish();
                } else {
                    Log.e("CharacterActivity", "MainActivity is null");
                }

//                finish();

            }

        });

*/



    }


