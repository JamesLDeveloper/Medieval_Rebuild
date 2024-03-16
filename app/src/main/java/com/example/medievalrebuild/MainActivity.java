package com.example.medievalrebuild;

import android.app.AlertDialog;

import android.content.DialogInterface;

import android.os.Bundle;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.widget.TextViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

import com.example.medievalrebuild.ArmourFiles.ChestArmour;
import com.example.medievalrebuild.ArmourFiles.Helmet;
import com.example.medievalrebuild.ArmourFiles.Shirt;
import com.example.medievalrebuild.ArmourFiles.Shoe;
import com.example.medievalrebuild.ArmourFiles.Trouser;
import com.example.medievalrebuild.Enemies.BossEnemy;
import com.example.medievalrebuild.Enemies.Enemy;
import com.example.medievalrebuild.Equipable.Equipable;
import com.example.medievalrebuild.Game.Art;
import com.example.medievalrebuild.Game.Player;
import com.example.medievalrebuild.HandHeldItems.Shield;
import com.example.medievalrebuild.Item.Item;
import com.example.medievalrebuild.Item.UpgradeItem;
import com.example.medievalrebuild.HandHeldItems.Weapon;
import java.io.Serializable;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.SubmissionPublisher;

public class MainActivity extends AppCompatActivity implements Serializable, MyAlertDialog.DialogCallBack {

    private MainActivityViewModel mainViewModel;

    Art art;

    private Handler handler = new Handler();

    String storySoFar;

    String storyUpdate;

    boolean waitingToProceed = true;

    RecyclerView recyclerView;


    ImageView mainImageView;

    TextView mainTextView;

    String mainTextViewText = "";

    TextView previousStageTextView;

    String previousStageTextViewText = "";

    TextView previousPreviousStageTextView;

    String previousPreviousStageTextViewText = "";

private boolean advanceText = false;

private boolean advanceCountShouldIncrease = false;

    Button userResponse0Button;

    Button userResponse1Button;

//    Button nextButton;

    private int advanceCount = 0;

    Button userResponse2Button; // save button

    Button userResponse3Button; // load button

    Button userExitButton; // has been turned into a delete button instead

    Button userSubmitButton;

    Button useItemButton;

    Button equipEquipableButton;

    Button switchToCharacter;

    Button switchToEnemy;

    Button switchToLastShop;




    ScrollView mainTextScrollView;

    ScrollView storySoFarScrollView;

    private Context context;

//    private int time;

    private boolean waitingForAnswer = true;

    CharacterActivity characterActivity;

    EnemyActivity enemyActivity;

    MainActivity mainActivity;

//    Button switchToStory;

//    EditText userTextInput;

    int userChoice = -1;


    private static final long serialVersionUID = 1L;

    /* Instance Variables */
    private Player player;

//    private Game.Player player;

    private Enemy currentEnemy;

    private Enemy enemy;

    private UpgradeItem upgradeItem;

//    private String progress;

    private int chest;
    private int chestTwo;

    private int chestThree;

    private int doorOne;
    private int zombie;

    private int enemy2;

    private int enemy3;

    private int enemy4;

    private int enemy5;

//    private int zombieHealth = 4;
//    private int zombieDamage = 2;

//    private String userTextInputCollected;

    private boolean validAnswer = false;

    private String newOrLoadSelected;

    private String loadGameSelected;

    final String[] options = {"New Game", "Load"};

    UpgradeItem potion = new UpgradeItem("Heal Potion", 0,0,0, 40, 0, 0, false);

    UpgradeItem brokenSceptre = new UpgradeItem("Broken Sceptre", 0, 0,0,0,0,0, true);

    Enemy zombieOne = new Enemy("Zombie", 20, 2, true, R.drawable.workzombie);
    Enemy zombieKing = new Enemy("Zombie King", 100, 20, true, R.drawable.zombie_king_by_reddnekk_dc69pgp_fullview);

    BossEnemy loki = new BossEnemy("Loki God Of Mischief", 175, 25, true, R.drawable.loki_1556801363,2);


    Enemy werewolf = new Enemy("Werewolf", 75, 12, true, R.drawable.werewolves);

    BossEnemy morgana = new BossEnemy("Morgana", 250, 30, true, R.drawable.merlin1133,3);



    Weapon longSword = new Weapon("Long Sword", 12, 500, 100 , 1 , R.drawable.longsword);

    ArrayList<Enemy> enemiesStartingStats = new ArrayList<>();

    private Item itemSelected;

    ArrayList<Item> itemList = new ArrayList<>();

    ArrayList<Equipable> equipableArrayList = new ArrayList<>();

//    ArrayList<Enemy> enemiesVariableStats = new ArrayList<>();

    ArrayList<Enemy> copiedEnemiesStartingStats;


    ArrayList<Equipable> lastShopInventory = new ArrayList<>();

    String lastShopName;

    String userTextInput;


    //    Level1 level1;
    //private static String progress = "level1";
    boolean newGame = false;

    private Runnable delayedTask;

    private Runnable delayedMessage;

    private Handler handlerDelayTask = new Handler(Looper.getMainLooper());


    private Handler handlerDelayMessage = new Handler(Looper.getMainLooper());


    private Handler updateUIHandler = new Handler(Looper.getMainLooper());

    ArrayList<String> saveGamesAfterRestart = new ArrayList<>();



    private int currentLevelPart = 0;


    //MainActivity mainActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = new MainActivity();



        MyApplication.setMainActivityInstance(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.medieval_marvels_and_might_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        MainActivityHolder.setMainActivity(this);

        characterActivity = new CharacterActivity();
        characterActivity.setMainActivity(MainActivity.this);
        Log.d("CharacterActivity", "MainActivity: " + mainActivity);
        System.out.println("Character Activity MainActivity: " + mainActivity);


        enemyActivity = new EnemyActivity();
        enemyActivity.setMainActivity(MainActivity.this);
        Log.d("EnemyActivity", "MainActivity: " + mainActivity);
        System.out.println("Enemy Activity: MainActivity: " + mainActivity);

        context = this;

        int autoSizeMinTextSize = 8;
        int autoSizeMaxTextSize = 10;
        int autoSizeStepGranularity = 1;
        int unit = TypedValue.COMPLEX_UNIT_SP;

        mainImageView = findViewById(R.id.iv_main_image);

        mainTextScrollView = findViewById(R.id.sv_main_user_text_container);

        storySoFarScrollView = findViewById(R.id.sv_main_previous_stage_user_text_container);



//        previousPreviousStageTextView = findViewById(R.id.tv_previous_previous_stage_user_text);
        previousStageTextView = findViewById(R.id.tv_main_previous_stage_user_text);
        mainTextView = findViewById(R.id.tv_main_user_text);


        switchToCharacter = findViewById(R.id.btn_main_character);

        switchToEnemy = findViewById(R.id.btn_main_enemy);

//        nextButton = findViewById(R.id.btn_main_next);

//        switchToStory = findViewById(R.id.btn_character_story);

        //   Log.d("MainActivity", "mainTextView: " + mainTextView);

        userResponse0Button = findViewById(R.id.btn_main_0);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userResponse0Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userResponse1Button = findViewById(R.id.btn_main_1);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userResponse1Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

//        nextButton = findViewById(R.id.btn_main_next);
//        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
//                nextButton, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userResponse2Button = findViewById(R.id.btn_main_2);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userResponse2Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userResponse3Button = findViewById(R.id.btn_main_3);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userResponse3Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userExitButton = findViewById(R.id.btn_main_exit);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userExitButton, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);


        useItemButton = findViewById(R.id.btn_main_item);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                useItemButton, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        equipEquipableButton = findViewById(R.id.btn_main_equipable);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                equipEquipableButton, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userSubmitButton = findViewById(R.id.btn_main_submit);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userSubmitButton, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        switchToCharacter = findViewById(R.id.btn_main_character);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                switchToCharacter, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        switchToEnemy = findViewById(R.id.btn_main_enemy);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                switchToEnemy, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        switchToLastShop = findViewById(R.id.btn_main_shop);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                switchToLastShop, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);


        storySoFar = "";
        storyUpdate = "";
//        mainTextViewText = "";
//        previousStageTextViewText = "";
//        previousPreviousStageTextViewText = "";
//        setPreviousAndMainText("Welcome to Medieval Marvels!");

//        userTextInput = findViewById(R.id.ett_main_user_text);
        //       TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
        //               userTextInput, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);


        // TODO 4-E: set onClickListener for each answer Button

        userResponse0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption(0);

            }
        });

        userResponse1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption(1);
            }
        });

        userResponse2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         //       selectOption(2);
                save();
            }
        });


//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(validAnswer == true) {
//                    waitingForAnswer = false;
//                    System.out.println("Waiting for answer = false");
//                }
//            }
//        });

        userResponse3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // selectOption(3);
                load();
            }

        });


        // TODO 5-A: set onClickListener for the submit answer Button

        userSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //               if (userTextInput != null) {
                //                   typeText(String.valueOf(userTextInput));
                //               } else {
                if (validAnswer == true) {
                    onAnswerSubmission();
                }

                if(validAnswer == true) {
                    waitingForAnswer = false;
                    System.out.println("Waiting for answer = false");
                }

                if (advanceCountShouldIncrease == true) {
                    advanceCount += 1;
                }
                //               }
            }
        });

        useItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseItem();
            }
        });

        equipEquipableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseEquipable();
            }
        });



        userExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //               selectOption(4);
                delete();
            }

        });


        switchToCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToCharacterStats();

        //        mainActivity.setMainActivity(MainActivity.this);

            }

        });

        switchToEnemy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToEnemyStats();

            }

        });

        switchToLastShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lastShopInventory != null){

                }
                switchToShop(lastShopInventory, lastShopName, getPlayerEquipablesList());

            }

        });


        userResponse0Button.setText("Yes");
        userResponse1Button.setText("No");
        userResponse2Button.setText("Save");
        userResponse3Button.setText("Load");
        useItemButton.setText("Item");
        equipEquipableButton.setText("Equip");
        userExitButton.setText("Delete");
        userSubmitButton.setText("Next");


        mainImageView.setImageResource(R.drawable._880px_panor_mica_oto_o_alc_zar_de_segovia);

        File internalStorageDirForPreviousSaves = context.getFilesDir();
        File[] files = internalStorageDirForPreviousSaves.listFiles();

        if (files != null){
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".svr")) {
                        saveGamesAfterRestart.add(fileName.substring(0, fileName.length() -4));
                    }
                }
            }
        }




//        enemies = Enemy.getAllEnemies();
//
//        ArrayList<Enemy>enemiesStartingStatsNewList = new ArrayList<>();
//        Iterator<Enemy> iteratorStartingStats = enemies.iterator();
//        while (iteratorStartingStats.hasNext()) {
//            Enemy enemy = iteratorStartingStats.next();
//            enemiesStartingStatsNewList.add(new Enemy(enemy.getEnemyName(), enemy.getEnemyHealth(), enemy.getEnemyDamage()));
//        }

        enemiesStartingStats = new ArrayList<>(Enemy.getEnemiesOriginalStats());

//        for (Enemy enemy : enemiesStartingStats) {
//            if (enemy instanceof Enemy) {
//                System.out.println("enemiesStartingStats: " + enemy.toString());
//            } else if (enemy instanceof BossEnemy) {
//                System.out.println("enemiesStartingStats: " + enemy.toString());
//            } else {
//            }
//        }

        for (Enemy enemy: enemiesStartingStats) {
            System.out.println("Enemies Starting Stats: " + enemy.toString());
        }


//      copiedEnemiesStartingStats = new ArrayList<>(enemiesStartingStats);
//
//        for (Enemy enemy : copiedEnemiesStartingStats) {
//            System.out.println("copiedEnemiesStartingStats" + enemy.toString());
//        }
//
//        for (Enemy enemy : copiedEnemiesStartingStats) {
//            enemiesVariableStats.add(new Enemy(enemy.getEnemyName(), enemy.getEnemyHealth(), enemy.getEnemyDamage()));
//
//        }

