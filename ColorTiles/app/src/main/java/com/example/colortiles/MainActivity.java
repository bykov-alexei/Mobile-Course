package com.example.colortiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    Tile tile_1, tile_2, tile_3, tile_4, tile_5, tile_6, tile_7, tile_8, tile_9, tile_10, tile_11,
            tile_12, tile_13, tile_14, tile_15, tile_16;


    int dark, light;
    Tile[][] list = new Tile[4][4];
    static Boolean value;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resources = getResources();
        dark = resources.getColor(R.color.darkColor,  null);
        light = resources.getColor(R.color.brightColor,  null);

        tile_1 = findViewById(R.id.tile_1);
        tile_2 = findViewById(R.id.tile_2);
        tile_3 = findViewById(R.id.tile_3);
        tile_4 = findViewById(R.id.tile_4);
        tile_5 = findViewById(R.id.tile_5);
        tile_6 = findViewById(R.id.tile_6);
        tile_7 = findViewById(R.id.tile_7);
        tile_8 = findViewById(R.id.tile_8);
        tile_9 = findViewById(R.id.tile_9);
        tile_10 = findViewById(R.id.tile_10);
        tile_11 = findViewById(R.id.tile_11);
        tile_12 = findViewById(R.id.tile_12);
        tile_13 = findViewById(R.id.tile_13);
        tile_14 = findViewById(R.id.tile_14);
        tile_15 = findViewById(R.id.tile_15);
        tile_16 = findViewById(R.id.tile_16);

        list[0][0] = tile_1;
        list[0][1] = tile_2;
        list[0][2] = tile_3;
        list[0][3] = tile_4;
        list[1][0] = tile_5;
        list[1][1] = tile_6;
        list[1][2] = tile_7;
        list[1][3] = tile_8;
        list[2][0] = tile_9;
        list[2][1] = tile_10;
        list[2][2] = tile_11;
        list[2][3] = tile_12;
        list[3][0] = tile_13;
        list[3][1] = tile_14;
        list[3][2] = tile_15;
        list[3][3] = tile_16;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile cur = list[i][j];
                if (cur.color == 1) {
                    cur.setBackgroundColor(dark);
                } else {
                    cur.setBackgroundColor(light);
                }
            }
        }
    }

    public void onClick(View view) {

        if (value) {
            switch (view.getId()) {
                case R.id.tile_1:
                case R.id.tile_2:
                case R.id.tile_3:
                case R.id.tile_4:
                case R.id.tile_5:
                case R.id.tile_6:
                case R.id.tile_7:
                case R.id.tile_8:
                case R.id.tile_9:
                case R.id.tile_10:
                case R.id.tile_11:
                case R.id.tile_12:
                case R.id.tile_13:
                case R.id.tile_14:
                case R.id.tile_15:
                case R.id.tile_16:
                    changeableTiles(view);
                    victory();
                    break;
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please, check that you have read the rules", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void changeableTiles(View view) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (view == list[i][j]) {

                    for (int k = 0; k < 4; k++) {
                        colorChanger(list[i][k]);
                        colorChanger(list[k][j]);
                    }
                    colorChanger(list[i][j]);
                }
            }
        }
    }


    public void colorChanger(View view) {

        ColorDrawable d = (ColorDrawable) view.getBackground();

        if (d.getColor() == dark) {
            view.setBackgroundColor(light);
        } else {
            view.setBackgroundColor(dark);
        }
    }

    public void victory() {

        int darkCount = 0;
        int lightCount = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ColorDrawable d = (ColorDrawable) list[i][j].getBackground();

                if (d.getColor() == dark) {
                    darkCount++;
                } else {
                    lightCount++;
                }
            }
        }

        if (darkCount == 16 || lightCount == 16) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "It is victory, congrats", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void rules(View view) {

        CheckedTextView rules = (CheckedTextView) findViewById(R.id.checkedTextView);

        if (rules.isChecked()) {
            rules.setChecked(false);
            value = rules.isChecked();
            rules.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
        } else {
            rules.setChecked(true);
            value = rules.isChecked();
            rules.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
        }
    }
}