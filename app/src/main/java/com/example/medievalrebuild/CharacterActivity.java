package com.example.medievalrebuild;

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
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.content.Context;
        import android.content.Intent;

        import com.example.medievalrebuild.Enemies.BossEnemy;
        import com.example.medievalrebuild.Enemies.Enemy;
        import com.example.medievalrebuild.Game.Art;
        import com.example.medievalrebuild.Game.Player;

        import java.io.Serializable;

public class CharacterActivity extends AppCompatActivity implements Serializable {

    ImageView characterImage;

//    TextView characterStats;

    TextView characterName;

    TextView characterHealth;

    TextView characterGold;

    TextView characterStrength;

    TextView characterAccuracy;

    TextView characterReactions;

    TextView characterSpeed;

    TextView characterIntelligence;

    TextView characterWeaponDescription;

    ImageView characterWeaponImage;

    TextView characterShieldDescription;

    ImageView characterShieldImage;

    TextView characterChestArmourDescription;

    ImageView characterChestArmourImage;

    TextView characterHelmetDescription;

    ImageView characterHelmetImage;

    TextView characterShirtDescription;

    ImageView characterShirtImage;

    TextView characterTrousersDescription;

    ImageView characterTrousersImage;

    TextView characterShoesDescription;

    ImageView characterShoesImage;

    TextView characterLevelName;

    TextView characterLevelDescription;

    Button switchToStory;

    Button switchToEnemy;




    private static final long serialVersionUID = 1L;

    /* Instance Variables */

    private transient MainActivity mainActivity;

    private transient EnemyActivity enemyActivity;
    private Player player;

    private Enemy enemy;

    private Enemy currentEnemy;


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

        setContentView(R.layout.activity_character);

        int autoSizeMinTextSize = 6;
        int autoSizeMaxTextSize = 30;
        int autoSizeStepGranularity = 1;
        int unit = TypedValue.COMPLEX_UNIT_SP;

        characterImage = findViewById(R.id.iv_character_image);

        characterName = findViewById(R.id.tv_character_name);

        characterHealth = findViewById(R.id.tv_character_health);

        characterGold = findViewById(R.id.tv_character_gold);

        characterStrength = findViewById(R.id.tv_character_strength);

        characterAccuracy = findViewById(R.id.tv_character_accuracy);

        characterReactions = findViewById(R.id.tv_character_reactions);

        characterSpeed = findViewById(R.id.tv_character_speed);

        characterIntelligence = findViewById(R.id.tv_character_intelligence);

        characterWeaponDescription = findViewById(R.id.tv_character_weapon_description);

        characterWeaponImage = findViewById(R.id.iv_character_weapon_image);

        characterShieldDescription = findViewById(R.id.tv_character_shield_description);

        characterShieldImage = findViewById(R.id.iv_character_shield_image);

        characterChestArmourDescription = findViewById(R.id.tv_character_chestArmour_description);

        characterChestArmourImage = findViewById(R.id.iv_character_chestArmour_image);

        characterHelmetDescription = findViewById(R.id.tv_character_helmet_description);

        characterHelmetImage = findViewById(R.id.iv_character_helmet_image);

        characterShirtDescription = findViewById(R.id.tv_character_shirt_description);

        characterShirtImage = findViewById(R.id.iv_character_shirt_image);

        characterTrousersDescription = findViewById(R.id.tv_character_trousers_description);

        characterTrousersImage = findViewById(R.id.iv_character_trousers_image);

        characterShoesDescription = findViewById(R.id.tv_character_shoes_description);

        characterShoesImage = findViewById(R.id.iv_character_shoes_image);

        characterLevelName = findViewById(R.id.tv_character_level_name);

        characterLevelDescription = findViewById(R.id.tv_character_level_description);


//        characterStats = findViewById(R.id.tv_character_stats);

        switchToStory = findViewById(R.id.btn_character_story);
//        switchToEnemy= findViewById(R.id.btn_character_enemy);


        if (getIntent().hasExtra("player")) {
            player = (Player) getIntent().getSerializableExtra("player");
//            characterStats.setText(player.toString());
            characterImage.setImageResource(R.drawable.e710d0a6e8434a2b874b74101ff5cb63);

            characterName.setText("Name: " + player.getName());

            characterHealth.setText("Current Health: " + String.valueOf(player.getHealth()));

            characterGold.setText("Gold: " + String.valueOf(player.getPlayerGold()));

            characterStrength.setText("Strength: " + String.valueOf(player.getStrength()));

            characterAccuracy.setText("Accuracy: " + String.valueOf(player.getAccuracy()));

            characterReactions.setText("Reactions: " + String.valueOf(player.getReactions()));

            characterSpeed.setText("Speed: " + String.valueOf(player.getSpeed()));

            characterIntelligence.setText("Intelligence: " + String.valueOf(player.getIntelligence()));

            characterWeaponDescription.setText(player.getCurrentWeapon());

            characterWeaponImage.setImageResource(player.getWeapon().getImageId());

            characterShieldDescription.setText(player.getShield().toString());

            characterShieldImage.setImageResource(player.getShield().getImageId());

            characterChestArmourDescription.setText((player.getChestArmour().toString()));

            characterChestArmourImage.setImageResource(player.getChestArmour().getImageId());

            characterHelmetDescription.setText(player.getHelmet().toString());

            characterHelmetImage.setImageResource(player.getHelmet().getImageId());

            characterShirtDescription.setText(player.getShirt().toString());

            characterShirtImage.setImageResource(player.getShirt().getImageId());

            characterTrousersDescription.setText(player.getTrouser().toString());

            characterTrousersImage.setImageResource(player.getTrouser().getImageId());

            characterShoesDescription.setText(player.getShoe().toString());

            characterShoesImage.setImageResource(player.getShoe().getImageId());

            characterLevelName.setText("Progress: " + player.getProgress());

            characterLevelDescription.setText("Level Name: " + player.getProgress());




        } else {
        Log.e("CharacterActivity", "Player object not found in Intent");
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

            }

        });

/*        switchToEnemy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//finish();
                //Intent intent = new Intent(CharacterActivity.this, MainActivity.class);
                //startActivity(intent);
                if (mainActivity != null) {
                    mainActivity.switchToEnemyStats();
                 //   finish();
                } else {
                    Log.e("CharacterActivity", "MainActivity is null");
                }
              // mainActivity.switchToEnemyStats();

            }

        });

*/



    }

}