//        for (Enemy enemy : enemiesVariableStats) {
//            System.out.println("enemiesVariableStats" + enemy.toString());
//        }

        chooseNewOrLoad();



    }


    public void chooseNewOrLoad() {

        System.out.println("chooseNewOrLoad() called");

        if (player != null) {

            if (player != null && player.getHealth() > 0 && saveGamesAfterRestart.size() > 0) {

                AlertDialog.Builder chooseGameModeDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                chooseGameModeDialogBuilder.setCancelable(false);
                chooseGameModeDialogBuilder.setTitle("You beat the game. Load or Start a New Game?");

//        final String[] options = {"Real life", "Movie quotes", "Both"};

                chooseGameModeDialogBuilder.setItems(new String[]{"New Game", "Load"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("TestDialog", "Selected Option: " + which);

                        newOrLoadSelected = options[which];

                        if (newOrLoadSelected.equalsIgnoreCase("New Game")) {
                            mainTextViewText = "Welcome to Medieval Marvels and Might!";
                            previousStageTextViewText = "Welcome to Medieval Marvels and Might!";
                            previousPreviousStageTextViewText = "";
                            player = null;
                            createPlayer();
//                        mainTextViewText = "Welcome to Medieval Marvels and Might" + player.getName();
                            art.homeScreen();
                        } else if (newOrLoadSelected.contentEquals("Load")) {
                            load();
                        }

                    }
                });
                chooseGameModeDialogBuilder.create().show();


            } else if (player != null & player.getHealth() > 0) {
                AlertDialog.Builder chooseGameModeDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                chooseGameModeDialogBuilder.setCancelable(false);
                chooseGameModeDialogBuilder.setTitle("You beat the game. Start a New Game");

//        final String[] options = {"Real life", "Movie quotes", "Both"};

                chooseGameModeDialogBuilder.setItems(new String[]{"New Game"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("TestDialog", "Selected Option: " + which);

                        newOrLoadSelected = options[which];

                        previousPreviousStageTextViewText = "";
                        previousStageTextViewText = "Welcome to Medieval Marvels and Might!";
                        mainTextViewText = "Welcome to Medieval Marvels and Might!";


                        player = null;
                        createPlayer();
//                  mainTextViewText = "Welcome to Medieval Marvels and Might" + player.getName();
                        art.homeScreen();


                    }
                });
                chooseGameModeDialogBuilder.create().show();
            } else if (player != null && saveGamesAfterRestart.size() > 0) {

                AlertDialog.Builder chooseGameModeDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                chooseGameModeDialogBuilder.setCancelable(false);
                chooseGameModeDialogBuilder.setTitle("You have Succumbed to the Enemy. Load or Start a New Game?");

//        final String[] options = {"Real life", "Movie quotes", "Both"};

                chooseGameModeDialogBuilder.setItems(new String[]{"New Game", "Load"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("TestDialog", "Selected Option: " + which);

                        newOrLoadSelected = options[which];

                        if (newOrLoadSelected.equalsIgnoreCase("New Game")) {
                            mainTextViewText = "Welcome to Medieval Marvels and Might!";
                            previousStageTextViewText = "Welcome to Medieval Marvels and Might!";
                            previousPreviousStageTextViewText = "";
                            player = null;
                            createPlayer();
//                        mainTextViewText = "Welcome to Medieval Marvels and Might" + player.getName();
                            art.homeScreen();
                        } else if (newOrLoadSelected.contentEquals("Load")) {
                            load();
                        }

                    }
                });
                chooseGameModeDialogBuilder.create().show();

            } else if (player != null) {
                AlertDialog.Builder chooseGameModeDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                chooseGameModeDialogBuilder.setCancelable(false);
                chooseGameModeDialogBuilder.setTitle("You have Succumbed to the Enemy. Start a New Game");

//        final String[] options = {"Real life", "Movie quotes", "Both"};

                chooseGameModeDialogBuilder.setItems(new String[]{"New Game"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("TestDialog", "Selected Option: " + which);

                        newOrLoadSelected = options[which];

                        previousPreviousStageTextViewText = "";
                        previousStageTextViewText = "Welcome to Medieval Marvels and Might!";
                        mainTextViewText = "Welcome to Medieval Marvels and Might!";


                        player = null;
                        createPlayer();
//                  mainTextViewText = "Welcome to Medieval Marvels and Might" + player.getName();
                        art.homeScreen();


                    }
                });
                chooseGameModeDialogBuilder.create().show();

            }
        } else if (player == null) {

            if (player == null && saveGamesAfterRestart.size() > 0) {

                AlertDialog.Builder chooseGameModeDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                chooseGameModeDialogBuilder.setCancelable(false);
                chooseGameModeDialogBuilder.setTitle("Load a saved Game or start a new one?");

//        final String[] options = {"Real life", "Movie quotes", "Both"};

                chooseGameModeDialogBuilder.setItems(new String[]{"New Game", "Load"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("TestDialog", "Selected Option: " + which);

                        newOrLoadSelected = options[which];

                        if (newOrLoadSelected.equalsIgnoreCase("New Game")) {
                            createPlayer();
                            art.homeScreen();
                        } else if (newOrLoadSelected.contentEquals("Load")) {
                            load();
                        }

                    }
                });
                chooseGameModeDialogBuilder.create().show();
            } else {
                AlertDialog.Builder chooseGameModeDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                chooseGameModeDialogBuilder.setCancelable(false);
                chooseGameModeDialogBuilder.setTitle("Start a New Game");

//        final String[] options = {"Real life", "Movie quotes", "Both"};

                chooseGameModeDialogBuilder.setItems(new String[]{"New Game"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("TestDialog", "Selected Option: " + which);

                        newOrLoadSelected = options[which];

                        mainTextViewText = "Welcome to Medieval Marvels and Might!";
                        previousStageTextViewText = "Welcome to Medieval Marvels and Might!";
                        previousPreviousStageTextViewText = "";
                        player = null;
                        createPlayer();
//                        mainTextViewText = "Welcome to Medieval Marvels and Might" + player.getName();
                        art.homeScreen();

                    }
                });
                chooseGameModeDialogBuilder.create().show();
            }

        }

    }

    private void selectOption(int answerSelection) {

        System.out.println("selectOption() called");
        userChoice = answerSelection;
        if (answerSelection == 0) {
            System.out.println("Answer Selection = " + answerSelection);
            userResponse0Button.setText("✔ " + "Yes");
            userResponse1Button.setText("No");
            userResponse2Button.setText("Save");
            userResponse3Button.setText("Load");
            userExitButton.setText("Delete");
            validAnswer = true;
        } else if (answerSelection == 1) {
            System.out.println("Answer Selection = " + answerSelection);
            userResponse0Button.setText("Yes");
            userResponse1Button.setText("✔" + "No");
            userResponse2Button.setText("Save");
            userResponse3Button.setText("Load");
            userExitButton.setText("Delete");
            validAnswer = true;
        } else if (answerSelection == 2) {
            System.out.println("Answer Selection = " + answerSelection);
            userResponse0Button.setText("Yes");
            userResponse1Button.setText("No");
            userResponse2Button.setText("✔ " + "Save");
            userResponse3Button.setText("Load");
            userExitButton.setText("Delete");
            validAnswer = true;
        } else if (answerSelection == 3) {
            System.out.println("Answer Selection = " + answerSelection);
            userResponse0Button.setText("Yes");
            userResponse1Button.setText("No");
            userResponse2Button.setText("Save");
            userResponse3Button.setText("✔ " + "Load");
            userExitButton.setText("Delete");
            validAnswer = true;
        } else if (answerSelection == 4) {
            System.out.println("Answer Selection = " + answerSelection);
            userResponse0Button.setText("Yes");
            userResponse1Button.setText("No");
            userResponse2Button.setText("Save");
            userResponse3Button.setText("Load");
            userExitButton.setText("✔ " + "Delete");
            validAnswer = true;
        } else {
            System.out.println("Answer Selection = " + answerSelection);
            validAnswer = false;
        }
    }

    public void onAnswerSubmission() {

        System.out.println("onAnswerSubmission Called");
        System.out.println("Valid Answer: " + validAnswer);
        System.out.println("User Choice = " + userChoice);
        userResponse0Button.setText("Yes");
        userResponse1Button.setText("No");
        userResponse2Button.setText("Save");
        userResponse3Button.setText("Load");
        userExitButton.setText("Delete");



        if(waitingForAnswer){
            waitingForAnswer = false;
        }
        System.out.println("if(waitingForAnswer) set");
        System.out.println("waiting for answer: " + waitingForAnswer);

            removeDelay();
        System.out.println("removeDelay() called");

        validAnswer = false;
        System.out.println("User Choice = " + userChoice);

        // nextLevel();
    }

    public Player createPlayer() {

        if (player == null){
            System.out.println("userTextInput before myAlertDialog created " + userTextInput);

            MyAlertDialog myAlertDialogCreatePlayer = new MyAlertDialog(this, this, "Please enter your character name", false, false);
            }

  //      return null;
        return player;

    }

    @Override
    public void onTextEnteredForPlayerName(String enteredText){

        if (!enteredText.equalsIgnoreCase("")) {

            userTextInput = enteredText;


            System.out.println("userTextInput before player created " + userTextInput);
            player = new Player(userTextInput, this);
            System.out.println("userTextInput after player created " + userTextInput);

            System.out.println("New player name is " + player.getName());

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    nextLevel();
                }
            }, 100);
        } else {
        createPlayer();

        }

    }

    private /*String*/ void save() {
        // Add save functionality here
//        String fileName = player.getName() + ".svr";

        if (player != null) {
            System.out.println("userTextInput before myAlertDialog created " + userTextInput);

            MyAlertDialog myAlertDialogSavePlayer = new MyAlertDialog(this, this, "Please enter your save name", true, true);
        }

      //  return userTextInput;
    }




    private void delete() {
        // Add load functionality here

        File internalStorageDir = context.getFilesDir();
        File[] files = internalStorageDir.listFiles();

        ArrayList<String> saveGames = new ArrayList<>();

        saveGames.add(0, "Cancel Delete Game");

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".svr")) {
                        saveGames.add(fileName.substring(0, fileName.length() - 4));
                    }
                }
            }
        }


        if (saveGames.size() > 0) {

            //         if (player != null) {

            AlertDialog.Builder chooseSavedGameToDeleteDialogBuilder = new AlertDialog.Builder(MainActivity.this);
          //  chooseSavedGameToDeleteDialogBuilder.setCancelable(false);
            chooseSavedGameToDeleteDialogBuilder.setTitle("Please choose your game to delete");


            int saveGamesSize = saveGames.size();

            String[] savedGames = new String[saveGamesSize];

            for (int i = 0; i < saveGamesSize; i++) {
                savedGames[i] = saveGames.get(i);
            }


            chooseSavedGameToDeleteDialogBuilder.setItems(savedGames, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("Test Dialog", "Saved Games: " + which);
                    String deleteGameSelected = savedGames[which] + ".svr";

                    if (which > 0) {

                        confirmDelete(deleteGameSelected);
                    } else {
                        dialog.dismiss();
                    }

                }
            });

            chooseSavedGameToDeleteDialogBuilder.create().show();

        } else {

                        //previousPreviousStageTextViewText = "";
                        //previousStageTextViewText = "";
                        //userChoice = -1;
                        //nextLevel();

                    }

                }

                private void confirmDelete(final String deleteGameSelected) {
        AlertDialog.Builder confirmDeleteDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        confirmDeleteDialogBuilder.setCancelable(false);
        confirmDeleteDialogBuilder.setTitle("Confirm Delete");
        confirmDeleteDialogBuilder.setMessage("Are you sure you want to delete : " + deleteGameSelected + "?");

        confirmDeleteDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteSavedGame(deleteGameSelected);
            }
        });

        confirmDeleteDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                previousPreviousStageTextViewText = "";
//                previousStageTextViewText = "";
//                userChoice = -1;
//                nextLevel();
            }
        });

        confirmDeleteDialogBuilder.create().show();
                }

       private void deleteSavedGame(String deleteGameSelected) {
        File internalStorageDir = context.getFilesDir();
        File deleteFile = new File(internalStorageDir, deleteGameSelected);

        if (deleteFile.exists()) {
            if (deleteFile.delete()){
                System.out.println(deleteGameSelected + " : successfully deleted");
            } else {
                System.out.println("Failed to delete " + deleteGameSelected);
            }
        } else {
            System.out.println("Error " + deleteGameSelected + " not found");
        }

       }
