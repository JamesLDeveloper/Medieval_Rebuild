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

    Button shopHandOneSell;

    Button shopHandTwoDescription;

    ImageButton shopHandTwoImage;

    Button shopHandTwoSell;

    Button shopHelmetDescription;

    ImageButton shopHelmetImage;

    Button shopHelmetSell;

    Button shopChestArmourDescription;

    ImageButton shopChestArmourImage;

//    Button backButton;

Button shopChestArmourSell;

    Button shopShirtDescription;

    ImageButton shopShirtImage;

    Button shopShirtSell;

    Button shopTrousersDescription;

    ImageButton shopTrousersImage;

    Button shopTrousersSell;

    Button shopShoesDescription;

    ImageButton shopShoesImage;

    Button shopShoesSell;

//    TextView characterLevelName;

//    TextView characterLevelDescription;

RecyclerView recyclerView;

    Button switchToStory;

    Equipable equipable;

    ArrayList<Equipable> shopEquipableItemsForPurchase = new ArrayList<>();

    ArrayList<Equipable> playerEquipablesInInventory = new ArrayList<>();

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

            ShopAdapterSell shopAdapterSell = new ShopAdapterSell(equipables, ShopActivity.this);

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

        shopHandOneSell = findViewById(R.id.btn_shop_sell_hand_item1);

        shopHandTwoDescription = findViewById(R.id.btn_shop_hand_item2_name);

        shopHandTwoImage = findViewById(R.id.imbtn_shop_hand_item2_image);

        shopHandTwoSell = findViewById(R.id.btn_shop_sell_hand_item2);

        shopChestArmourDescription = findViewById(R.id.btn_shop_torso_armour_item_name);

        shopChestArmourImage = findViewById(R.id.imbtn_shop_torso_armour_item_image);

        shopChestArmourSell = findViewById(R.id.btn_shop_sell_torso_armour_item);

        shopHelmetDescription = findViewById(R.id.btn_shop_head_item_name);

        shopHelmetImage = findViewById(R.id.imbtn_shop_head_item_image);

        shopHelmetSell = findViewById(R.id.btn_shop_sell_head_item);

        shopShirtDescription = findViewById(R.id.btn_shop_torso_underArmour_item_name);

        shopShirtImage = findViewById(R.id.imbtn_shop_torso_underArmour_item_image);

        shopShirtSell = findViewById(R.id.btn_shop_sell_torso_underArmour_item);

        shopTrousersDescription = findViewById(R.id.btn_shop_legs_item_name);

        shopTrousersImage = findViewById(R.id.imbtn_shop_legs_item_image);

        shopTrousersSell = findViewById(R.id.btn_shop_sell_legs_item);

        shopShoesDescription = findViewById(R.id.btn_shop_feet_item_name);

        shopShoesImage = findViewById(R.id.imbtn_shop_feet_item_image);

        shopShoesSell = findViewById(R.id.btn_shop_sell_feet_item);

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
            shopHandOneSell.setText("Sell");
            shopHandTwoDescription.setText("Hand Two");
            shopHandTwoImage.setImageResource(R.drawable.left_hand);
            shopHandTwoSell.setText("Sell");
            shopHelmetDescription.setText("Head");
            shopHelmetImage.setImageResource(R.drawable.head);
            shopHelmetSell.setText("Sell");
            shopChestArmourDescription.setText("Torso Armour");
            shopChestArmourImage.setImageResource(R.drawable.material_armour_torso_metal);
            shopChestArmourSell.setText("Sell");
            shopShirtDescription.setText("Torso Under Armour");
            shopShirtImage.setImageResource(R.drawable.material_torso_underarmour);
            shopShirtSell.setText("Sell");
            shopTrousersDescription.setText("Legs");
            shopTrousersImage.setImageResource(R.drawable.legs);
            shopTrousersSell.setText("Sell");
            shopShoesDescription.setText("Feet");
            shopShoesImage.setImageResource(R.drawable.feet);
            shopShoesSell.setText("Sell");

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

        if (getIntent().hasExtra("playerEquipablesInventory")) {
            playerEquipablesInInventory = (ArrayList<Equipable>) getIntent().getSerializableExtra("playerEquipablesInventory");


            shopActivityViewModel.setEquipablesLiveData(playerEquipablesInInventory);

            System.out.println(playerEquipablesInInventory);


        } else {
            Log.e("playerEquipablesInventory", "playerEquipablesInventory object not found in Intent");
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

                if(shopEquipableItemsForPurchase != null) {

                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Hand");
                }
            }
        });

        shopHandOneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Hand");
                }
            }
        });

        shopHandOneSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesToSellDialog(playerEquipablesInInventory, "Hand");
                }
            }
        });


        shopHandTwoDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Hand");
                }
            }
        });

        shopHandTwoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Hand");
                }
            }
        });

        shopHandTwoSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesToSellDialog(playerEquipablesInInventory, "Hand");
                }
            }
        });


        shopHelmetDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Helmet");
                }
            }
        });

        shopHelmetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Helmet");
                }
            }
        });

        shopHelmetSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesToSellDialog(playerEquipablesInInventory, "Helmet");
                }
            }
        });

        shopChestArmourDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "ChestArmour");
                }
            }
        });

        shopChestArmourImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "ChestArmour");
                }
            }
        });

        shopChestArmourSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesToSellDialog(playerEquipablesInInventory, "ChestArmour");
                }
            }
        });


        shopShirtDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Shirt");
                }
            }
        });

        shopShirtImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Shirt");
                }
            }
        });

        shopShirtSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesToSellDialog(playerEquipablesInInventory, "Shirt");
                }
            }
        });


        shopTrousersDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Trousers");
                }
            }
        });

        shopTrousersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Trousers");
                }
            }
        });

        shopTrousersSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesToSellDialog(playerEquipablesInInventory, "Trousers");
                }
            }
        });


        shopShoesDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Shoes");
                }
            }
        });

        shopShoesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesDialog(shopEquipableItemsForPurchase, "Shoes");
                }
            }
        });

        shopShoesSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopEquipableItemsForPurchase != null) {
                    showHandEquipablesToSellDialog(playerEquipablesInInventory, "Shoes");
                }
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



    private void showHandEquipablesToSellDialog(ArrayList<Equipable> equipables, String slotType) {

        AlertDialog.Builder chooseItemToSellBuilder = new AlertDialog.Builder(ShopActivity.this);

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_equipable_to_sell, null);
        chooseItemToSellBuilder.setView(dialogView);

