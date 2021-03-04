package com.example.customadapterdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    UserListAdapter adapter;
    ListView listView;

    ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);



        // TODO: реализовать загрузку данных из JSON-файла
        // который загрузить в папку assets
        // DONE

        /*for (int i = 0; i < 10; i++) {
            users.add(new User("Petya", "123", Sex.MAN));
            users.add(new User("Vasya", "234", Sex.MAN));
            users.add(new User("Valya", "456", Sex.WOMAN));
            users.add(new User("UFO", "@@@", Sex.UNKNOWN));
        }*/

        InputStream stream = null;
        try {
            stream = getAssets().open("users");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        User[] text = gson.fromJson(new InputStreamReader(stream), User[].class);
        users = new ArrayList<>(Arrays.asList(text));
        adapter = new UserListAdapter(this, users);

        listView.setAdapter(adapter);


    }

    public void sortByName (View view) {
        Collections.sort(users, name);
        adapter.notifyDataSetChanged();
    }

    public void sortBySex (View view) {
        Collections.sort(users, sex);
        adapter.notifyDataSetChanged();
    }

    public void sortByPhone (View view) {
        Collections.sort(users, phone);
        adapter.notifyDataSetChanged();
    }

    public Comparator<User> name = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public Comparator<User> phone = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
        }
    };

    public Comparator<User> sex = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getSex().compareTo(o2.getSex());
        }
    };
}
