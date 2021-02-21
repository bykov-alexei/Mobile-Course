package com.example.colortiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class Tile extends View {
    int color;

    public Tile(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.color = new Random().nextInt(2);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