//            });
//
//            //        if (saveGamesSize > 0) {
//
//            chooseSavedGameToDeleteDialogBuilder.create().show();
//        } else if (player == null) {
//
//
//            userChoice = -1;
//            createPlayer();
//            //      }
//
//        } else {
//            userChoice = -1;
//            nextLevel();
//        }
//    }



        private void load() {
            // Add load functionality here

   //         change to dialog box rather choose and submit?

            System.out.println("Load game called");

            File internalStorageDir = context.getFilesDir();
            File[] files = internalStorageDir.listFiles();

            ArrayList<String> saveGames = new ArrayList<>();

            saveGames.add(0, "Cancel Load Game");

            if (files != null){
                for (File file : files) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        if (fileName.endsWith(".svr")) {
                            saveGames.add(fileName.substring(0, fileName.length() -4));
                        }
                    }
                }
            }


            if (saveGames.size() > 0) {

                //         if (player != null) {

                AlertDialog.Builder chooseSavedGameDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                chooseSavedGameDialogBuilder.setCancelable(true);
                chooseSavedGameDialogBuilder.setTitle("Please choose your saved game");


                int saveGamesSize = saveGames.size();

                String[] savedGames = new String[saveGamesSize];

                for (int i = 0; i < saveGamesSize; i++) {
                    savedGames[i] = saveGames.get(i);
                }


                chooseSavedGameDialogBuilder.setItems(savedGames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Test Dialog", "Saved Games: " + which);
                        loadGameSelected = savedGames[which] + ".svr";

                        Player loadedPlayerSave;
                        ArrayList<Enemy> loadedEnemiesSave;
                        ArrayList<Item> loadedItemList;
                        String loadedStorySoFar;
                        ArrayList<Equipable> loadedEquipableItemList;
                        String loadedLatestShop;
                        ArrayList<Equipable> loadedShopInventory;



                        if (which > 0) {
                            System.out.println("which > 0");
                            try {


                                File loadFile = new File(internalStorageDir, loadGameSelected);

                                FileInputStream loadedSavePlayerFile = new FileInputStream(loadFile);
                                ObjectInputStream loadedObjectPlayerFile = new ObjectInputStream(loadedSavePlayerFile);

                                loadedPlayerSave = (Player) loadedObjectPlayerFile.readObject();
                                player = loadedPlayerSave;

                                loadedEnemiesSave = (ArrayList<Enemy>) loadedObjectPlayerFile.readObject();

                                loadedItemList = (ArrayList<Item>) loadedObjectPlayerFile.readObject();

                                loadedStorySoFar = (String) loadedObjectPlayerFile.readObject();

                                loadedEquipableItemList = (ArrayList<Equipable>) loadedObjectPlayerFile.readObject();

                                loadedLatestShop = (String) loadedObjectPlayerFile.readObject();

                                loadedShopInventory = (ArrayList<Equipable>) loadedObjectPlayerFile.readObject();


                                Iterator<Enemy> iterator = enemiesStartingStats.iterator();
                                while (iterator.hasNext()) {
                                    Enemy currentEnemy = iterator.next();
                                    for (Enemy loadedEnemy : loadedEnemiesSave) {
                                        if (currentEnemy.getEnemyName().equalsIgnoreCase(loadedEnemy.getEnemyName())/* && loadedEnemy.getIsOriginal() == false */) {

                                            currentEnemy.updateEnemy(loadedEnemy);
                                            System.out.println("Enemy: " + loadedEnemy.getEnemyName() + " health has been set to " + loadedEnemy.getEnemyHealth());
                                            System.out.println("Enemy: " + loadedEnemy.getEnemyName() + " damage has been set to " + loadedEnemy.getEnemyDamage());
                                           // iterator.remove(); // Remove the currentEnemy using the iterator
                                            break;
                                        }
                                    }
                                }

                                itemList = loadedItemList;
                                storySoFar = loadedStorySoFar;
                                equipableArrayList = loadedEquipableItemList;
                                lastShopName = loadedLatestShop;
                                lastShopInventory = loadedShopInventory;

                                mainActivity = MyApplication.getMainActivityInstance();

                                if (mainActivity == null){
                                    System.out.println("mainActivity = null");
                                } else {
                                    System.out.println("mainActivity != null");
                                }

// Now add the loadedEnemiesSave to the enemies list
      //                          enemiesStartingStats.addAll(loadedEnemiesSave);


//                                previousPreviousStageTextViewText = "";
//                                previousStageTextViewText = "";
                               // userChoice = -1;
                                System.out.println("Next Level about to be called");
                                nextLevel();

                                loadedObjectPlayerFile.close();
                            } catch (IOException | ClassNotFoundException e) {


                            }

                        } else if (which == 0 && player != null) {
                            System.out.println("which == 0 && player!= null");

                            dialog.dismiss();
                        } else {
                            System.out.println("else");
                            dialog.dismiss();
                            chooseNewOrLoad();
                        }



                    }
                });

                //        if (saveGamesSize > 0) {

                chooseSavedGameDialogBuilder.create().show();



            } else if (player == null) {


              //  userChoice = -1;
                createPlayer();
                //      }

            } else {
              //  userChoice = -1;
              //  nextLevel();
            }





    }

    @Override
    public void onTextEnteredForOtherPurpose(String enteredText){

        if (!enteredText.equalsIgnoreCase("")){

            userTextInput = enteredText;

            String chosenName = userTextInput;
            String fileName = chosenName+": " + player.getProgress() + " .svr";
            try {


                File internalStorageDir = context.getFilesDir();
                File saveFile = new File(internalStorageDir, fileName);

                FileOutputStream fileOutputStreamSavePlayer = new FileOutputStream(saveFile);
                ObjectOutputStream playerSaver = new ObjectOutputStream(fileOutputStreamSavePlayer);

                playerSaver.writeObject(player);

                playerSaver.writeObject(enemiesStartingStats);

                playerSaver.writeObject(itemList);

                playerSaver.writeObject(storySoFar);

                playerSaver.writeObject(equipableArrayList);

                playerSaver.writeObject(lastShopName);

                playerSaver.writeObject(lastShopInventory);



                playerSaver.close();
                fileOutputStreamSavePlayer.close();
//                playerSaver.writeObject(progress);
                System.out.println("We've just saved your game with file name " + chosenName);

                //saveGames.add(chosenName);
                //System.out.println("Saved Games: " + saveGames.toString());

                File[] files = internalStorageDir.listFiles();




                if (files != null){
                    for (File file : files) {
                        if (file.isFile()) {
                            String fileNameFound = file.getName();
                            if (fileNameFound.endsWith(".svr")) {
                                saveGamesAfterRestart.add(fileNameFound.substring(0, fileNameFound.length() -4));
                            }
                        }
                    }
                }


                int saveGamesSize = saveGamesAfterRestart.size();

                String[] savedGames = new String[saveGamesSize];

                for (int i = 0; i < saveGamesSize; i++) {
                    savedGames[i] = saveGamesAfterRestart.get(i);
                }



                System.out.println("Saved Games: " + savedGames.toString());

                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

                //return fileName;
            } catch (IOException e) {
                e.printStackTrace();
                String cannotSave = "Unable to save game.";
                System.out.println(cannotSave);

                //return cannotSave;
            }
            // End of save
            // would be preferable to save as a console given name so different saves can be made and loaded when needed.
           // userChoice = -1;
           // startDelayedTask(1000, true);

        } else {
            save();
        }



    }

    public Player getPlayer() {

        return player;
    }

    private void runLevelOne(int levelIndex) {


        userSubmitButton.setEnabled(true);

        String[] levelTexts = {
                "Welcome to Medieval Marvels!",
                "Adventure awaits " + player.getName() + "!",
                "Your experiments with magic have yielded few successes so far, however a kindly wizard has had compassion for your efforts and given you a magical rucksack.",
                "Any non-living item less than the size of a spear could be placed into the sack and be rendered weightless.",
                "The wizard starts you off on your journey and you are excited to try out this magical gift.",
                "You discover a chest. Would you like to open it?"
        };



        if (levelIndex >= levelTexts.length) {
            // All levels completed
            return;
        }

        // Set the text for the current level
        setPreviousAndMainText(levelTexts[levelIndex]);

        // Enable submit button for user interaction
        userSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceed to the next level after the button click
                runLevelOne(levelIndex + 1);
            }
        });

        // Additional UI operations for specific levels
        if (levelIndex == 5) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //setPreviousAndMainText("You discover a chest. Would you like to open it?");
                    mainImageView.setImageResource(R.drawable._1gwcdig28l__ac_sx569_);
                    System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
                    userChoice = -1;
                    waitingForAnswer = true;
                    startDelayedTask(200000, true);
                    enableAllButtons();
                    userSubmitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (validAnswer) {
                                onAnswerSubmission();
                            }
                            if (validAnswer) {
                                waitingForAnswer = false;
                                System.out.println("Waiting for answer = false");
                            }
                            if (advanceCountShouldIncrease) {
                                advanceCount += 1;
                            }
                        }
                    });
                }
            });
        }
    }







    public void nextLevel() {

        disableAllButtons();


//        delayTask = new DelayTask();
        System.out.println("nextLevel() called");
        String progress = player.getProgress();
        System.out.println(progress);
        waitingForAnswer = true;
        userChoice = -1;

        if (mainActivity == null){
            System.out.println("mainActivity = null");
        } else {
            System.out.println("mainActivity != null");
        }




        switch (progress) {
            case "level1":

                storySoFar = "";

                setPreviousAndMainText(storySoFar);

                advanceCount = 0;


                                        if (!itemList.isEmpty()) {
                                            itemList.clear();
                                        }


                                        if (!equipableArrayList.isEmpty()) {
                                            equipableArrayList.clear();
                                        }

                                        Shoe speedyBoots = new Shoe("Speedy Boots", 20, 200, 1000, 200, R.drawable.zombie1);

                                        //         addEquipableToEquipableList(speedyBoots);

                                        equipableArrayList.add(speedyBoots);

                                        lastShopInventory = null;


                                        for (Enemy originalEnemy : enemiesStartingStats) {
                                            // Assuming the "NewGame" version has the same name with "NewGame" appended
                                            String newGameName = originalEnemy.getEnemyName() + "NewGame";

                                            // Find the "NewGame" version
                                            for (Enemy newGameEnemy : enemiesStartingStats) {
                                                if (newGameEnemy.getEnemyName().equals(newGameName)) {
                                                    // Update the original enemy's statistics
                                                    originalEnemy.updateEnemy(newGameEnemy);
                                                    // You may update other properties as needed
                                                }
                                            }
                                        }

                currentLevelPart = 0;

                        // Start the level sequence
                        runLevelOne(currentLevelPart);


                break;
            case "level2":
//                userSubmitButton.setEnabled(false);
                //   addDelay(5000);
//                userSubmitButton.setEnabled(true);
                //       mainTextView.setText("You have reached the start of level 2");
                //   System.out.println("You have reached the start of level 2");

                waitingForAnswer = true;


                    if (mainActivity == null) {
                        System.out.println("mainActivity = null");
                    } else {
                        System.out.println("mainActivity != null");
                    }

                    //   setPreviousAndMainText("You have reached the start of level 2");


                    disableAllButtons();
                    //     addDelay(5000);
//                userSubmitButton.setEnabled(true);


                    //             handler.postDelayed(new Runnable() {
                    //                 @Override
                    //                 public void run() {

//                Enemy zombieOne = new Enemy("Zombie",4,2);


                    handlerDelayMessage.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            currentEnemy = zombieOne;

                            System.out.println("\nYou discover a " + zombieOne.getEnemyName() + ". The Zombie has " + zombieOne.getEnemyHealth() + " health and " + zombieOne.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
                            //              mainTextView.setText("\nYou discover a Zombie. The Zombie has " + zombieHealth + " health and " + zombieDamage + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setPreviousAndMainText("You discover a " + zombieOne.getEnemyName() + ". Would you like to attack it?");
                                    mainImageView.setImageResource(R.drawable.workzombie);


                                }
                            });

                            enableAllButtons();

                            zombie = userChoice;

                            //                 }
                            //                 }, 5000);

                            //     startDelayedTask(200000, true);


                        }
                    }, 1500);


                    for (Enemy enemy : enemiesStartingStats) {
                        System.out.println("enemiesStartingStats: " + enemy.toString());
                    }


