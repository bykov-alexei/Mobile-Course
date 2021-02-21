package com.example.colortiles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PlayField extends View {

    ArrayList<Tile> tiles = new ArrayList<>();


    public PlayField(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
    }
}