//            RecyclerView recyclerView = dialogView.findViewById(R.id.recyclerViewHandOne);

        RecyclerView recyclerView = dialogView.findViewById(R.id.btn_dialog_equipable_to_sell_sell_item);

//backButton.findViewById(R.id.btn_dialog_equipable_in_shop_back);

        ArrayList<Equipable> generatedEquipablesForSlotTypeToSell = new ArrayList<>();

        ArrayList<Equipable> playerEquipablesToSell = new ArrayList<>();

        playerEquipablesToSell = mainActivity.getPlayerEquipablesList();


        if (slotType.equalsIgnoreCase("Hand")) {


            ArrayList<Equipable> generatedHandOneEquipablesToSell = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


            for (Equipable equipable : playerEquipablesToSell) {

                if (equipable instanceof Shield || equipable instanceof Weapon) {
                    generatedHandOneEquipablesToSell.add(equipable);
                }

            }

            generatedEquipablesForSlotTypeToSell = generatedHandOneEquipablesToSell;
        } else if (slotType.equalsIgnoreCase("Helmet")) {


            ArrayList<Equipable> generatedHelmetEquipablesToSell = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


            for (Equipable equipable : playerEquipablesToSell) {

                if (equipable instanceof Helmet) {
                    generatedHelmetEquipablesToSell.add(equipable);
                }

            }

            generatedEquipablesForSlotTypeToSell = generatedHelmetEquipablesToSell;
        } else if (slotType.equalsIgnoreCase("ChestArmour")) {


            ArrayList<Equipable> generatedChestArmourEquipablesToSell = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


            for (Equipable equipable : playerEquipablesToSell) {

                if (equipable instanceof ChestArmour) {
                    generatedChestArmourEquipablesToSell.add(equipable);
                }

            }

            generatedEquipablesForSlotTypeToSell = generatedChestArmourEquipablesToSell;
        } else if (slotType.equalsIgnoreCase("Trousers")) {


            ArrayList<Equipable> generatedTrouserEquipablesToSell = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


            for (Equipable equipable : playerEquipablesToSell) {

                if (equipable instanceof Trouser) {
                    generatedTrouserEquipablesToSell.add(equipable);
                }

            }

            generatedEquipablesForSlotTypeToSell = generatedTrouserEquipablesToSell;
        } else if (slotType.equalsIgnoreCase("Shirt")) {


            ArrayList<Equipable> generatedShirtEquipablesToSell = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


            for (Equipable equipable : playerEquipablesToSell) {

                if (equipable instanceof Shirt) {
                    generatedShirtEquipablesToSell.add(equipable);
                }

            }

            generatedEquipablesForSlotTypeToSell = generatedShirtEquipablesToSell;

        } else if (slotType.equalsIgnoreCase("Shoes")) {


            ArrayList<Equipable> generatedShoeEquipablesToSell = new ArrayList<>();

//            ArrayList<Equipable> handOneEquipablesList = equipables.stream()
//                    .filter(equipable -> equipable instanceof Shield || equipable instanceof Weapon)
//                    .collect(Collectors.toCollection(ArrayList::new));


            for (Equipable equipable : playerEquipablesToSell) {

                if (equipable instanceof Shoe) {
                    generatedShoeEquipablesToSell.add(equipable);
                }

            }

            generatedEquipablesForSlotTypeToSell = generatedShoeEquipablesToSell;

        }


        //                       ArrayList<Equipable> handOneEquipablesList = generatedHandOneEquipables;

        //HandOneAdapter handOneAdapter = new HandOneAdapter(handOneEquipablesList);
        //recyclerView.setAdapter(handOneAdapter);

        ShopAdapterSell shopAdapterSell = new ShopAdapterSell(generatedEquipablesForSlotTypeToSell, ShopActivity.this);
        recyclerView.setAdapter(shopAdapterSell);

        recyclerView.setLayoutManager(new LinearLayoutManager(ShopActivity.this));

        AlertDialog alertDialog = chooseItemToSellBuilder.create();
        alertDialog.show();

        Button backButton = dialogView.findViewById(R.id.btn_dialog_equipable_to_sell_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        shopAdapterSell.setOnSellClickListener(new ShopAdapterSell.OnSellClickListener() {

            @Override
            public void onSellClick(Equipable equipable) {
                System.out.println("You have chosen an equipable.");
                showSellConfirmationDialog(equipable);
                alertDialog.dismiss();
            }
        });

    }



    protected void showSellConfirmationDialog(Equipable equipable) {

        System.out.println("show sell confirmation dialog called");

        AlertDialog.Builder sellConfirmationDialogBuilder = new AlertDialog.Builder(ShopActivity.this);

        sellConfirmationDialogBuilder.setTitle("Confirm Item to Sell");
        sellConfirmationDialogBuilder.setMessage("Do you want to sell " + equipable.toString() + " for " + equipable.getGoldSellValue() + " gold?");

        sellConfirmationDialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
            // Check if the player has enough gold
                // Add the equipable to the player's list

            mainActivity.getPlayer().addPlayerGold(equipable.getGoldSellValue());

                mainActivity.getPlayerEquipablesList().remove(equipable);

                // Notify the ViewModel about the changes in equipables
                shopActivityViewModel.setEquipablesLiveData(mainActivity.getPlayerEquipablesList());

        });

        sellConfirmationDialogBuilder.setNegativeButton("No", (dialog, which) -> {
            // User chose not to purchase
            dialog.dismiss();
        });

        sellConfirmationDialogBuilder.show();
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