//                for (Enemy enemy : copiedEnemiesStartingStats) {
//                    System.out.println("copiedEnemiesStartingStats" + enemy.toString());
//                }
//
//
//                for (Enemy enemy : enemiesVariableStats) {
//                    System.out.println("enemiesVariableStats" + enemy.toString());
//                }

                new Thread(new Runnable() {


                    @Override
                    public void run() {
                        // Your while loop doing nothing
                        while (waitingForAnswer == true) {
                            // Do nothing
                        }

                        while (waitingForAnswer == false) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    zombie = userChoice;
                                    System.out.println("While loop called");
                                    System.out.println("UserChoice = " + userChoice);
                                    System.out.println("zombie = " + zombie);
                                    if (zombie == 0) {
                                        userSubmitButton.setEnabled(false);
                                        setPreviousAndMainText("You attack the zombie");
                                        System.out.println("\nYou attack the zombie");
                                        for (Enemy enemy : enemiesStartingStats) {
                                            System.out.println("enemiesStartingStats: " + enemy.toString());
                                        }
                                        String levelOneVictory = "The %s was carrying a %s which you claim as your own. You also recover %s gold.";
                                        advanceCount = 0;
                                        battle(zombieOne, longSword, levelOneVictory, "level3", 40, null);
                                        if (player.getHealth() > 0) {
                                            nextLevel();
                                            waitingForAnswer = true;

                                        } else {
                                            chooseNewOrLoad();
                                            waitingForAnswer = true;
                                        }
                                    } else if (zombie == 1) {
                                        System.out.println("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");
                                        setPreviousAndMainText("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");
                                        player.takeDamage(zombieOne.getEnemyDamage() * 1.5);
                                        System.out.println(player.toString());
                                        player.setProgress("level3");
                                        if (player.getHealth() > 0) {
                                            System.out.println("Zombie == 1 called");
                                            nextLevel();
                                            waitingForAnswer = true;
                                        } else {
                                            chooseNewOrLoad();
                                            waitingForAnswer = true;
                                        }
                                    } else {
                                        previousStageTextViewText = "";
                                      //  startDelayedTask(200000, true);
                                        waitingForAnswer = true;
                                    }
                                  //  nextLevel();
                                }

                            });
                        //break;
                        }


                }

                }).start();
                break;

            case "level3":

        //        while (player.getProgress().equalsIgnoreCase("level3")) {


         //           handler.postDelayed(new Runnable() {
         //                                   @Override
         //                                   public void run() {
                currentEnemy = null;

                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

                ArrayList<Equipable> majistosWorkshop = new ArrayList<>();



                String majistosWorkshopName = "Majisto's Workshop";

                Weapon daggerStilleto = new Weapon("Stilleto", 8.0, 300.0, 60.0, 1, R.drawable.stiletto);
                Weapon mace = new Weapon("Mace", 40, 800, 160, 1, R.drawable.mace_2);
                Weapon longsword = new Weapon("Longsword", 35.0, 750.0, 150.0, 1, R.drawable.longsword_1);
                Weapon warhammer = new Weapon("Warhammer", 38.0, 780.0, 156.0, 2, R.drawable.warhammer_4);
                Weapon halberd = new Weapon("Halberd", 32.0, 720.0, 144.0, 2, R.drawable.halberd_3);

                Shield lionShield = new Shield("Lion Head Shield", 5, 16, 12, 250, 50, 1, R.drawable.lion_head_shield_3);
                Shield romanCenturion = new Shield("Roman Centurian", 3, 10, 25, 500, 100, 1, R.drawable.roman_centurian_design_shield_1 );
                Shield dragonScaleShield = new Shield("Dragon Scale Shield", 6, 18, 15, 300, 60, 1, R.drawable.dragonscale_shield_1);
                Shield vikingRoundShield = new Shield("Viking Round Shield", 4, 12, 10, 200, 40, 1, R.drawable.viking_round_wooden_shield_4);
                Shield towerShield = new Shield("Tower Shield", 7, 20, 16, 350, 70, 2, R.drawable.tower_shield_8);

                Helmet dragonHelmet = new Helmet("Dragon Scale Helmet", 20, 15, 300, 60, R.drawable.dragonhelmet_4);
                Helmet lionHelmet = new Helmet("Lion Mask Helmet", 8, 7, 200, 40, R.drawable.lionhelmet_2);
                Helmet tigerHelmet = new Helmet("Tiger Stripe Helmet", 18, 13, 280, 55, R.drawable.tigerhelmet_1);
                Helmet eagleHelmet = new Helmet("Eagle Feather Helmet", 15, 10, 250, 50, R.drawable.eaglehelmet_4);
                Helmet bearHelmet = new Helmet("Bear Claw Helmet", 22, 17, 320, 65, R.drawable.bearhelmet_7);

                ChestArmour leatherChestArmour = new ChestArmour("Leather Jerkin", 15, 10, 200, 40, R.drawable.leatherchestarmour1);
                ChestArmour gambesonChestArmour = new ChestArmour("Gambeson Tunic", 20, 15, 250, 50, R.drawable.gambeson_chest_armor_1);
                ChestArmour brigandineChestArmour = new ChestArmour("Brigandine Vest", 25, 20, 300, 60, R.drawable.brigandinechestarmour2);
                ChestArmour paddedChestArmour = new ChestArmour("Padded Jack", 18, 13, 230, 46, R.drawable.paddedarmour_1);
                ChestArmour surcoatChestArmour = new ChestArmour("Surcoat with Mail Sleeves", 22, 18, 280, 56, R.drawable.surcoatchestarmour1);

                Trouser leatherTrouser = new Trouser("Leather Leggings", 12, 8, 150, 30, R.drawable.leatherleggings3);
                Trouser paddedTrouser = new Trouser("Padded Chausses", 15, 10, 180, 36, R.drawable.padded_chausses);
                Trouser mailLeggings = new Trouser("Mail Leggings", 20, 15, 220, 44, R.drawable.chainmailleggings_4);
                Trouser brigandineLeggings = new Trouser("Brigandine Leg Guards", 18, 13, 200, 40, R.drawable.brigandinelegguards1);
                Trouser clothHose = new Trouser("Cloth Hose", 14, 9, 170, 34, R.drawable.cloth_hose);

                Shirt linenShirt = new Shirt("Linen Undertunic", 10, 5, 120, 24, R.drawable.linen_undertunic_1);
                Shirt armingDoublet = new Shirt("Arming Doublet", 15, 8, 160, 32, R.drawable.arming_doublet_2);
                Shirt paddedGambeson = new Shirt("Padded Gambeson", 18, 12, 200, 40, R.drawable.padded_gambeson_2);
                Shirt silkUndershirt = new Shirt("Silk Undershirt", 12, 6, 140, 28, R.drawable.silk_undertunic_1);
                Shirt woolenHauberk = new Shirt("Woolen Hauberk", 20, 15, 220, 44, R.drawable.woolen_hauberk_1);

                Shoe leatherBoots = new Shoe("Leather Boots", 10, 6, 120, 24, R.drawable.leather_boots_1);
                Shoe paddedGreaves = new Shoe("Padded Greaves", 15, 8, 160, 32, R.drawable.padded_greaves_1);
                Shoe woodenSabatons = new Shoe("Wooden Sabatons", 18, 12, 200, 40, R.drawable.wooden_sabatons_2);
                Shoe furLinedBoots = new Shoe("Fur-lined Boots", 12, 7, 140, 28, R.drawable.fur_lined_boots_3);
                Shoe chainmailFootguards = new Shoe("Chainmail Footguards", 20, 15, 220, 44, R.drawable.chainmail_footguards_2);



                addEquipablesToWorkshop(majistosWorkshop,
                        daggerStilleto,
                        mace,
                        longsword,
                        warhammer,
                        halberd,
                        lionShield,
                        romanCenturion,
                        dragonScaleShield,
                        vikingRoundShield,
                        towerShield,
                        dragonHelmet,
                        lionHelmet,
                        tigerHelmet,
                        eagleHelmet,
                        bearHelmet,
                        leatherChestArmour,
                        gambesonChestArmour,
                        brigandineChestArmour,
                        paddedChestArmour,
                        surcoatChestArmour,
                        leatherTrouser,
                        paddedTrouser,
                        mailLeggings,
                        brigandineLeggings,
                        clothHose,
                        linenShirt,
                        armingDoublet,
                        paddedGambeson,
                        silkUndershirt,
                        woolenHauberk,
                        leatherBoots,
                        paddedGreaves,
                        woodenSabatons,
                        furLinedBoots,
                        chainmailFootguards
);

//System.out.println(majistosWorkshop);

                lastShopInventory = majistosWorkshop;

                lastShopName = majistosWorkshopName;

 //               switchToShop(lastShopInventory, lastShopName, getPlayerEquipablesList());



                disableAllButtons();
                handlerDelayMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                 //       userSubmitButton.setEnabled(true);



                        System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setPreviousAndMainText("You discover a chest. Would you like to open it?");

                                mainImageView.setImageResource(R.drawable._1gwcdig28l__ac_sx569_);
                            }
                        });

//                        setPreviousAndMainText("You discover a chest. Would you like to open it?");
//
//                        mainImageView.setImageResource(R.drawable._1gwcdig28l__ac_sx569_);


                        chestTwo = userChoice;

                        //                                   }
                        //           }, 5000);
                    enableAllButtons();
                        startDelayedTask(200000, true);

                    }
                },1500);


                for (Enemy enemy : enemiesStartingStats) {
                    System.out.println("enemiesStartingStats: " + enemy.toString());
                }

     //   }


                break;

            case "level4":

                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

       //             handler.postDelayed(new Runnable() {
       //                 @Override
       //                 public void run() {

                currentEnemy = zombieKing;

                disableAllButtons();
         //       while (player.getProgress().equalsIgnoreCase("level4")) {

                handlerDelayMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("\nYou discover a " + zombieKing.getEnemyName() + " .The Zombie King has " + zombieKing.getEnemyHealth() + " health and " + zombieKing.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setPreviousAndMainText("You discover a " + zombieKing.getEnemyName() + ". Would you like to attack it?");

                                mainImageView.setImageResource(R.drawable.zombie_king_by_reddnekk_dc69pgp_fullview);
                            }
                        });

//userSubmitButton.setEnabled(true);

                        enemy2 = userChoice;

                        //                   }
                        //                }, 5000);

                        startDelayedTask(200000, true);
                       enableAllButtons();

                    }
                },1500);


                for (Enemy enemy : enemiesStartingStats) {
                    System.out.println("enemiesStartingStats: " + enemy.toString());
                }

                    break;
      //          }


            case "level5":

                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

                currentEnemy = null;

                disableAllButtons();
                handlerDelayMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("\nYou discover a door. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                setPreviousAndMainText("You discover a door. Would you like to open it?");

                                mainImageView.setImageResource(R.drawable.victorian_4_panel_solid_oak_door);

                            }
                        });

//userSubmitButton.setEnabled(true);


                        doorOne = userChoice;

                        startDelayedTask(200000, true);
                        enableAllButtons();

                    }
                },1500);

                for (Enemy enemy : enemiesStartingStats) {
                    System.out.println("enemiesStartingStats: " + enemy.toString());
                }

                    break;

            case "level6":

                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

                currentEnemy = loki;


               disableAllButtons();

                Shield wooodenShield = new Shield("Round wooden shield", 3, 4, 8, 30, 2, 1, R.drawable.woodenshield);

                handlerDelayMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("\nYou discover " + loki.getEnemyName() + ". He has " + loki.getEnemyHealth() + " health and " + loki.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                setPreviousAndMainText("You discover " + loki.getEnemyName() + ". Luckily, nearby you find a " + wooodenShield.getName() + " and pick it up. " + loki.getEnemyName() + " moves into an aggressive pose. Would you like to attack them?");

                                addEquipableToEquipableList(wooodenShield);

                                mainImageView.setImageResource(R.drawable.loki_1556801363);

                            }
                        });

//userSubmitButton.setEnabled(true);

                        enemy3 = userChoice;


                        startDelayedTask(200000, true);
                        enableAllButtons();

                    }
                },1500);

                for (Enemy enemy : enemiesStartingStats) {
                    System.out.println("enemiesStartingStats: " + enemy.toString());
                }

                    break;

            case "level7":

                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

                currentEnemy = werewolf;

                //             handler.postDelayed(new Runnable() {
                //                 @Override
                //                 public void run() {

                disableAllButtons();
                //       while (player.getProgress().equalsIgnoreCase("level4")) {

                handlerDelayMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("\nYou discover a " + werewolf.getEnemyName() + ". The " + werewolf.getEnemyName() + " has " + werewolf.getEnemyHealth() + " health and " + werewolf.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setPreviousAndMainText("You discover a " + werewolf.getEnemyName() + ". Would you like to attack it?");

                                mainImageView.setImageResource(R.drawable.werewolves);
                            }
                        });

//userSubmitButton.setEnabled(true);

                        enemy4 = userChoice;

                        //                   }
                        //                }, 5000);

                        startDelayedTask(200000, true);
                        enableAllButtons();

                    }
                },1500);


                for (Enemy enemy : enemiesStartingStats) {
                    System.out.println("enemiesStartingStats: " + enemy.toString());
                }

                break;
            //          }


            case "level8":

                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

                currentEnemy = null;

                disableAllButtons();
                handlerDelayMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                setPreviousAndMainText("You discover a chest. Would you like to open it?");

                                mainImageView.setImageResource(R.drawable._1gwcdig28l__ac_sx569_);

                            }
                        });

//userSubmitButton.setEnabled(true);


                        chestThree = userChoice;

                        startDelayedTask(200000, true);
                       enableAllButtons();

                    }
                },1500);

                for (Enemy enemy : enemiesStartingStats) {
                    System.out.println("enemiesStartingStats: " + enemy.toString());
                }

                break;

            case "level9":

                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

                currentEnemy = morgana;


                disableAllButtons();
                handlerDelayMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("\nYou discover " + morgana.getEnemyName() + ". They have " + morgana.getEnemyHealth() + " health and " + morgana.getEnemyDamage() + " damage. Would you like to attack them? Type y for yes, n for no, s for save, x for exit.");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                setPreviousAndMainText("You discover " + morgana.getEnemyName() + ". Would you like to attack them?");

                                mainImageView.setImageResource(R.drawable.merlin1133);

                            }
                        });

//userSubmitButton.setEnabled(true);

                        enemy5 = userChoice;


                        startDelayedTask(200000, true);
                        enableAllButtons();

                    }
                },1500);

                for (Enemy enemy : enemiesStartingStats) {
                    System.out.println("enemiesStartingStats: " + enemy.toString());
                }

                break;

            case "level10" :

                if (mainActivity == null){
                    System.out.println("mainActivity = null");
                } else {
                    System.out.println("mainActivity != null");
                }

                currentEnemy = null;

                disableAllButtons();
                handlerDelayMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {



                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                setPreviousAndMainText("Congratulations, you have reached the end of the game");
                                mainImageView.setImageResource(R.drawable.s_l1600);

                            }
                        });

//userSubmitButton.setEnabled(true);

                        disableAllButtons();
                        player = null;
                      //  chooseNewOrLoad();

                    }
                },1500);


                handlerDelayMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        chooseNewOrLoad();


                    }
                }, 15000);



                for (Enemy enemy : enemiesStartingStats) {
                    System.out.println("enemiesStartingStats: " + enemy.toString());
                }


                break;
            default:
                throw new IllegalStateException("Unexpected value: " + progress);

        }
