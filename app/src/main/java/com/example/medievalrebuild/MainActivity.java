package com.example.medievalrebuild;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;




import com.example.medievalrebuild.ArmourFiles.Armour;
import com.example.medievalrebuild.ArmourFiles.Helmet;
import com.example.medievalrebuild.ArmourFiles.Shirt;
import com.example.medievalrebuild.ArmourFiles.Shoe;
import com.example.medievalrebuild.ArmourFiles.Trouser;
import com.example.medievalrebuild.Enemies.BossEnemy;
import com.example.medievalrebuild.Enemies.Enemy;
import com.example.medievalrebuild.Game.Art;
import com.example.medievalrebuild.Game.Player;
import com.example.medievalrebuild.Weapons.Weapon;

import java.io.Serializable;
import java.io.*;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements Serializable, MyAlertDialog.DialogCallBack {

    Art art;

    private Handler handler = new Handler();

    ImageView mainImageView;

    TextView mainTextView;

    String mainTextViewText = "";

    TextView previousStageTextView;

    String previousStageTextViewText = "";

    TextView previousPreviousStageTextView;

    String previousPreviousStageTextViewText = "";


    Button userResponse0Button;

    Button userResponse1Button;

    Button userResponse2Button;

    Button userResponse3Button;

    Button userExitButton;

    Button userSubmitButton;

    Button switchToCharacter;

    private Context context;

//    private int time;

    private boolean waitingForAnswer = true;

    CharacterActivity characterActivity;

//    Button switchToStory;

//    EditText userTextInput;

    int userChoice = -1;


    private static final long serialVersionUID = 1L;

    /* Instance Variables */
    private Player player;

//    private Game.Player player;

    private Enemy enemy;

//    private String progress;

    private int chest;
    private int chestTwo;

    private int doorOne;
    private int zombie;

    private int enemy2;

    private int enemy3;
    private int zombieHealth = 4;
    private int zombieDamage = 2;

//    private String userTextInputCollected;

    private boolean validAnswer = false;

    private String newOrLoadSelected;

    final String[] options = {"New Game", "Load"};
    Enemy zombieKing = new Enemy("Zombie King", 20, 20);

    BossEnemy loki = new BossEnemy("Loki God Of Mischeif", 35, 25, 4);

    String userTextInput;


    //    Level1 level1;
    //private static String progress = "level1";
    boolean newGame = false;

    private Runnable delayedTask;

    private Handler handlerDelayTask = new Handler(Looper.getMainLooper());

    //MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.medieval_marvels_and_might_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);


        int autoSizeMinTextSize = 6;
        int autoSizeMaxTextSize = 30;
        int autoSizeStepGranularity = 1;
        int unit = TypedValue.COMPLEX_UNIT_SP;

        mainImageView = findViewById(R.id.iv_main_image);

        previousPreviousStageTextView = findViewById(R.id.tv_previous_previous_stage_user_text);
        previousStageTextView = findViewById(R.id.tv_previous_stage_user_text);
        mainTextView = findViewById(R.id.tv_main_user_text);


        switchToCharacter = findViewById(R.id.btn_main_character);

//        switchToStory = findViewById(R.id.btn_character_story);

        //   Log.d("MainActivity", "mainTextView: " + mainTextView);

        userResponse0Button = findViewById(R.id.btn_main_0);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userResponse0Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userResponse1Button = findViewById(R.id.btn_main_1);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userResponse1Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userResponse2Button = findViewById(R.id.btn_main_2);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userResponse2Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userResponse3Button = findViewById(R.id.btn_main_3);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userResponse3Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userExitButton = findViewById(R.id.btn_main_exit);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userExitButton, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        userSubmitButton = findViewById(R.id.btn_main_submit);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                userSubmitButton, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);


        previousStageTextViewText = "";
        setPreviousAndMainText("Welcome to Medieval Marvels!");

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
                selectOption(2);
            }
        });

        userResponse3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption(3);
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
                //               }
            }
        });


        userExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption(4);
            }

        });

        switchToCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (player != null) {

 //                   new MyAlertDialog(MainActivity.this, MainActivity.this);
                    Intent intent = new Intent(MainActivity.this, CharacterActivity.class);
                    intent.putExtra("player", player);

                    startActivity(intent);
 //                   finish();


                }
            }

        });

        userResponse0Button.setText("Yes");
        userResponse1Button.setText("No");
        userResponse2Button.setText("Save");
        userResponse3Button.setText("Load");
        userExitButton.setText("Exit");
        userSubmitButton.setText("Submit");


        chooseNewOrLoad();

    }


    public void chooseNewOrLoad() {

        if (player != null){

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
                        //chooseLoadGame();
                    }

                }
            });
            chooseGameModeDialogBuilder.create().show();

        } else {


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
                        //chooseLoadGame();
                    }

                }
            });
            chooseGameModeDialogBuilder.create().show();
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
            userExitButton.setText("Exit");
            validAnswer = true;
        } else if (answerSelection == 1) {
            System.out.println("Answer Selection = " + answerSelection);
            userResponse0Button.setText("Yes");
            userResponse1Button.setText("✔" + "No");
            userResponse2Button.setText("Save");
            userResponse3Button.setText("Load");
            userExitButton.setText("Exit");
            validAnswer = true;
        } else if (answerSelection == 2) {
            System.out.println("Answer Selection = " + answerSelection);
            userResponse0Button.setText("Yes");
            userResponse1Button.setText("No");
            userResponse2Button.setText("✔ " + "Save");
            userResponse3Button.setText("Load");
            userExitButton.setText("Exit");
            validAnswer = true;
        } else if (answerSelection == 3) {
            System.out.println("Answer Selection = " + answerSelection);
            userResponse0Button.setText("Yes");
            userResponse1Button.setText("No");
            userResponse2Button.setText("Save");
            userResponse3Button.setText("✔ " + "Load");
            userExitButton.setText("Exit");
            validAnswer = true;
        } else if (answerSelection == 4) {
            System.out.println("Answer Selection = " + answerSelection);
            userResponse0Button.setText("Yes");
            userResponse1Button.setText("No");
            userResponse2Button.setText("Save");
            userResponse3Button.setText("Load");
            userExitButton.setText("✔ " + "Exit");
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
        userExitButton.setText("Exit");



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

            MyAlertDialog myAlertDialogCreatePlayer = new MyAlertDialog(this, this, "Please enter your character name", false);
            }

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

            MyAlertDialog myAlertDialogSavePlayer = new MyAlertDialog(this, this, "Please enter your save name", true);
        }

      //  return userTextInput;
    }



    @Override
    public void onTextEnteredForOtherPurpose(String enteredText){

        if (!enteredText.equalsIgnoreCase("")){

            userTextInput = enteredText;

            String chosenName = userTextInput;
            String fileName = chosenName+".svr";
            try {


                File internalStorageDir = context.getFilesDir();
                File saveFile = new File(internalStorageDir, fileName);

                FileOutputStream fileOutputStreamSavePlayer = new FileOutputStream(saveFile);
                ObjectOutputStream playerSaver = new ObjectOutputStream(fileOutputStreamSavePlayer);

                playerSaver.writeObject(player);

                playerSaver.close();
                fileOutputStreamSavePlayer.close();
//                playerSaver.writeObject(progress);
                System.out.println("We've just saved your game with file name " + chosenName);

                //return fileName;
            } catch (IOException e) {
                e.printStackTrace();
                String cannotSave = "Unable to save game.";
                System.out.println(cannotSave);

                //return cannotSave;
            }
            // End of save
            // would be preferable to save as a console given name so different saves can be made and loaded when needed.

            startDelayedTask(1000, true);

        } else {
            save();
        }



    }

    public Player getPlayer() {

        return player;
    }


    public void nextLevel() {

//        delayTask = new DelayTask();
        System.out.println("nextLevel() called");
        String progress = player.getProgress();
        System.out.println(progress);
        waitingForAnswer = true;
        userChoice = -1;


        switch (progress) {
            case "level1":

           //    while (progress.equalsIgnoreCase("level1")) {


//        boolean level1InProgress = true;
                    //while (progress.equalsIgnoreCase("level1")) {
                    //player.setProgress("level1");
//                    mainActivity.addDelay(2000);
                userSubmitButton.setEnabled(false);
             //   addDelay(2000);
                userSubmitButton.setEnabled(true);

                previousStageTextViewText = previousStageTextViewText + " " + player.getName();
                setPreviousAndMainText("You discover a chest. Would you like to open it?");



                    System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
                    //                  String chest = console.next().toLowerCase();

                    userChoice = -1;

                System.out.println(userChoice);

//                waitingForAnswer = true;
//                while (waitingForAnswer) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

                waitingForAnswer = true;
                System.out.println("waiting for answer " + waitingForAnswer);

                int chest = userChoice;
                System.out.println("chest int  " + chest);


                chest = 1;
                System.out.println("chest int  " + chest);






//                while (waitingForAnswer == true) {
//                    try {
//                        Thread.sleep(100); // Add a small delay to avoid excessive CPU usage
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

            //    }

              //  waitingForAnswer = false;


//                    System.out.println("chest after waiting for answer while statement  " + chest);
//                    if (chest == 0) {
//                        System.out.println("chest int  " + chest);
//                        System.out.println("You open the chest to find a helmet. You put it on.");
//                        System.out.println("You choose option 0");
//
//                        setPreviousAndMainText("You open the chest to find a helmet. You put it on.");
//
//                        //mainTextView.setText("You open the chest to find a helmet. You put it on.");
//                        Helmet platedHelmet = new Helmet("Plated ArmourFiles.Helmet", 5, 8);
//                        player.setHelmet(platedHelmet);
//                        //System.out.println(player);
//                        //addDelay(2000);
//                        player.setProgress("level2");
//                      //  startDelayedTask(10000, true);
//                       // removeDelay();
//                    } else {
//
//                    }


                 //   break;

              //  }


                startDelayedTask(200000, true);




                break;
            case "level2":
                userSubmitButton.setEnabled(false);
             //   addDelay(5000);
                userSubmitButton.setEnabled(true);
         //       mainTextView.setText("You have reached the start of level 2");
             //   System.out.println("You have reached the start of level 2");




             //   setPreviousAndMainText("You have reached the start of level 2");


                userSubmitButton.setEnabled(false);
           //     addDelay(5000);
                userSubmitButton.setEnabled(true);




   //             handler.postDelayed(new Runnable() {
   //                 @Override
   //                 public void run() {


                   System.out.println("\nYou discover a Zombie. The Zombie has " + zombieHealth + " health and " + zombieDamage + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
  //              mainTextView.setText("\nYou discover a Zombie. The Zombie has " + zombieHealth + " health and " + zombieDamage + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
                        setPreviousAndMainText("You discover a Zombie. The Zombie has " + zombieHealth + " health and " + zombieDamage + " damage. Would you like to attack it?");

                    zombie = userChoice;


   //                 }
   //                 }, 5000);



                startDelayedTask(200000, true);



                break;

            case "level3":

        //        while (player.getProgress().equalsIgnoreCase("level3")) {


         //           handler.postDelayed(new Runnable() {
         //                                   @Override
         //                                   public void run() {

                    System.out.println("\nYou discover a chest. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
                    setPreviousAndMainText("You discover a chest. Would you like to open it?");


                    chestTwo = userChoice;

         //                                   }
         //           }, 5000);

                    startDelayedTask(200000, true);

     //   }


                break;

            case "level4":

       //             handler.postDelayed(new Runnable() {
       //                 @Override
       //                 public void run() {


         //       while (player.getProgress().equalsIgnoreCase("level4")) {
                    System.out.println("\nYou discover a " + zombieKing.getEnemyName() + " .The Zombie King has " + zombieKing.getEnemyHealth() + " health and " + zombieKing.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");

                   setPreviousAndMainText("You discover a " + zombieKing.getEnemyName() + " .The Zombie King has " + zombieKing.getEnemyHealth() + " health and " + zombieKing.getEnemyDamage() + " damage. Would you like to attack it?");

                    enemy2 = userChoice;

     //                   }
    //                }, 5000);

                    startDelayedTask(200000, true);


                    break;
      //          }


            case "level5":


                    System.out.println("\nYou discover a door. Would you like to open it? Type y for yes, n for no, s for save, x for exit.");
                    setPreviousAndMainText("You discover a door. Would you like to open it?");
                    doorOne = userChoice;

                    startDelayedTask(200000, true);

                    break;

            case "level6":

                    System.out.println("\nYou discover " + loki.getEnemyName() + " .He has " + loki.getEnemyHealth() + " health and " + loki.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
                    setPreviousAndMainText("You discover " + loki.getEnemyName() + " .He has " + loki.getEnemyHealth() + " health and " + loki.getEnemyDamage() + " damage. Would you like to attack it?");

                    enemy3 = userChoice;

                    System.out.println("Before level 2 part 2 called");
                    startDelayedTask(200000, true);
                System.out.println("After level 2 part 2 called");

                    break;


            case "level7" :
                setPreviousAndMainText("You have reached the end of the game");
                userSubmitButton.setEnabled(false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + progress);

        }
//            break;



            /*case "level6":
                while (player.getProgress().equalsIgnoreCase("level6")) {
                    System.out.println("\nYou discover " + loki.getEnemyName() + " .He has " + loki.getEnemyHealth() + " health and " + loki.getEnemyDamage() + " damage. Would you like to attack it? Type y for yes, n for no, s for save, x for exit.");
                    enemy3 = userChoice;

                    while (true) {
                        if (enemy3== 0) {
                            System.out.println("\nYou attack " + loki.getEnemyName());
                            int currentWeaponDamage = player.getCurrentWeaponDamage();

                            int lokiHealth = loki.getEnemyHealth();
                            while (true) {

                                if (currentWeaponDamage >= lokiHealth) {
                                    addDelay(2000);
                                    lokiHealth -= currentWeaponDamage;
                                    System.out.println("You have killed " + loki.getEnemyName() + " and taken no damage.");
                                    Trouser chainMailTrousers = new Trouser("Chain Mail Trousers", 10, 10);
                                    Weapon flamingSword = new Weapon("Flaming Sword", 24);
                                    System.out.println(loki.getEnemyName() + " drops a key. You use it to open a chest. Inside is a pair of " + chainMailTrousers.getArmourName() + " and a " + flamingSword.getName() + ".");
                                    player.setCurrentWeapon(flamingSword);
                                    player.setTrouser(chainMailTrousers);
                                    System.out.println(player);
                                    System.out.println("Please enter your save mainActivity name.");
                                    String savedFileName = userTextInputCollected;
                                    save(savedFileName);
                                    System.out.println("Congratulations you have defeated the boss and completed the  Well done!");
                                    player.setProgress("level7");
                                    System.exit(1);
                                    break;
                                } else {
                                    addDelay(2000);
                                    System.out.println("\nYou have damaged the " + loki.getEnemyName());
                                    lokiHealth -= currentWeaponDamage;
                                    System.out.println("The " + loki.getEnemyName() + " now has " + lokiHealth + " health.");
                                    System.out.println("\n" + loki.getEnemyName() + "has attacked you with " + loki.getEnemyDamage() + " damage.");
                                    player.getArmour().reduceDurability(loki.getReduceDurability());
                                    player.getShirt().reduceDurability(loki.getReduceDurability());
                                    player.getShoe().reduceDurability(loki.getReduceDurability());
                                    player.getHelmet().reduceDurability(loki.getReduceDurability());
                                    player.getTrouser().reduceDurability(loki.getReduceDurability());
                                    System.out.println(player);
                                    player.takeDamage(loki.getEnemyDamage());
//                        break;

                                }
//                    break;
                            }
                            break;
                        } else if (enemy3== 1) {
                            System.out.println("You choose not to attack. " + loki.getEnemyName() + " attacks you in the back as you run away.");
                            player.takeDamage(loki.getEnemyDamage() / 2);
                            player.setProgress("level7");
                            System.out.println("Your cowardly actions have not gone unnoticed, the King has thrown you in jail and you journey is at an end. Better luck next time.");
                            System.exit(0);
                            break;

                        } else if (enemy3== 2) {
                            System.out.println("Please enter your save mainActivity name.");
                            String savedFileName = userTextInputCollected;
                            save(savedFileName);
                            break;

                        } else if (enemy3== 4) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;
                        } else {
//                    progress = "level7";
                            //    System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
                            //    enemy2 = userChoice;
                        }
                    }
                }
                break;

*/
     //   }

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

                    userSubmitButton.setEnabled(false);
             //       addDelay(2000);
                    userSubmitButton.setEnabled(true);
                    System.out.println("You choose option 0");
          //          mainTextView.setText("You open the chest to find a helmet. You put it on.");

                            setPreviousAndMainText("You open the chest to find a helmet. You put it on.");


                    Helmet platedHelmet = new Helmet("Plated Helmet", 5, 8);
                    player.setHelmet(platedHelmet);

       //                 }
      //              }, 5000);

                    //System.out.println(player);
                   // addDelay(2000);
                    player.setProgress("level2");
                    userSubmitButton.setEnabled(false);
              //      addDelay(10000);
                    userSubmitButton.setEnabled(true);






                } else if (chest == 1) {
                    System.out.println("Chest is : " + chest);
                    System.out.println("You choose not to open the chest. An onlooker observes your honesty and gives you a pair of boots.");
                    System.out.println("You choose option 1");
              //      mainTextView.setText("You choose not to open the chest. An onlooker observes your honesty and gives you a pair of boots.");

                    setPreviousAndMainText("You choose not to open the chest. An onlooker observes your honesty and gives you a pair of boots.");

                    Shoe leatherboots = new Shoe("Leather Boots", 10, 10);
                    player.setShoe(leatherboots);
                    //System.out.println(player);
                    player.setProgress("level2");
                    //userChoice = -1;
                    //break;
                } else {
                    startDelayedTask(200000, true);
                }
                nextLevel();
                break;

                case "level2":
                        zombie = userChoice;
                        if (zombie == 0) {
                            userSubmitButton.setEnabled(false);
                  //          addDelay(2000);
                            userSubmitButton.setEnabled(true);
                 //           mainTextView.setText("You attack the zombie");

                            setPreviousAndMainText("You attack the zombie");

                            System.out.println("\nYou attack the zombie");
                            int currentWeaponDamage = player.getCurrentWeaponDamage();

                            while (player.getHealth() > 0) {
                                if (currentWeaponDamage >= zombieHealth) {
                                    userSubmitButton.setEnabled(false);
                                //    addDelay(2000);
                                    userSubmitButton.setEnabled(true);
                                    zombieHealth -= currentWeaponDamage;
                         //           mainTextView.setText("You have killed the Zombie and taken no damage.");

                                    setPreviousAndMainText("You have killed the Zombie and taken no damage.");

                                    System.out.println("You have killed the Zombie and taken no damage.");
                                    Weapon longSword = new Weapon("Long Sword", 12);
                                    player.setCurrentWeapon(longSword);
                                    userSubmitButton.setEnabled(false);
                             //       addDelay(2000);
                                    userSubmitButton.setEnabled(true);

                                    setPreviousAndMainText("The Zombie was carrying a Long Sword which you claim as your own.");

                      //              mainTextView.setText("The Zombie was carrying a Long Sword which you claim as your own.");
                                    System.out.println("The Zombie was carrying a Long Sword which you claim as your own.");
                                    System.out.println(player);
                                    player.setProgress("level3");
                                    System.out.println("Player progress is: " + player.getProgress());
                                    System.out.println("Please enter your save game name.");
                                    //                                    //String savedFileName = userTextInputCollected;
                                    //save(userTextInputCollected);
                                    break;
                                } else {
                        //            userSubmitButton.setEnabled(false);
                       //             addDelay(2000);
                        //            userSubmitButton.setEnabled(true);
                                    System.out.println("\nYou have damaged the Zombie");

                                    setPreviousAndMainText("You have damaged the Zombie");

                              //      mainTextView.setText("You have damaged the Zombie");
                                    zombieHealth -= currentWeaponDamage;
                         //           userSubmitButton.setEnabled(false);
                         //           addDelay(2000);
                          //          userSubmitButton.setEnabled(true);
                                    System.out.println("The Zombie now has " + zombieHealth + " health.");

                                    setPreviousAndMainText("The Zombie now has " + zombieHealth + " health.");

                         //           mainTextView.setText("The Zombie now has " + zombieHealth + " health.");
                         //           userSubmitButton.setEnabled(false);
                         //           addDelay(2000);
                         //           userSubmitButton.setEnabled(true);

                                    setPreviousAndMainText("The Zombie has attacked you with " + zombieDamage + " damage.");

                             //       mainTextView.setText("The Zombie has attacked you with " + zombieDamage + " damage.");
                                    System.out.println("\nThe Zombie has attacked you with " + zombieDamage + " damage.");
                                    player.takeDamage(zombieDamage);
                                }
                            }
                            nextLevel();
                            break;
                        } else if (zombie == 1) {
                        //    userSubmitButton.setEnabled(false);
                        //    addDelay(2000);
                        //    userSubmitButton.setEnabled(true);
                            System.out.println("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");
                       //     mainTextView.setText("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");

                            setPreviousAndMainText("You choose not to attack the Zombie. The Zombie attacks you in the back as you run away.");

                            player.takeDamage(zombieDamage * 1.5);
                            player.setProgress("level3");
                            nextLevel();
                            break;

                        } else if (zombie == 2) {
                            //System.out.println("Please enter your save game name.");
                            //String savedFileName = userTextInputCollected;
                            //save(userTextInputCollected);
                            save();
                            break;
 /*                       } else if (zombie ==3) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;
                        } else if (zombie == 4) {
                            System.out.println("What is the name of your save file?");
                            String loadFileName = userTextInputCollected + ".svr";
                            player = load(loadFileName);
 */                       } else {
                            //System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
                            // zombie = userChoice;
                            startDelayedTask(200000, true);
                        }

               nextLevel();
                break;

                case "level3":
     //               while (true) {

                    chestTwo = userChoice;
                        if (chestTwo== 0) {
                            //addDelay(2000);

                            setPreviousAndMainText("You open the chest to find some chain mail. You put it on.");
                            System.out.println("You open the chest to find some chain mail. You put it on.");
                            Armour chainMail = new Armour("Chain Mail", 10, 10);
                            player.setArmour(chainMail);
                            System.out.println(player);
                            //addDelay(2000);
                            player.setProgress("level4");
                            nextLevel();
                            break;
                        } else if (chestTwo== 1) {
                            setPreviousAndMainText("You choose not to open the chest. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own armor behind.");
                            System.out.println("You choose not to open the chest. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own armor behind.");
                            Armour platedArmor = new Armour("Plated Armor", 7, 6);
                            player.setArmour(platedArmor);
                            System.out.println(player);
                            player.setProgress("level4");
                            nextLevel();
                            break;
//                        } else if (chestTwo== 2) {
//                            System.out.println("Please enter your save game name.");
//                            String savedFileName = userTextInputCollected;
//                            save(savedFileName);
//                            break;
//                        } else if (chestTwo== 4) {
//                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
//                            System.exit(0);
//                            break;
                        } else {
                            // System.out.println("Please try again, your options are y or n to open the chest.");
                            //  chestTwo = console.next().toLowerCase();
                            startDelayedTask(200000, true);
                        }
           //         }
                    nextLevel();
                break;

                case "level4":
                        enemy2 = userChoice;
                        if (enemy2 == 0 ) {
                            System.out.println("\nYou attack the " + zombieKing.getEnemyName());

                            setPreviousAndMainText("You attack the " + zombieKing.getEnemyName());

                            int currentWeaponDamage = player.getCurrentWeaponDamage();
                            int zombieKingHealth = zombieKing.getEnemyHealth();


                            while (player.getHealth() > 0) {
                                if (currentWeaponDamage >= zombieKingHealth) {
                                  //  addDelay(2000);
                                    zombieKingHealth -= currentWeaponDamage;

                                    setPreviousAndMainText("You have killed the " + zombieKing.getEnemyName() + " and taken no damage.");

                                    System.out.println("You have killed the " + zombieKing.getEnemyName() + " and taken no damage.");

                                    setPreviousAndMainText("The " + zombieKing.getEnemyName() + " drops a key. You use it to open a chest. Inside is a pair of chain mail trousers.");
                                    System.out.println("The " + zombieKing.getEnemyName() + " drops a key. You use it to open a chest. Inside is a pair of chain mail trousers.");

                                    Trouser chainMailTrousers = new Trouser("Chain Mail Trousers", 10, 10);
                                    player.setTrouser(chainMailTrousers);
                                    System.out.println(player);
                  //                  player.setProgress("level5");
                  //                  System.out.println("Please enter your save game name.");
                  //                  String savedFileName = userTextInputCollected;
                  //                  save(savedFileName);
                                    player.setProgress("level5");
                                    break;

                                } else {
                                  //  addDelay(2000);

                                    setPreviousAndMainText("You have damaged the " + zombieKing.getEnemyName());
                                    System.out.println("\nYou have damaged the " + zombieKing.getEnemyName());
                                    zombieKingHealth -= currentWeaponDamage;

                                    setPreviousAndMainText("The " + zombieKing.getEnemyName() + " now has " + zombieKingHealth + " health.");
                                    System.out.println("The " + zombieKing.getEnemyName() + " now has " + zombieKingHealth + " health.");

                                    setPreviousAndMainText("The Zombie has attacked you with " + zombieKing.getEnemyDamage() + " damage.");
                                    System.out.println("\nThe Zombie has attacked you with " + zombieKing.getEnemyDamage() + " damage.");

                                    player.takeDamage(zombieKing.getEnemyDamage());
                                }
                            }
                            nextLevel();
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
                            break;
                        } else if (enemy2 == 3) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;*/

                        } else {
                            // System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
                            // enemy2 = userChoice;
                            startDelayedTask(200000, true);

                        }
      //              }

                    nextLevel();
                    break;


                case "level5":
                    //                  while (true) {

                    doorOne = userChoice;
                    if (doorOne== 0) {
                       // addDelay(2000);
                        setPreviousAndMainText("You open the door to find a leather jacket. You put it on.");
                        System.out.println("You open the door to find a leather jacket. You put it on.");
                        Shirt leatherJacket = new Shirt("Leather Jacket", 3, 3);
                        player.setShirt(leatherJacket);
                        System.out.println(player);
                      //  addDelay(2000);
                        player.setProgress("level6");
                        nextLevel();
                        break;
                    } else if (doorOne== 1) {
                        System.out.println("You choose not to open the door. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own armor behind.");
                        setPreviousAndMainText("You choose not to open the door. An observer thinks it must be locked and bashes it with their mace. They discover some Chain Mail inside and leave their own armor behind.");
                        Armour platedArmor = new Armour("Plated Armor", 7, 6);
                        player.setArmour(platedArmor);
                        System.out.println(player);
                        player.setProgress("level6");
                         nextLevel();
                        break;

                    } /* else if (doorOne== 2) {
                            System.out.println("Please enter your save mainActivity name.");
                            String savedFileName = userTextInputCollected;
                            save(savedFileName);
                            break;
                        } else if (doorOne== 4) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;
                        }  */ else {
                            // System.out.println("Please try again, your options are y or n to open the chest.");
                            //  doorOne = userChoice;
                            startDelayedTask(200000, true);
                        }
                        nextLevel();
                break;
            //        }

                case "level6":

                    enemy3 = userChoice;
 //                   while (player.getHealth() > 0) {
                        if (enemy3 == 0) {
                            System.out.println("\nYou attack " + loki.getEnemyName());
                            setPreviousAndMainText("You attack " + loki.getEnemyName());

                            int currentWeaponDamage = player.getCurrentWeaponDamage();
                            int lokiHealth = loki.getEnemyHealth();

                            while (player.getHealth() > 0) {

                                if (currentWeaponDamage >= lokiHealth) {
                       //             addDelay(2000);
                                    lokiHealth -= currentWeaponDamage;

                                    System.out.println("You have killed " + loki.getEnemyName() + " and taken no damage.");
                                    setPreviousAndMainText("You have killed " + loki.getEnemyName() + " and taken no damage.");

                            //        Trouser chainMailTrousers = new Trouser("Chain Mail Trousers", 10, 10);
                                    Weapon flamingSword = new Weapon("Flaming Sword", 24);

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
                                    //nextLevel();
      //                              System.exit(1);
                                    break;
                                } else {
                                //    addDelay(2000);
                                    System.out.println("\nYou have damaged the " + loki.getEnemyName());
                                    setPreviousAndMainText("You have damaged the \" + loki.getEnemyName()");
                                    lokiHealth -= currentWeaponDamage;
                                    System.out.println("The " + loki.getEnemyName() + " now has " + lokiHealth + " health.");
                                    setPreviousAndMainText("The " + loki.getEnemyName() + " now has " + lokiHealth + " health.");
                                    System.out.println("\n" + loki.getEnemyName() + "has attacked you with " + loki.getEnemyDamage() + " damage.");
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
                            nextLevel();
                            break;
                        } else if (enemy3 == 1) {
                            System.out.println("You choose not to attack. " + loki.getEnemyName() + " attacks you in the back as you run away.");
                            setPreviousAndMainText("You choose not to attack. " + loki.getEnemyName() + " attacks you in the back as you run away.");
                            player.takeDamage(loki.getEnemyDamage() / 2);
                            setPreviousAndMainText("You now have " + player.getHealth() + " health left.");
              //              player.setProgress("level7");
                            System.out.println("Your cowardly actions have not gone unnoticed, the King has thrown you in jail and you journey is at an end. Better luck next time.");

                            setPreviousAndMainText("Your cowardly actions have not gone unnoticed, the King has thrown you in jail and you journey is at an end. Better luck next time.");
                            player.setProgress("level7");
                            nextLevel();
                        //    System.exit(0);
                            break;

                  /*      } else if (enemy3== 2) {
                            System.out.println("Please enter your save mainActivity name.");
                            String savedFileName = userTextInputCollected;
                            save(savedFileName);
                            break;

                        } else if (enemy3== 4) {
                            System.out.println("Goodbye Traveller, return soon to conquer to hordes of evil!");
                            System.exit(0);
                            break;
                   */     } else {
//                    progress = "level7";
                            //    System.out.println("Please try again, your options are y or n to attack the Zombie, s to Save or x to exit");
                            //    enemy2 = userChoice;
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


    private void setPreviousAndMainText(String newMainText){
        mainTextViewText = newMainText;
        previousPreviousStageTextView.setText(previousPreviousStageTextViewText);
        previousStageTextView.setText(previousStageTextViewText);
        mainTextView.setText(mainTextViewText);
        previousPreviousStageTextViewText = previousStageTextViewText;
        previousStageTextViewText = mainTextViewText;
    }

//    public void setTime(int time){
//        this.time = time;
//    }







}