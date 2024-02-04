package com.example.medievalrebuild;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medievalrebuild.ArmourFiles.ChestArmour;
import com.example.medievalrebuild.ArmourFiles.Helmet;
import com.example.medievalrebuild.ArmourFiles.Shirt;
import com.example.medievalrebuild.ArmourFiles.Shoe;
import com.example.medievalrebuild.ArmourFiles.Trouser;
import com.example.medievalrebuild.Equipable.Equipable;
import com.example.medievalrebuild.Game.Player;
import com.example.medievalrebuild.HandHeldItems.Shield;
import com.example.medievalrebuild.HandHeldItems.Weapon;
import com.example.medievalrebuild.Item.UpgradeItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ShopActivity extends AppCompatActivity implements Serializable {

    private ShopActivityViewModel shopActivityViewModel;


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

//    Button backButton;



    Button shopShirtDescription;

    ImageButton shopShirtImage;

    Button shopTrousersDescription;

    ImageButton shopTrousersImage;

    Button shopShoesDescription;

    ImageButton shopShoesImage;

//    TextView characterLevelName;

//    TextView characterLevelDescription;

RecyclerView recyclerView;

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


        shopActivityViewModel = new ViewModelProvider(this).get(ShopActivityViewModel.class);

        shopActivityViewModel.getEquipablesLiveData().observe(this, equipables -> {

            ShopAdapter shopAdapter = new ShopAdapter(equipables, ShopActivity.this);
//            recyclerView.setAdapter(shopAdapter);


        });

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

//        backButton.findViewById(R.id.btn_dialog_equipable_in_shop_back);

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


            shopActivityViewModel.setEquipablesLiveData(shopEquipableItemsForPurchase);

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
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Hand");
            }
        });

        shopHandOneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Hand");
            }
        });

        shopHandTwoDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Hand");
            }
        });

        shopHandTwoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Hand");
            }
        });



        shopHelmetDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Helmet");
            }
        });

        shopHelmetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Helmet");
            }
        });

        shopChestArmourDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "ChestArmour");
            }
        });

        shopChestArmourImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "ChestArmour");
            }
        });


        shopShirtDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Shirt");
            }
        });

        shopShirtImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Shirt");
            }
        });


        shopTrousersDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Trousers");
            }
        });

        shopTrousersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Trousers");
            }
        });


        shopShoesDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Shoes");
            }
        });

        shopShoesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHandEquipablesDialog(shopEquipableItemsForPurchase, "Shoes");
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



        private void showHandEquipablesDialog(ArrayList<Equipable> equipables, String slotType) {

                AlertDialog.Builder chooseItemFromEquipablesBuilder = new AlertDialog.Builder(ShopActivity.this);

                        View dialogView = getLayoutInflater().inflate(R.layout.dialog_equipable_in_shop, null);
                chooseItemFromEquipablesBuilder.setView(dialogView);

//            RecyclerView recyclerView = dialogView.findViewById(R.id.recyclerViewHandOne);

            RecyclerView recyclerView = dialogView.findViewById(R.id.btn_dialog_equipable_in_shop_buy_item);

//backButton.findViewById(R.id.btn_dialog_equipable_in_shop_back);

            ArrayList<Equipable> generatedEquipablesForSlotType = new ArrayList<>();



            if (slotType.equalsIgnoreCase("Hand")) {


                ArrayList<Equipable> generatedHandOneEquipables = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


                for (Equipable equipable : shopEquipableItemsForPurchase) {

                    if (equipable instanceof Shield || equipable instanceof Weapon) {
                        generatedHandOneEquipables.add(equipable);
                    }

                }

                generatedEquipablesForSlotType = generatedHandOneEquipables;
            } else if (slotType.equalsIgnoreCase("Helmet")) {


                ArrayList<Equipable> generatedHelmetEquipables = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


                for (Equipable equipable : shopEquipableItemsForPurchase) {

                    if (equipable instanceof Helmet) {
                        generatedHelmetEquipables.add(equipable);
                    }

                }

                generatedEquipablesForSlotType = generatedHelmetEquipables;
            } else if (slotType.equalsIgnoreCase("ChestArmour")) {


                ArrayList<Equipable> generatedChestArmourEquipables = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


                for (Equipable equipable : shopEquipableItemsForPurchase) {

                    if (equipable instanceof ChestArmour) {
                        generatedChestArmourEquipables.add(equipable);
                    }

                }

                generatedEquipablesForSlotType = generatedChestArmourEquipables;
            } else if (slotType.equalsIgnoreCase("Trousers")) {


                ArrayList<Equipable> generatedTrouserEquipables = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


                for (Equipable equipable : shopEquipableItemsForPurchase) {

                    if (equipable instanceof Trouser) {
                        generatedTrouserEquipables.add(equipable);
                    }

                }

                generatedEquipablesForSlotType = generatedTrouserEquipables;
            } else if (slotType.equalsIgnoreCase("Shirt")) {


                ArrayList<Equipable> generatedShirtEquipables = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


                for (Equipable equipable : shopEquipableItemsForPurchase) {

                    if (equipable instanceof Shirt) {
                        generatedShirtEquipables.add(equipable);
                    }

                }

                generatedEquipablesForSlotType = generatedShirtEquipables;

            } else if (slotType.equalsIgnoreCase("Shoes")) {


                ArrayList<Equipable> generatedShoeEquipables = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


                for (Equipable equipable : shopEquipableItemsForPurchase) {

                    if (equipable instanceof Shoe) {
                        generatedShoeEquipables.add(equipable);
                    }

                }

                generatedEquipablesForSlotType = generatedShoeEquipables;

            }


 //                       ArrayList<Equipable> handOneEquipablesList = generatedHandOneEquipables;

                        //HandOneAdapter handOneAdapter = new HandOneAdapter(handOneEquipablesList);
                        //recyclerView.setAdapter(handOneAdapter);

                        ShopAdapter shopAdapter = new ShopAdapter(generatedEquipablesForSlotType, ShopActivity.this);
                        recyclerView.setAdapter(shopAdapter);

                        recyclerView.setLayoutManager(new LinearLayoutManager(ShopActivity.this));

                        AlertDialog alertDialog = chooseItemFromEquipablesBuilder.create();
                        alertDialog.show();

            Button backButton = dialogView.findViewById(R.id.btn_dialog_equipable_in_shop_back);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

                        shopAdapter.setOnBuyClickListener(new ShopAdapter.OnBuyClickListener() {

                            @Override
                            public void onBuyClick(Equipable equipable) {
                                System.out.println("You have chosen an equipable.");
                                showPurchaseConfirmationDialog(equipable);
                                alertDialog.dismiss();
                            }
                        });


                    }

    protected void showPurchaseConfirmationDialog(Equipable equipable) {

System.out.println("show purchase confirmation dialog called");

        AlertDialog.Builder confirmationDialogBuilder = new AlertDialog.Builder(ShopActivity.this);

        confirmationDialogBuilder.setTitle("Confirm Purchase");
        confirmationDialogBuilder.setMessage("Do you want to purchase " + equipable.toString() + " for " + equipable.getGoldPurchaseCost() + " gold?");

        confirmationDialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
            // Check if the player has enough gold
            if (mainActivity.getPlayer().getPlayerGold() >= equipable.getGoldPurchaseCost()) {
                // Deduct gold from the player
                mainActivity.getPlayer().subtractGold(equipable.getGoldPurchaseCost());

                // Add the equipable to the player's list
                mainActivity.addEquipableToEquipableList(equipable);

                // Remove the equipable from the shop's list
                shopEquipableItemsForPurchase.remove(equipable);

                // Notify the ViewModel about the changes in equipables
                shopActivityViewModel.setEquipablesLiveData(shopEquipableItemsForPurchase);
            } else {
                // Player doesn't have enough gold, show a message
                showInsufficientGoldDialog();
            }
        });

        confirmationDialogBuilder.setNegativeButton("No", (dialog, which) -> {
            // User chose not to purchase
            dialog.dismiss();
        });

        confirmationDialogBuilder.show();
    }

    private void showInsufficientGoldDialog() {
        AlertDialog.Builder insufficientGoldDialogBuilder = new AlertDialog.Builder(ShopActivity.this);
        insufficientGoldDialogBuilder.setTitle("Insufficient Gold");
        insufficientGoldDialogBuilder.setMessage("You don't have enough gold to purchase this item.");

        insufficientGoldDialogBuilder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
        });

        insufficientGoldDialogBuilder.show();
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