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

    TextView characterStats;

    Button switchToStory;


    private static final long serialVersionUID = 1L;

    /* Instance Variables */

    MainActivity mainActivity;
    private Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.medieval_marvels_and_might_icon);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_character);

        int autoSizeMinTextSize = 6;
        int autoSizeMaxTextSize = 30;
        int autoSizeStepGranularity = 1;
        int unit = TypedValue.COMPLEX_UNIT_SP;

        characterImage = findViewById(R.id.iv_character_image);
        characterStats = findViewById(R.id.tv_character_stats);

        switchToStory = findViewById(R.id.btn_character_story);


        if (getIntent().hasExtra("player")) {
            player = (Player) getIntent().getSerializableExtra("player");
            characterStats.setText(player.toString());
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





    }

}