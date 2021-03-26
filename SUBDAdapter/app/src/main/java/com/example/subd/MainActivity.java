package com.example.subd;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView songListView;
    SimpleCursorAdapter adapter;

    TextView titleEditView;
    TextView authorEditView;
    TextView yearEditView;
    TextView durationEditView;
    TextView countView;
    TextView wholeDurationView;

    Button addSongButton;

    Button sortTitleButton;
    Button sortAuthorButton;
    Button sortYearButton;
    Button sortDurationButton;

    Boolean checkerTitle = true;
    Boolean checkerAuthor = true;
    Boolean checkerYear = true;
    Boolean checkerDuration = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        adapter = Song.getSongAdapter(this);
        songListView.setAdapter(adapter);
        updateSongInformation();
    }

    public void onClick(View v) {
        Song song = new Song(
                titleEditView.getText().toString(),
                authorEditView.getText().toString(),
                yearEditView.getText().toString(),
                durationEditView.getText().toString()
        );
        song.save(this);
        updateSongInformation();
    }

    public void updateSongInformation() {
        Song.updateAdapter(adapter);
        countView.setText(String.valueOf(Song.getCount(this)));
        wholeDurationView.setText(Song.formatDuration(Song.getTotalDuration(this)));
    }

    public void toggleVisibility(View view) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public void showAddMenu(View view) {
        View[] views = {titleEditView, authorEditView, yearEditView, durationEditView, addSongButton};
        for (View v: views) {
            toggleVisibility(v);
        }
    }

    public void showSort(View view) {
        View[] views = { sortYearButton, sortTitleButton, sortDurationButton, sortAuthorButton };
        for (View v: views) {
            toggleVisibility(v);
        }
    }

    public void sortByAuthor(View view) {
        Song.updateAdapter(adapter, Song.AUTHOR, checkerAuthor);
        checkerAuthor = !checkerAuthor;
    }

    public void sortByTitle(View view) {
        Song.updateAdapter(adapter, Song.TITLE, checkerTitle);
        checkerTitle = !checkerTitle;
    }

    public void sortByYear(View view) {
        Song.updateAdapter(adapter, Song.YEAR, checkerYear);
        checkerYear = !checkerYear;
    }

    public void sortByDuration(View view) {
        Song.updateAdapter(adapter, Song.DURATION, checkerDuration);
        checkerDuration = !checkerDuration;
    }

    public void findViews() {
        songListView = findViewById(R.id.mainPlayList);

        titleEditView = findViewById(R.id.mainTitle);
        authorEditView = findViewById(R.id.mainAuthor);
        yearEditView = findViewById(R.id.mainYear);
        durationEditView = findViewById(R.id.mainDuration);

        addSongButton = findViewById(R.id.mainAdd);

        sortAuthorButton = findViewById(R.id.sortAuthopr);
        sortDurationButton = findViewById(R.id.sortDuration);
        sortTitleButton = findViewById(R.id.sortTitle);
        sortYearButton = findViewById(R.id.sortYear);

        countView = findViewById(R.id.mainCount);
        wholeDurationView = findViewById(R.id.mainWholeDuration);
    }
}