//            break;

    }



    private void addDelay(int time) {
        try {
            Thread.sleep(time);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void addDelayWithCancelCheck(int time) {

        long endTime = System.currentTimeMillis() + time;


        while (System.currentTimeMillis() < endTime && !waitingForAnswer) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void startDelayedTask(int time, boolean applyDelay) {
     //   if (applyDelay) {
            cancelDelayedTask(); // Cancel any existing tasks
    //    }

        System.out.println("Delayed Task Due to run");
//        System.out.println("Delayed task starting in: " + time);

        delayedTask = new Runnable() {
            @Override
            public void run() {
                // Your task logic goes here
            System.out.println(chest);


            switch(player.getProgress()) {

                case "level1":
                    chest = userChoice;
                if (chest == 0) {



      //              handler.postDelayed(new Runnable() {
      //                  @Override
      //                  public void run() {




                    System.out.println("Chest is: " + chest);


                    System.out.println("chest int  " + chest);
                    System.out.println("You open the chest to find a helmet. You put it on.");

//                    userSubmitButton.setEnabled(false);
//             //       addDelay(2000);
//                    userSubmitButton.setEnabled(true);
                    System.out.println("You choose option 0");
          //          mainTextView.setText("You open the chest to find a helmet. You put it on.");

                            setPreviousAndMainText("You open the chest to find a helmet.");


                    Helmet platedHelmet = new Helmet("Plated Helmet", 5, 8, 100, 20, R.drawable.platedhelmet);

                    addEquipableToEquipableList(platedHelmet);

 //                   player.setHelmet(platedHelmet);

       //                 }
      //              }, 5000);

                    //System.out.println(player);
                   // addDelay(2000);
                    player.setProgress("level2");
//                    userSubmitButton.setEnabled(false);
//              //      addDelay(10000);
//                    userSubmitButton.setEnabled(true);






                } else if (chest == 1) {
                    System.out.println("Chest is : " + chest);
                    System.out.println("You choose not to open the chest. An onlooker observes your honesty and gives you a pair of boots.");
                    System.out.println("You choose option 1");
              //      mainTextView.setText("You choose not to open the chest. An onlooker observes your honesty and gives you a pair of boots.");

                    setPreviousAndMainText("You choose not to open the chest. An onlooker observes your honesty and gives you a pair of boots.");

                    Shoe leatherboots = new Shoe("Leather Boots", 10, 10, 200, 40, R.drawable.leatherboots);

                    addEquipableToEquipableList(leatherboots);

  //                  player.setShoe(leatherboots);
                    //System.out.println(player);
                    player.setProgress("level2");
                    //userChoice = -1;
                    //break;
                } else if (chest == 2) {
                    //System.out.println("Please enter your save game name.");
                    //String savedFileName = userTextInputCollected;
                    //save(userTextInputCollected);
                    save();
                    break;

                } else if (chest == 3) {

                    load();
                    break;


                } else {
                    previousStageTextViewText = "";
                    startDelayedTask(200000, true);
                }
                nextLevel();
                break;

//                case "level2":
//                        zombie = userChoice;
//
//
//
//
//                        if (zombie == 0) {
//                            userSubmitButton.setEnabled(false);
//                  //          addDelay(2000);
//                            userSubmitButton.setEnabled(true);
//                 //           mainTextView.setText("You attack the zombie");
//
//                            setPreviousAndMainText("You attack the zombie");
//
//                            System.out.println("\nYou attack the zombie");
//                           // int currentWeaponDamage = player.getCurrentWeaponDamage();
// //                           int currentWeaponDamage = player.getCurrentWeaponDamage();
//                          //  int zombieOneHealth = zombieOne.getEnemyHealth();
//                          //  int zombieOneDamage = zombieOne.getEnemyDamage();
//                          //  String zombieOneName = "zombie";
//
//
//                            for (Enemy enemy : enemiesStartingStats) {
//                                System.out.println("enemiesStartingStats: " + enemy.toString());
//                            }
//
//
////                            for (Enemy enemy : copiedEnemiesStartingStats) {
////                                System.out.println("copiedEnemiesStartingStats" + enemy.toString());
////                            }
////
////
////                            for (Enemy enemy : enemiesVariableStats) {
////                                System.out.println("enemiesVariableStats" + enemy.toString());
////                            }
//
//
//                            //    battle(getEnemyForBattle(enemiesStartingStats, zombieOne), longSword);
//
//                            String levelOneVictory = "The %s was carrying a %s which you claim as your own. You also recover %s gold.";
//
//                            battle(zombieOne, longSword, levelOneVictory, "level3", 40, null);
//
//
//
//                            if (player.getHealth() >0) {
//                                nextLevel();
//                            } else {
//                                chooseNewOrLoad();
//                            }
//                            break;
//                        } else if (zombie == 1) {
//                        //    userSubmitButton.setEnabled(false);
//                        //    addDelay(2000);
//                        //    userSubmitButton.setEnabled(true);
//                            System.out.println("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");
//                       //     mainTextView.setText("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");
//
//                            setPreviousAndMainText("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");
//
//                            player.takeDamage(zombieOne.getEnemyDamage() * 1.5);
//
//                            player.setProgress("level3");
//                            if (player.getHealth() >0) {
//                                nextLevel();
//                            } else {
//                                chooseNewOrLoad();
//                            }
//                            break;
//
//                        } else if (zombie == 2) {
//                            //System.out.println("Please enter your save game name.");
//                            //String savedFileName = userTextInputCollected;
//                            //save(userTextInputCollected);
//                            save();
//                            break;
//
//                        } else if (zombie == 3) {
//
//                            load();
//                            break;
//
// /*                       } else if (zombie ==3) {
//                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
//                            System.exit(0);
//                            break;
//                        } else if (zombie == 4) {
//                            System.out.println("What is the name of your save file?");
//                            String loadFileName = userTextInputCollected + ".svr";
//                            player = load(loadFileName);
// */                       } else {
//                            //System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
//                            // zombie = userChoice;
//                            previousStageTextViewText = "";
//                            startDelayedTask(200000, true);
//                        }
//
//               nextLevel();
//                break;

                case "level3":
     //               while (true) {


                    for (Enemy enemy : enemiesStartingStats) {
                        System.out.println("enemiesStartingStats: " + enemy.toString());
                    }

                    chestTwo = userChoice;
                    if (chestTwo== 0) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                                    ChestArmour chainMail = new ChestArmour("Chain Mail", 10, 10, 200, 20, R.drawable.chainmailchestarmour);
                                    //                           player.setChestArmour(chainMail);

                                    addEquipableToEquipableList(chainMail);

                                    setPreviousAndMainText("You open the chest to find some " + chainMail.getName() + ".");




                                    System.out.println("You open the chest to find some chain mail. You put it on.");

                                    addItemToItemList(potion);

                                    System.out.println(player);

                                    try {
                        Thread.sleep(2000); // Add a small delay to avoid excessive CPU usage




                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                                    waitingForAnswer = false;

                                }
                            });


                        }
                    }).start();


                            //addDelay(2000);



                            //addDelay(2000);

                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            try {
                                                System.out.println("Attempting to move to level 4");

                                                synchronized (this) {
                                                    while (waitingForAnswer) {
                                                        System.out.println("Waiting to move to level 4");
                                                        wait();
                                                    }
                                                    System.out.println("Wait over, moving to level 4");
                                                    waitingForAnswer = true;
                                                    player.setProgress("level4");
                                                    nextLevel();

                                                }

                                                Thread.sleep(2000);

                                                synchronized (this) {
                                                    waitingForAnswer = false;
                                                    System.out.println("No longer waiting for answer");
                                                    notifyAll();
                                                }
                                                System.out.println("Done!");


                                            } catch (InterruptedException e) {
                                                System.out.println(e);


                                            }
                                        }
                                    });


                                }
                            }).start();

                            break;
                        } else if (chestTwo== 1) {
                            ChestArmour platedArmor = new ChestArmour("Plated Armor", 7, 6, 140, 35, R.drawable.platedarmour);

                            addEquipableToEquipableList(platedArmor);

//                            player.setChestArmour(platedArmor);
                            setPreviousAndMainText("You choose not to open the chest. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own " + platedArmor.getName() + " behind.");
                            System.out.println("You choose not to open the chest. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own armor behind.");
                            System.out.println(player);
                            player.setProgress("level4");
                            nextLevel();
                            break;
//                        } else if (chestTwo== 2) {
//                            System.out.println("Please enter your save game name.");
//                            String savedFileName = userTextInputCollected;
//                            save(savedFileName);
//                            break;
                        } else if (chestTwo == 2) {
                            //System.out.println("Please enter your save game name.");
                            //String savedFileName = userTextInputCollected;
                            //save(userTextInputCollected);
                            save();
                            break;
                        } else if (chestTwo == 3) {

                            load();
                            break;
//                        } else if (chestTwo== 4) {
//                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
//                            System.exit(0);
//                            break;
                        } else {
                            // System.out.println("Please try again, your options are y or n to open the chest.");
                            //  chestTwo = console.next().toLowerCase();
                            previousStageTextViewText = "";
                            startDelayedTask(200000, true);
                        }
           //         }
                    nextLevel();
                break;

                case "level4":

                    for (Enemy enemy : enemiesStartingStats) {
                        System.out.println("enemiesStartingStats: " + enemy.toString());
                    }

                        enemy2 = userChoice;
                        if (enemy2 == 0 ) {
                            System.out.println("\nYou attack the " + zombieKing.getEnemyName());

                            setPreviousAndMainText("You attack the " + zombieKing.getEnemyName());

                           // int currentWeaponDamage = player.getCurrentWeaponDamage();
                           // int zombieKingHealth = zombieKing.getEnemyHealth();


                            Trouser chainMailTrousers = new Trouser("Chain Mail Trousers", 10, 10, 200, 40, R.drawable.chainmailtrousers2);

                            String levelFourVictory = "The %s drops a key. You use it to open a chest. Inside is a pair of %s. You also recover %s gold.";

                            battle(zombieKing, chainMailTrousers, levelFourVictory, "level5", 90, null);


                            if (player.getHealth() >0) {
                                nextLevel();
                            } else {
                                chooseNewOrLoad();
                            }
                            break;
                        } else if (enemy2 == 1) {
                            setPreviousAndMainText("You choose not to attack the " + zombieKing.getEnemyName() + ". The Zombie attacks you in the back as you run away.");
                            System.out.println("You choose not to attack the " + zombieKing.getEnemyName() + ". The Zombie attacks you in the back as you run away.");
                            player.takeDamage(zombieKing.getEnemyDamage() / 2);
                            player.setProgress("level5");
                            nextLevel();
                            break;
                   /*     } else if (enemy2 == 2) {
                            System.out.println("Please enter your save mainActivity name.");
                            String savedFileName = userTextInputCollected;
                            save(savedFileName);
                            break; */
                        } else if (enemy2 == 2) {
                            //System.out.println("Please enter your save game name.");
                            //String savedFileName = userTextInputCollected;
                            //save(userTextInputCollected);
                            save();
                            break; /*
                        } else if (enemy2 == 3) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;*/

                        } else if (enemy2 == 3) {

                            load();
                            break;

                        } else {
                            // System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
                            // enemy2 = userChoice;
                            previousStageTextViewText = "";
                            startDelayedTask(200000, true);

                        }
      //              }

                    nextLevel();
                    break;


                case "level5":
                    //                  while (true) {

                    for (Enemy enemy : enemiesStartingStats) {
                        System.out.println("enemiesStartingStats: " + enemy.toString());
                    }

                    doorOne = userChoice;
                    if (doorOne== 0) {
                       // addDelay(2000);
                        Shirt woolenShirt = new Shirt("Woolen Shirt", 3, 3, 10, 2, R.drawable.woolenshirt);

                        setPreviousAndMainText("You open the door to find a " + woolenShirt.getName() + ".");
                        System.out.println("You open the door to find a leather jacket. You put it on.");

                        addEquipableToEquipableList(woolenShirt);

//                        player.setShirt(woolenShirt);
                        System.out.println(player);
                      //  addDelay(2000);
                        player.setProgress("level6");
                        nextLevel();
                        break;
                    } else if (doorOne== 1) {

                        ChestArmour platedArmor = new ChestArmour("Plated Armor", 7, 6, 140, 35, R.drawable.platedarmour);

                        System.out.println("You choose not to open the door. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own " + platedArmor.getName() +  " behind.");
                        setPreviousAndMainText("You choose not to open the door. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own " + platedArmor.getName() +  " behind.");


                        addEquipableToEquipableList(platedArmor);


//                        player.setChestArmour(platedArmor);
                        System.out.println(player);
                        player.setProgress("level6");
                         nextLevel();
                        break;

                    } /* else if (doorOne== 2) {
                            System.out.println("Please enter your save mainActivity name.");
                            String savedFileName = userTextInputCollected;
                            save(savedFileName);
                            break; */

                                             else if (doorOne == 2) {
                    //System.out.println("Please enter your save game name.");
                    //String savedFileName = userTextInputCollected;
                    //save(userTextInputCollected);
                    save();
                    break;

                    /*

                        } else if (doorOne== 4) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;  */


                    } else if (doorOne == 3) {

                        load();
                        break;

                        } else {
                            // System.out.println("Please try again, your options are y or n to open the chest.");
                            //  doorOne = userChoice;
                            startDelayedTask(200000, true);
                        }
                        nextLevel();
                break;
            //        }

                case "level6":

                    for (Enemy enemy : enemiesStartingStats) {
                        System.out.println("enemiesStartingStats: " + enemy.toString());
                    }

                    enemy3 = userChoice;
 //                   while (player.getHealth() > 0) {
                        if (enemy3 == 0) {
                            System.out.println("\nYou attack " + loki.getEnemyName());
                            setPreviousAndMainText("You attack " + loki.getEnemyName());

                           // int currentWeaponDamage = player.getCurrentWeaponDamage();
                          //  int lokiHealth = loki.getEnemyHealth();

                            String levelSixVictory = "%s drops a key. You use it to open a chest. Inside is a %s.";

                            Weapon flamingSword = new Weapon("Flaming Sword", 24, 1000, 200 , 1, R.drawable.flamingsword);

                            bossBattle(loki, flamingSword, levelSixVictory, "level7", 150, null);

                            /*

                            while (player.getHealth() > 0) {

                                if (player.getCurrentWeaponDamage() >= loki.getEnemyHealth()) {
                       //             addDelay(2000);
                                    loki.enemyTakeDamage(player.getCurrentWeaponDamage());

                        //            enemiesStartingStats.remove(loki);
                        //            enemiesStartingStats.add(loki);

                                    System.out.println("You have killed " + loki.getEnemyName() + " and taken no damage.");
                                    setPreviousAndMainText("You have killed " + loki.getEnemyName() + " and taken no damage.");

                            //        Trouser chainMailTrousers = new Trouser("Chain Mail Trousers", 10, 10);


                                  //  System.out.println(loki.getEnemyName() + " drops a key. You use it to open a chest. Inside is a pair of " + chainMailTrousers.getArmourName() + " and a " + flamingSword.getName() + ".");
                                    setPreviousAndMainText(loki.getEnemyName() + " drops a key. You use it to open a chest. Inside is a " + flamingSword.getName() + ".");

                                    player.setCurrentWeapon(flamingSword);
                              //      player.setTrouser(chainMailTrousers);

                                    System.out.println(player);
                                    System.out.println("Please enter your save mainActivity name.");

    //                                String savedFileName = userTextInputCollected;
    //                                save(savedFileName);

                                    System.out.println("Congratulations you have defeated the boss and completed the game. Well done!");
                                    setPreviousAndMainText("Congratulations you have defeated the boss and completed the game. Well done!");
                                    player.setProgress("level7");

                                    for (Enemy enemy : enemiesStartingStats) {
                                        System.out.println("enemiesStartingStats: " + enemy.toString());
                                    }





                                    //nextLevel();
      //                              System.exit(1);
                                    break;
                                } else {
                                //    addDelay(2000);
                                    System.out.println("\nYou have damaged " + loki.getEnemyName());
                                    setPreviousAndMainText("You have damaged " + loki.getEnemyName());
                                    loki.enemyTakeDamage(player.getCurrentWeaponDamage());
                           //         enemiesStartingStats.remove(loki);
                          //          enemiesStartingStats.add(loki);
                                    System.out.println(loki.getEnemyName() + " now has " + loki.getEnemyHealth()+ " health.");
                                    setPreviousAndMainText(loki.getEnemyName() + " now has " + loki.getEnemyHealth() + " health.");
                                    System.out.println("\n" + loki.getEnemyName() + " has attacked you with " + loki.getEnemyDamage() + " damage.");
                                    setPreviousAndMainText(loki.getEnemyName() + " has attacked you with " + loki.getEnemyDamage() + " damage.");
                                    player.getArmour().reduceDurability(loki.getReduceDurability());
                                    player.getShirt().reduceDurability(loki.getReduceDurability());
                                    player.getShoe().reduceDurability(loki.getReduceDurability());
                                    player.getHelmet().reduceDurability(loki.getReduceDurability());
                                    player.getTrouser().reduceDurability(loki.getReduceDurability());
                                    System.out.println(player);
                                    player.takeDamage(loki.getEnemyDamage());
                                    setPreviousAndMainText("You now have " + player.getHealth() + " health left.");
//                        break;

                                }
//                    break;
                            }
                            if (player.getHealth() >0) {
                                nextLevel();
                            } else {
                                chooseNewOrLoad();
                            }
                            break;


                             */
                        } else if (enemy3 == 1) {
                            System.out.println("You choose not to attack. " + loki.getEnemyName() + " attacks you in the back as you run away.");
                            setPreviousAndMainText("You choose not to attack. " + loki.getEnemyName() + " attacks you in the back as you run away.");
                            player.takeDamage(loki.getEnemyDamage() / 2);
                            setPreviousAndMainText("You now have " + player.getHealth() + " health left.");
              //              player.setProgress("level7");
                            System.out.println("Your cowardly actions have not gone unnoticed, the King has thrown you in jail and you journey is at an end. Better luck next time.");

                            setPreviousAndMainText("Your cowardly actions have not gone unnoticed, the King has thrown you in jail and you journey is at an end. Better luck next time.");
                            player.setProgress("level7");

                            for (Enemy enemy : enemiesStartingStats) {
                                System.out.println("enemiesStartingStats: " + enemy.toString());
                            }

                            nextLevel();
                        //    System.exit(0);
                            break;

                  /*      } else if (enemy3== 2) {
                            System.out.println("Please enter your save mainActivity name.");
                            String savedFileName = userTextInputCollected;
                            save(savedFileName);
                            break; */
                        } else if (enemy3 == 2) {
                            //System.out.println("Please enter your save game name.");
                            //String savedFileName = userTextInputCollected;
                            //save(userTextInputCollected);
                            save();
                            break;

                        } else if (enemy3 == 3) {

                            load();
                            break;


                        } else if (enemy3== 4) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;
                        } else {
//                    progress = "level7";
                            //    System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
                            //    enemy2 = userChoice;
                            previousStageTextViewText = "";
                            startDelayedTask(200000, true);
                        }
 //                   }
 //           }
                nextLevel();
                break;





                case "level7":

                    for (Enemy enemy : enemiesStartingStats) {
                        System.out.println("enemiesStartingStats: " + enemy.toString());
                    }

                    enemy4 = userChoice;
                    if (enemy4 == 0 ) {
                        System.out.println("\nYou attack the " + werewolf.getEnemyName());

                        setPreviousAndMainText("You attack the " + werewolf.getEnemyName());

                        // int currentWeaponDamage = player.getCurrentWeaponDamage();
                        // int zombieKingHealth = zombieKing.getEnemyHealth();


                        UpgradeItem weights  = new UpgradeItem("Set of weights", 0, 0,0, 0, 2, 0, false);

                        String levelFourVictory = "The %s drops a key. You use it to open a chest. You discover %s gold and also some %s.";

                        battle(werewolf, null, levelFourVictory, "level8", 90, weights);


                        if (player.getHealth() >0) {
                            nextLevel();
                        } else {
                            chooseNewOrLoad();
                        }
                        break;
                    } else if (enemy4 == 1) {
                        setPreviousAndMainText("You choose not to attack the " + werewolf.getEnemyName() + ". The Zombie attacks you in the back as you run away.");
                        System.out.println("You choose not to attack the " + werewolf.getEnemyName() + ". The Zombie attacks you in the back as you run away.");
                        player.takeDamage(werewolf.getEnemyDamage() / 2);
                        player.setProgress("level8");
                        nextLevel();
                        break;
                   /*     } else if (enemy2 == 2) {
                            System.out.println("Please enter your save mainActivity name.");
                            String savedFileName = userTextInputCollected;
                            save(savedFileName);
                            break; */
                    } else if (enemy4 == 2) {
                        //System.out.println("Please enter your save game name.");
                        //String savedFileName = userTextInputCollected;
                        //save(userTextInputCollected);
                        save();
                        break; /*
                        } else if (enemy2 == 3) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;*/

                    } else if (enemy4 == 3) {

                        load();
                        break;

                    } else {
                        // System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
                        // enemy2 = userChoice;
                        //previousStageTextViewText = "";
                        startDelayedTask(200000, true);

                    }
                    //              }

                    nextLevel();
                    break;


                case "level8":
                    //                  while (true) {

                    for (Enemy enemy : enemiesStartingStats) {
                        System.out.println("enemiesStartingStats: " + enemy.toString());
                    }

                    chestThree = userChoice;
                    if (chestThree== 0) {
                        // addDelay(2000);


                        Shirt dragonScaleWeave = new Shirt("Dragon Scale Weave Shirt", 20, 15, 1000, 200, R.drawable.dragonscaleweave);

                        String formattedTextNonCombat = "";



                        setPreviousAndMainText("You open the chest to find a " + dragonScaleWeave.getName() + ".");
                        System.out.println("You open the chest to find a " + dragonScaleWeave.getName() + ". You put it on.");

                        addEquipableToEquipableList(dragonScaleWeave);

//                        player.setShirt(dragonScaleWeave);

                        System.out.println(player);
                        //  addDelay(2000);
                        player.setProgress("level9");
                        nextLevel();
                        break;
                    } else if (chestThree== 1) {

                        double chestThreeGold = 900;

                        System.out.println("You choose not to open the chest. The owner sees you and invites you to a game of cards. You agree and clean up! You win " + chestThreeGold + " gold.");
                        setPreviousAndMainText("You choose not to open the chest. The owner sees you and invites you to a game of cards. You agree and clean up! You win " + chestThreeGold + " gold.");


                        player.addPlayerGold(chestThreeGold);


                        System.out.println(player);
                        player.setProgress("level9");
                        nextLevel();
                        break;

                    } /* else if (doorOne== 2) {
                            System.out.println("Please enter your save mainActivity name.");
                            String savedFileName = userTextInputCollected;
                            save(savedFileName);
                            break; */

                    else if (chestThree == 2) {
                        //System.out.println("Please enter your save game name.");
                        //String savedFileName = userTextInputCollected;
                        //save(userTextInputCollected);
                        save();
                        break;

                    /*

                        } else if (doorOne== 4) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;  */


                    } else if (chestThree == 3) {

                        load();
                        break;

                    } else {
                        // System.out.println("Please try again, your options are y or n to open the chest.");
                        //  doorOne = userChoice;
                        startDelayedTask(200000, true);
                    }
                    nextLevel();
                    break;
                //        }

                case "level9":

                    for (Enemy enemy : enemiesStartingStats) {
                        System.out.println("enemiesStartingStats: " + enemy.toString());
                    }

                    enemy5 = userChoice;
                    //                   while (player.getHealth() > 0) {
                    if (enemy3 == 0) {
                        System.out.println("\nYou attack " + morgana.getEnemyName());
                        setPreviousAndMainText("You attack " + morgana.getEnemyName());

                        // int currentWeaponDamage = player.getCurrentWeaponDamage();
                        //  int lokiHealth = loki.getEnemyHealth();

                        String levelNineVictory = morgana.getEnemyName() + " drops their sceptre. Is smashes on the floor and emits a strange gas which you try to escape but inhale. Much to your surprise you feel invigorated. Your skin hardens, and underneath, your organs glow.";



                        bossBattle(morgana, null, levelNineVictory, "level10", 350, brokenSceptre);

                            /*

                            while (player.getHealth() > 0) {

                                if (player.getCurrentWeaponDamage() >= loki.getEnemyHealth()) {
                       //             addDelay(2000);
                                    loki.enemyTakeDamage(player.getCurrentWeaponDamage());

                        //            enemiesStartingStats.remove(loki);
                        //            enemiesStartingStats.add(loki);

                                    System.out.println("You have killed " + loki.getEnemyName() + " and taken no damage.");
                                    setPreviousAndMainText("You have killed " + loki.getEnemyName() + " and taken no damage.");

                            //        Trouser chainMailTrousers = new Trouser("Chain Mail Trousers", 10, 10);


                                  //  System.out.println(loki.getEnemyName() + " drops a key. You use it to open a chest. Inside is a pair of " + chainMailTrousers.getArmourName() + " and a " + flamingSword.getName() + ".");
                                    setPreviousAndMainText(loki.getEnemyName() + " drops a key. You use it to open a chest. Inside is a " + flamingSword.getName() + ".");

                                    player.setCurrentWeapon(flamingSword);
                              //      player.setTrouser(chainMailTrousers);

                                    System.out.println(player);
                                    System.out.println("Please enter your save mainActivity name.");

    //                                String savedFileName = userTextInputCollected;
    //                                save(savedFileName);

                                    System.out.println("Congratulations you have defeated the boss and completed the game. Well done!");
                                    setPreviousAndMainText("Congratulations you have defeated the boss and completed the game. Well done!");
                                    player.setProgress("level7");

                                    for (Enemy enemy : enemiesStartingStats) {
                                        System.out.println("enemiesStartingStats: " + enemy.toString());
                                    }





                                    //nextLevel();
      //                              System.exit(1);
                                    break;
                                } else {
                                //    addDelay(2000);
                                    System.out.println("\nYou have damaged " + loki.getEnemyName());
                                    setPreviousAndMainText("You have damaged " + loki.getEnemyName());
                                    loki.enemyTakeDamage(player.getCurrentWeaponDamage());
                           //         enemiesStartingStats.remove(loki);
                          //          enemiesStartingStats.add(loki);
                                    System.out.println(loki.getEnemyName() + " now has " + loki.getEnemyHealth()+ " health.");
                                    setPreviousAndMainText(loki.getEnemyName() + " now has " + loki.getEnemyHealth() + " health.");
                                    System.out.println("\n" + loki.getEnemyName() + " has attacked you with " + loki.getEnemyDamage() + " damage.");
                                    setPreviousAndMainText(loki.getEnemyName() + " has attacked you with " + loki.getEnemyDamage() + " damage.");
                                    player.getArmour().reduceDurability(loki.getReduceDurability());
                                    player.getShirt().reduceDurability(loki.getReduceDurability());
                                    player.getShoe().reduceDurability(loki.getReduceDurability());
                                    player.getHelmet().reduceDurability(loki.getReduceDurability());
                                    player.getTrouser().reduceDurability(loki.getReduceDurability());
                                    System.out.println(player);
                                    player.takeDamage(loki.getEnemyDamage());
                                    setPreviousAndMainText("You now have " + player.getHealth() + " health left.");
//                        break;

                                }
//                    break;
                            }
                            if (player.getHealth() >0) {
                                nextLevel();
                            } else {
                                chooseNewOrLoad();
                            }
                            break;


                             */
                    } else if (enemy3 == 1) {
                        System.out.println("You choose not to attack. " + loki.getEnemyName() + " attacks you in the back as you run away.");
                        setPreviousAndMainText("You choose not to attack. " + loki.getEnemyName() + " attacks you in the back as you run away.");
                        player.takeDamage(loki.getEnemyDamage() / 2);
                        setPreviousAndMainText("You now have " + player.getHealth() + " health left.");
                        //              player.setProgress("level7");
                        System.out.println("Your cowardly actions have not gone unnoticed, the King has thrown you in jail and you journey is at an end. Better luck next time.");

                        setPreviousAndMainText("Your cowardly actions have not gone unnoticed, the King has thrown you in jail and you journey is at an end. Better luck next time.");
                        player.setProgress("level7");

                        for (Enemy enemy : enemiesStartingStats) {
                            System.out.println("enemiesStartingStats: " + enemy.toString());
                        }

                        nextLevel();
                        //    System.exit(0);
                        break;

                  /*      } else if (enemy3== 2) {
                            System.out.println("Please enter your save mainActivity name.");
                            String savedFileName = userTextInputCollected;
                            save(savedFileName);
                            break; */
                    } else if (enemy3 == 2) {
                        //System.out.println("Please enter your save game name.");
                        //String savedFileName = userTextInputCollected;
                        //save(userTextInputCollected);
                        save();
                        break;

                    } else if (enemy3 == 3) {

                        load();
                        break;


                    } else if (enemy3== 4) {
                        System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                        System.exit(0);
                        break;
                    } else {
//                    progress = "level7";
                        //    System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
                        //    enemy2 = userChoice;
                        previousStageTextViewText = "";
                        startDelayedTask(200000, true);
                    }
                    //                   }
                    //           }
                    nextLevel();
                    break;





                default:

            }

                // Task completed
                Log.d("DelayedTask", "Task completed");
            }
        };

        if (applyDelay) {
            handler.postDelayed(delayedTask, time);
        } else {
            handler.post(delayedTask);
        }
    }

    private void removeDelay() {
        if (delayedTask != null) {
            handler.removeCallbacks(delayedTask);
            //cancelDelayedTask();
            Log.d("DelayedTask", "Delay removed, task executed immediately");

        }
        if (delayedTask != null) {
            //delayedTask.run(); // Execute the task immediately
            delayedTask = null;
        }
        startDelayedTask(0,false);
    }

    private void cancelDelayedTask() {
        if (delayedTask != null) {
            handler.removeCallbacks(delayedTask);
            delayedTask = null;
            Log.d("DelayedTask", "Task cancelled");
        }
    }





