/*
* Authors: Tomas Diaz-Wahl & Alexis Petignat
* Date: 2023-05-12
* Purpose: This class represents an admin menu that allows an admin user to navigate to custom
* search options
* */

package com.example.project_2_space_recorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivityAdminmenuBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class AdminMenuActivity extends AppCompatActivity {

    int USERID;
    private static final String idGetter = "LandingPage.userID";
    User USER;
    UserDAO userDAO;
    ActivityAdminmenuBinding mainBinding;
    TextView back;
    Button userOptions;
    Button spaceObjectOptions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupUser();

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            startActivity(SuperSecretActivity.getIntent(getApplicationContext(), USERID));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(LandingPageActivity.getIntent(getApplicationContext(), USERID));
            }
        });

        spaceObjectOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Search.getIntent(getApplicationContext(), USERID));
            }
        });

        userOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SearchUser.getIntent(getApplicationContext(), USERID));
            }
        });
    }


    @SuppressLint("SetTextI18n")
    void setupVariables(){
        mainBinding = ActivityAdminmenuBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        back = mainBinding.arrow;
        userOptions = mainBinding.userOptions;
        spaceObjectOptions = mainBinding.spaceObjectOptions;
    }

    void setupUser(){
        userDAO = Room.databaseBuilder(this, AppDataBaseUser.class, AppDataBaseUser.DATABASE_NAME)
                .allowMainThreadQueries().build().UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        setupVariables();
    }

    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, com.example.project_2_space_recorder.AdminMenuActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
