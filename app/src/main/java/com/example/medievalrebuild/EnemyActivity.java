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
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

import com.example.medievalrebuild.Enemies.BossEnemy;
import com.example.medievalrebuild.Enemies.Enemy;
import com.example.medievalrebuild.Game.Art;
import com.example.medievalrebuild.Game.Player;

import java.io.Serializable;

public class EnemyActivity extends AppCompatActivity implements Serializable {

    ImageView EnemyImage;

    TextView EnemyStats;

    Button switchToStory;

    Button switchToCharacter;


    private static final long serialVersionUID = 1L;

    /* Instance Variables */

    private transient MainActivity mainActivity;

    private transient CharacterActivity characterActivity;
    private Player player;

    private Enemy enemy;

    private Enemy currentEnemy;


    @SuppressLint("MissingInflatedId")



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

        setContentView(R.layout.activity_enemy);

        int autoSizeMinTextSize = 6;
        int autoSizeMaxTextSize = 30;
        int autoSizeStepGranularity = 1;
        int unit = TypedValue.COMPLEX_UNIT_SP;

        EnemyImage = findViewById(R.id.iv_enemy_image);
        EnemyStats = findViewById(R.id.tv_enemy_stats);

        switchToStory = findViewById(R.id.btn_enemy_story);
        switchToCharacter= findViewById(R.id.btn_enemy_character);


        if (getIntent().hasExtra("currentEnemy")) {
            currentEnemy = (Enemy) getIntent().getSerializableExtra("currentEnemy");
            EnemyStats.setText(currentEnemy.toString());
        } else {
            Log.e("EnemyActivity", "Player object not found in Intent");
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





    }

}
