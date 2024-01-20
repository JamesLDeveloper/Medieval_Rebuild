package com.example.medievalrebuild;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

import com.example.medievalrebuild.Enemies.BossEnemy;
import com.example.medievalrebuild.Enemies.Enemy;
import com.example.medievalrebuild.Equipable.Equipable;
import com.example.medievalrebuild.Game.Art;
import com.example.medievalrebuild.Game.Player;

import java.io.Serializable;
import java.util.ArrayList;

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

    ArrayList<Equipable> shopItemsForPurchase = new ArrayList<>();


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
        int autoSizeMaxTextSize = 30;
        int autoSizeStepGranularity = 1;
        int unit = TypedValue.COMPLEX_UNIT_SP;





        shopImage = findViewById(R.id.iv_shop_shop_image);

        shopName = findViewById(R.id.tv_shop_shop_name);

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

        if (getIntent().hasExtra("shopInventory")){
            shopItemsForPurchase = (ArrayList<Equipable>) getIntent().getSerializableExtra("shopInventory");

            System.out.println(shopItemsForPurchase);



        } else {
            Log.e("shopInventory", "shopInventory object not found in Intent");
        }

        if (getIntent().hasExtra("shopName")){
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

}