//    private void setPreviousAndMainText(String newMainText){
//        mainTextViewText = newMainText;
//        previousStageTextViewText = mainTextViewText;
//        previousPreviousStageTextViewText = previousStageTextViewText;
//
//
//        previousPreviousStageTextView.setText(previousPreviousStageTextViewText);
//        previousStageTextView.setText(previousStageTextViewText);
//        mainTextView.setText(mainTextViewText);
//
//
//    }


//    public void updatestorySoFar(String updateToStory) {

            private void setPreviousAndMainText(String updateToStory){

//        String updatedStorySoFar = storySoFar.concat("\n " + updateToStory);
//
//        storyUpdate = updateToStory;
//        storySoFar = updatedStorySoFar;
//
//        mainTextViewText = storyUpdate;
//        previousStageTextViewText = storySoFar;


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        storyUpdate = "\n" + updateToStory;

                        //addDelay(2000);

                        mainTextViewText = storyUpdate;
                        mainTextView.setText(mainTextViewText);

                        previousStageTextViewText = storySoFar;
                        previousStageTextView.setText(previousStageTextViewText);

                        String updatedStorySoFar = storySoFar.concat("\n\n" + updateToStory);

                        storySoFar = updatedStorySoFar;

                        storySoFarScrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                storySoFarScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        });


                    }
                });

//                storyUpdate = "\n" + updateToStory;
//
//                //addDelay(2000);
//
//                mainTextViewText = storyUpdate;
//                mainTextView.setText(mainTextViewText);
//
//                previousStageTextViewText = storySoFar;
//                previousStageTextView.setText(previousStageTextViewText);
//
//                String updatedStorySoFar = storySoFar.concat("\n\n" + updateToStory);
//
//                storySoFar = updatedStorySoFar;
//
//                storySoFarScrollView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        storySoFarScrollView.fullScroll(ScrollView.FOCUS_DOWN);
//                    }
//                });




    }


//    public void setTime(int time){
//        this.time = time;
//    }

    private void battle(Enemy levelEnemy, Equipable equipableReward, String rewardText, String progress, double goldReward, Item itemReward) {

        String formattedRewardText = "";

        if (equipableReward != null && itemReward != null) {
            formattedRewardText = String.format(rewardText, levelEnemy.getEnemyName(), equipableReward.getName(), goldReward, itemReward.getItemName());
        } else if (equipableReward != null && itemReward == null) {
            formattedRewardText = String.format(rewardText, levelEnemy.getEnemyName(), equipableReward.getName(), goldReward);
        } else if (equipableReward == null && itemReward != null) {
            formattedRewardText = String.format(rewardText, levelEnemy.getEnemyName(), goldReward, itemReward.getItemName());
        } else if (equipableReward == null && itemReward == null) {
            formattedRewardText = String.format(rewardText, goldReward);
        }

        while (player.getHealth() > 0) {
            levelEnemy.enemyTakeDamage(player.getCurrentWeaponDamage());

            if (levelEnemy.getEnemyHealth() <= 0) {
                try {
                    System.out.println("Battle Ended");

                    synchronized (this) {
                        while (waitingToProceed) {
                            System.out.println("Waiting to Proceed");
                            wait();
                        }
                        System.out.println("Proceeding");
                        waitingToProceed = true;
                    }

                    System.out.println("The Original" + levelEnemy.getOriginalEnemyStats(levelEnemy).getEnemyName() + " has " + levelEnemy.getOriginalEnemyStats(levelEnemy).getEnemyHealth() + " health.");

                    setPreviousAndMainText("You have killed the " + levelEnemy.getEnemyName() + " and taken no additional damage.");

                    if (equipableReward != null) {
                        addEquipableToEquipableList(equipableReward);
                    }

                    player.addPlayerGold(goldReward);

                    if (itemReward != null) {
                        if (itemReward.getUseOnAcquisition()) {
                            player.setMaxHealth(player.getMaxHealth() + itemReward.getMaxHealth());
                            player.addStrength(itemReward.getStrengthAmount());
                            player.heal(itemReward.getHealAmount());
                        } else {
                            addItemToItemList(itemReward);
                        }
                    }

                    player.setProgress(progress);
                    setPreviousAndMainText(formattedRewardText);

                    System.out.println(player);
                    System.out.println("Player progress is: " + player.getProgress());
                    System.out.println("Please enter your save game name.");

                    for (Enemy enemy : enemiesStartingStats) {
                        System.out.println("enemiesStartingStats: " + enemy.toString());
                    }

                    synchronized (this) {
                        waitingToProceed = false;
                        System.out.println("No longer waiting to proceed!");
                        notifyAll();
                    }

                    break;
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            } else {
                synchronized (this) {
                    waitingToProceed = true;
                    notifyAll();
                }

                updateUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        setPreviousAndMainText("You have damaged the " + levelEnemy.getEnemyName() + ".");
                        setPreviousAndMainText("The " + levelEnemy.getEnemyName() + " now has " + levelEnemy.getEnemyHealth() + " health.");
                        setPreviousAndMainText("The " + levelEnemy.getEnemyName() + " has attacked you with " + levelEnemy.getEnemyDamage() + " damage.");
                        player.takeDamage(levelEnemy.getEnemyDamage());
                    }
                });
            }
        }
    }


    private void bossBattle (Enemy levelEnemy, Equipable equipableReward, String rewardText, String progress, double goldReward, Item itemReward){


        String formattedRewardText = "";


        if (equipableReward != null && itemReward != null) {
            formattedRewardText = String.format(rewardText, levelEnemy.getEnemyName(), equipableReward.getName(), goldReward, itemReward.getItemName());
        } else if (equipableReward!= null && itemReward == null) {
            formattedRewardText = String.format(rewardText, levelEnemy.getEnemyName(), equipableReward.getName(), goldReward);
        } else if (equipableReward == null && itemReward != null) {
            formattedRewardText = String.format(rewardText, levelEnemy.getEnemyName(), goldReward, itemReward.getItemName());
        } else if (equipableReward == null && itemReward == null) {
            formattedRewardText = String.format(rewardText, goldReward);
        }


        while (player.getHealth() > 0) {


            if (player.getCurrentWeaponDamage() >= levelEnemy.getEnemyHealth()) {
//                userSubmitButton.setEnabled(false);
//                //addDelay(2000);
//                userSubmitButton.setEnabled(true);
                levelEnemy.enemyTakeDamage(player.getCurrentWeaponDamage());

                //    enemiesStartingStats.remove(levelEnemy);
                //    enemiesStartingStats.add(levelEnemy);

                System.out.println("The Original" + levelEnemy.getOriginalEnemyStats(levelEnemy).getEnemyName() + " has " + levelEnemy.getOriginalEnemyStats(levelEnemy).getEnemyHealth() + " health.");

                //           mainTextView.setText("You have killed the Zombie and taken no damage.");

                setPreviousAndMainText("You have killed the " + levelEnemy.getEnemyName() +" and taken no damage.");

                System.out.println("You have killed the " + levelEnemy.getEnemyName() +" and taken no damage.");
                //  Weapon longSword = new Weapon("Long Sword", 12);


                if (equipableReward != null) {
                    addEquipableToEquipableList(equipableReward);
                }
//                if (equipableReward!= null) {
//
//                    if (equipableReward instanceof Weapon) {
//                        player.setCurrentWeapon(equipableReward);
//                    } else if (equipableReward instanceof Helmet) {
//                        player.setHelmet(equipableReward);
//                    } else if (equipableReward instanceof ChestArmour) {
//                        player.setChestArmour(equipableReward);
//                    } else if (equipableReward instanceof Shirt) {
//                        player.setShirt(equipableReward);
//                    } else if (equipableReward instanceof Shoe) {
//                        player.setShoe(equipableReward);
//                    } else if (equipableReward instanceof Trouser) {
//                        player.setTrouser(equipableReward);
//                    }
//                }

                player.addPlayerGold(goldReward);



//            userSubmitButton.setEnabled(false);
                //    addDelay(2000);
//            userSubmitButton.setEnabled(true);




//            setPreviousAndMainText("The "+ levelEnemy.getEnemyName() +" was carrying a " + reward.getName() + " which you claim as your own.");
                setPreviousAndMainText(formattedRewardText);

                if (itemReward != null){
                    if (itemReward.getUseOnAcquisition() == true){
 //                       if (itemReward.getMaxHealth() > 0) {
                           player.setMaxHealth(player.getMaxHealth()+ itemReward.getMaxHealth());
 //                       } else if
                        player.addStrength(itemReward.getStrengthAmount());
                        player.heal(itemReward.getHealAmount());
                    } else {
                            addItemToItemList(itemReward);
                    }
                }

                //              mainTextView.setText("The Zombie was carrying a Long Sword which you claim as your own.");
 //               System.out.println("The "+ levelEnemy.getEnemyName() +" was carrying a " + equipableReward.getName() + " which you claim as your own.");


                System.out.println(player);
                player.setProgress(progress);
                System.out.println("Player progress is: " + player.getProgress());
                System.out.println("Please enter your save game name.");


                for (Enemy enemy : enemiesStartingStats) {
                    System.out.println("enemiesStartingStats: " + enemy.toString());
                }

                //                                    //String savedFileName = userTextInputCollected;
                //save(userTextInputCollected);
                break;
            } else {
                //            userSubmitButton.setEnabled(false);
//            addDelay(2000);
                //            userSubmitButton.setEnabled(true);
                System.out.println("\nYou have damaged the Zombie");

                setPreviousAndMainText("You have damaged the " + levelEnemy.getEnemyName() + ".");

                //      mainTextView.setText("You have damaged the Zombie");
                levelEnemy.enemyTakeDamage(player.getCurrentWeaponDamage());
                //    enemiesStartingStats.remove(levelEnemy);
                //    enemiesStartingStats.add(levelEnemy);
                //           userSubmitButton.setEnabled(false);
                //        addDelay(2000);
                //          userSubmitButton.setEnabled(true);
                //           System.out.println("The Original" + levelEnemy.getOriginalEnemyStats(levelEnemy).getEnemyName() + " has " + levelEnemy.getOriginalEnemyStats(levelEnemy).getEnemyHealth() + " health.");
                System.out.println("The Original Final " + levelEnemy.getOriginalEnemyStats(/*enemiesStartingStats,*/ levelEnemy).getEnemyName() + " has " + levelEnemy.getOriginalEnemyStats(/*enemiesStartingStats, */levelEnemy).getEnemyHealth() + " health.");

                System.out.println(enemiesStartingStats.toString());

                System.out.println("The " + levelEnemy.getEnemyName() + " now has " + levelEnemy.getEnemyHealth() + " health.");

                setPreviousAndMainText("The " + levelEnemy.getEnemyName() + " now has " + levelEnemy.getEnemyHealth() + " health.");

                //           mainTextView.setText("The Zombie now has " + zombieHealth + " health.");
                //           userSubmitButton.setEnabled(false);
                //           addDelay(2000);
                //           userSubmitButton.setEnabled(true);








                setPreviousAndMainText("The " + levelEnemy.getEnemyName() + " has attacked you with " + levelEnemy.getEnemyDamage() + " damage.");

                //       mainTextView.setText("The Zombie has attacked you with " + zombieDamage + " damage.");
                System.out.println("\nThe " + levelEnemy.getEnemyName() + " has attacked you with " + levelEnemy.getEnemyDamage() + " damage.");
                player.takeDamage(levelEnemy.getEnemyDamage());


                player.getChestArmour().reduceDurability(morgana.getReduceDurability(), MainActivity.this);
                player.getShirt().reduceDurability(morgana.getReduceDurability(), MainActivity.this);
                player.getShoe().reduceDurability(morgana.getReduceDurability(), MainActivity.this);
                player.getHelmet().reduceDurability(morgana.getReduceDurability(), MainActivity.this);
                player.getTrouser().reduceDurability(morgana.getReduceDurability(), MainActivity.this);

                setPreviousAndMainText("You now have " + player.getHealth() + " health left.");


            }
        }

    }


    private void addItemToItemList(Item itemToAdd){

        itemList.add(itemToAdd);

        AlertDialog.Builder addItemDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        addItemDialogBuilder.setCancelable(true);
        addItemDialogBuilder.setMessage("You have collected a " + itemToAdd.getItemName() + ".");

       addItemDialogBuilder.create().show();

    }


private void chooseItem(){



ArrayList<String> itemNames = new ArrayList<>();

    itemNames.add(0, "Cancel Item Use");

        if (!itemList.isEmpty()){
            for (Item item : itemList){
                itemNames.add(item.getItemName());
            }
        }


        AlertDialog.Builder chooseItemDialogBuilder = new AlertDialog.Builder(MainActivity.this);
    chooseItemDialogBuilder.setCancelable(true);
    chooseItemDialogBuilder.setTitle("Which Item would you like to use?");


    int itemListSize = itemNames.size();

    String[] itemsInList = new String[itemListSize];

//        itemsInList[0] = "Cancel Item Use";

    for (int i = 0; i < itemListSize; i++) {
        itemsInList[i] = itemNames.get(i);
    }


    chooseItemDialogBuilder.setItems(itemsInList, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.d("Test Dialog", "Item List: " + which);
//            itemSelected = itemList.get(which-1);

            Item itemChosen;

            if (which > 0) {

//                try {

                    itemChosen = itemList.get(which-1);

                    System.out.println("You have chosen to use " + itemList.get(which-1).getItemName());

//                    previousPreviousStageTextViewText = "";
//                    previousStageTextViewText = "";
                    userChoice = -1;

//                } catch (IOException | ClassNotFoundException e) {
//
//
//                }

                useItem(itemChosen);

            } else /*if (which == 0 && player != null)*/ {
                dialog.dismiss();
    //        } else {
    //            dialog.dismiss();
    //            chooseNewOrLoad();
            }



        }
    });

    //        if (saveGamesSize > 0) {

    chooseItemDialogBuilder.create().show();

}

public void useItem(Item itemChosen){


        AlertDialog.Builder useItemDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        useItemDialogBuilder.setCancelable(false);
        useItemDialogBuilder.setTitle("Confirm Item Use");
        useItemDialogBuilder.setMessage("You have chosen " + itemChosen.getItemName() + ". Are you sure you would like to use this item?");

        useItemDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

 //               if (itemChosen.getHealAmount() > 0){
                
                player.setMaxHealth(player.getMaxHealth()+ itemChosen.getMaxHealth());
                //                       } else if
                player.addStrength(itemChosen.getStrengthAmount());
                player.heal(itemChosen.getHealAmount());

                    itemList.remove(itemChosen);

            }
        });

        useItemDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


    useItemDialogBuilder.create().show();
}

public Enemy getOriginalEnemyStat (ArrayList<Enemy> originalEnemyStats, Enemy currentEnemy) {

        int index = originalEnemyStats.indexOf(currentEnemy);

        Enemy currentEnemyOriginalStats = originalEnemyStats.get(index);

        return currentEnemyOriginalStats;
}

public Enemy getEnemyForBattle(ArrayList<Enemy> enemiesVariableStats, Enemy currentEnemy) {
        int index = enemiesVariableStats.indexOf(currentEnemy);

        Enemy currentEnemyVariableStats = enemiesVariableStats.get(index);

        return currentEnemyVariableStats;

}

public void switchToCharacterStats () {

    if (player != null) {

        //                   new MyAlertDialog(MainActivity.this, MainActivity.this);

        Intent intentCharacter = new Intent(MainActivity.this, CharacterActivity.class);
        intentCharacter.putExtra("player", player);

        startActivity(intentCharacter);
        //                   finish();

    }

}

    public void switchToEnemyStats(){

        if (currentEnemy != null) {

            //                   new MyAlertDialog(MainActivity.this, MainActivity.this);
            Intent intentEnemy = new Intent(MainActivity.this, EnemyActivity.class);
            intentEnemy.putExtra("currentEnemy", currentEnemy);

            startActivity(intentEnemy);
            //                   finish();


        }

    }


    public void switchToShop (ArrayList<Equipable> shopInventory, String shopName, ArrayList<Equipable> playerEquipablesInventory) {

        if (player != null) {

            //                   new MyAlertDialog(MainActivity.this, MainActivity.this);

            Intent intentShop = new Intent(MainActivity.this, ShopActivity.class);
            intentShop.putExtra("player", player);
            intentShop.putExtra("shopInventory", shopInventory);
            intentShop.putExtra("shopName", shopName);
            intentShop.putExtra("playerEquipablesInventory", playerEquipablesInventory);
            startActivity(intentShop);
            //                   finish();

        }

    }



    private void chooseEquipable(){
        AlertDialog.Builder chooseEquipableFromPlayerInventoryBuilder = new AlertDialog.Builder(MainActivity.this);

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_equipable_in_player_inventory, null);

        chooseEquipableFromPlayerInventoryBuilder.setView(dialogView);

        RecyclerView recyclerView = dialogView.findViewById(R.id.btn_dialog_equipable_in_player_inventory_equip_item);

        ArrayList<Equipable> generatedPlayerEquipables = new ArrayList<>();

        for (Equipable equipable : equipableArrayList) {
            generatedPlayerEquipables.add(equipable);
        }

        EquipableAdapter equipableAdapter = new EquipableAdapter(generatedPlayerEquipables, MainActivity.this);
        recyclerView.setAdapter(equipableAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        AlertDialog alertDialog = chooseEquipableFromPlayerInventoryBuilder.create();
        alertDialog.show();

        Button backButton = dialogView.findViewById(R.id.btn_dialog_equipable_in_player_inventory_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        equipableAdapter.setOnEquipClickListener(new EquipableAdapter.OnEquipClickListener() {
            @Override
            public void onEquipClick(Equipable equipable) {
                System.out.println("You have chosen an Equipable to equip");
                useEquipable(equipable);
                alertDialog.dismiss();
            }
        });


    }










    public void useEquipable(Equipable equipableChosen/*, int arrayListIndex*/){


        AlertDialog.Builder useEquipableDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        useEquipableDialogBuilder.setCancelable(false);
        useEquipableDialogBuilder.setTitle("Confirm Item Use");
        useEquipableDialogBuilder.setMessage("You have chosen " + equipableChosen.getName() + ". Are you sure you would like to equip this equipable?");

        useEquipableDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //               if (itemChosen.getHealAmount() > 0){


                if (equipableChosen instanceof Weapon) {
                    addEquipableToEquipableList(player.getWeapon());
                    player.setCurrentWeapon(equipableChosen);
                    equipableArrayList.remove(/*arrayListIndex*/equipableChosen);
                } else if (equipableChosen instanceof Helmet) {
                    addEquipableToEquipableList(player.getHelmet());
                    player.setHelmet(equipableChosen);
                    equipableArrayList.remove(/*arrayListIndex*/equipableChosen);
                } else if (equipableChosen instanceof ChestArmour) {
                    addEquipableToEquipableList(player.getChestArmour());
                    player.setChestArmour(equipableChosen);
                    equipableArrayList.remove(/*arrayListIndex*/equipableChosen);
                } else if (equipableChosen instanceof Shirt) {
                    addEquipableToEquipableList(player.getShirt());
                    player.setShirt(equipableChosen);
                    equipableArrayList.remove(/*arrayListIndex*/equipableChosen);
                } else if (equipableChosen instanceof Shoe) {
                    addEquipableToEquipableList(player.getShoe());
                    player.setShoe(equipableChosen);
                    equipableArrayList.remove(/*arrayListIndex*/equipableChosen);
                } else if (equipableChosen instanceof Trouser) {
                    addEquipableToEquipableList(player.getTrouser());
                    player.setTrouser(equipableChosen);
                    equipableArrayList.remove(/*arrayListIndex*/equipableChosen);
                } else if (equipableChosen instanceof Shield) {
                    addEquipableToEquipableList(player.getShield());
                    player.setShield(equipableChosen);
                    equipableArrayList.remove(/*arrayListIndex*/equipableChosen);

                }


            }
        });

        useEquipableDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        useEquipableDialogBuilder.create().show();
    }


 /*   public void equipItemChoice(Equipable itemToEquip){

//        Iterator<Equipable> iterator = equipableArrayList.iterator();
//        while (iterator.hasNext()) {
//            Equipable currentEquipable = iterator.next();
//if (itemToEquip == currentEquipable){
//    iterator.remove();

    if (itemToEquip instanceof Weapon) {
        addEquipableToEquipableList(player.getWeapon());
        player.setCurrentWeapon(itemToEquip);
    } else if (itemToEquip instanceof Helmet) {
        addEquipableToEquipableList(player.getHelmet());
        player.setHelmet(itemToEquip);
    } else if (itemToEquip instanceof ChestArmour) {
        addEquipableToEquipableList(player.getChestArmour());
        player.setChestArmour(itemToEquip);
    } else if (itemToEquip instanceof Shirt) {
        addEquipableToEquipableList(player.getShirt());
        player.setShirt(itemToEquip);
    } else if (itemToEquip instanceof Shoe) {
        addEquipableToEquipableList(player.getShoe());
        player.setShoe(itemToEquip);
    } else if (itemToEquip instanceof Trouser) {
        addEquipableToEquipableList(player.getTrouser());
        player.setTrouser(itemToEquip);
    }

//    break;

//}
//                }
            }
            */

    public void addEquipableToEquipableListNoDuplicates(Equipable itemToAddToEquipableList){

        boolean itemInArrayList = false;

        for (Equipable equipable : equipableArrayList){
            if (itemInArrayList == true){
                break;
            } else if (itemToAddToEquipableList == equipable){
                itemInArrayList = true;
                break;
            } else {
                equipableArrayList.add(itemToAddToEquipableList);
            }

        }
    }

    public ArrayList getPlayerEquipablesList(){
        return equipableArrayList;
    }

    public void addEquipableToEquipableList(Equipable itemToAddToEquipableList){

        equipableArrayList.add(itemToAddToEquipableList);

        AlertDialog.Builder collectedEquipableDialogBuiler = new AlertDialog.Builder(MainActivity.this);

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_collect_equipable, null);

        collectedEquipableDialogBuiler.setView(dialogView);

        collectedEquipableDialogBuiler.setCancelable(true);

     /*   collectedEquipableDialogBuiler.setMessage("You have collected " +
                itemToAddToEquipableList.getName() +
                ". It has been added to your equipable items.");
     */

     TextView  collectEquipableMessage;

     collectEquipableMessage = dialogView.findViewById(R.id.tv_dialog_collect_equipable_message);

     collectEquipableMessage.setText("You have collected " + itemToAddToEquipableList.getName() + ". It has been added to your equipable items.");

     Button collectEquipableGoldValue;

     collectEquipableGoldValue = dialogView.findViewById(R.id.btn_dialog_collect_equipable_gold_value);

     collectEquipableGoldValue.setText("Gold value: \n" + itemToAddToEquipableList.getGoldSellValue());

     ImageButton collectEquipableImage;

     collectEquipableImage = dialogView.findViewById(R.id.imbtn_dialog_collect_equipable_image);

     collectEquipableImage.setImageResource(itemToAddToEquipableList.getImageId());

     Button collectEquipableDescription;

     collectEquipableDescription = dialogView.findViewById(R.id.btn_dialog_collect_equipable_description);

     collectEquipableDescription.setText(itemToAddToEquipableList.toString());


     collectedEquipableDialogBuiler.create().show();
    }


    public void lowHealthMessage(){
        AlertDialog.Builder lowHealthDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        lowHealthDialogBuilder.setCancelable(true);

        String lowHealth = String.format("Your health is less than 30%% of your max health. You have %.2f left. Please heal as soon as possible!", player.getHealth());

        lowHealthDialogBuilder.setMessage(lowHealth);
        lowHealthDialogBuilder.create().show();
    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save necessary data to restore state
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state from the saved bundle
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.clearMainActivityInstance();
    }


    public void goToShop(){

    }

        public void addEquipablesToWorkshop(ArrayList<Equipable> workShopName, Equipable... equipables) {
            for (Equipable equipable : equipables) {
                workShopName.add(equipable);
            }
        }

        // Example usage


                    // Add more items as needed


    public void enableAllButtons(){

        userSubmitButton.setEnabled(true);
        equipEquipableButton.setEnabled(true);
        useItemButton.setEnabled(true);
        userExitButton.setEnabled(true);
        userResponse0Button.setEnabled(true);
        userResponse1Button.setEnabled(true);
        userResponse2Button.setEnabled(true);
        userResponse3Button.setEnabled(true);
        switchToCharacter.setEnabled(true);
        switchToEnemy.setEnabled(true);
        switchToLastShop.setEnabled(true);

    }


    public void disableAllButtons(){

        userSubmitButton.setEnabled(false);
        equipEquipableButton.setEnabled(false);
        useItemButton.setEnabled(false);
        userExitButton.setEnabled(false);
        userResponse0Button.setEnabled(false);
        userResponse1Button.setEnabled(false);
        userResponse2Button.setEnabled(false);
        userResponse3Button.setEnabled(false);
        switchToCharacter.setEnabled(false);
        switchToEnemy.setEnabled(false);
        switchToLastShop.setEnabled(false);
//        nextButton.setEnabled(false);

    }









}
