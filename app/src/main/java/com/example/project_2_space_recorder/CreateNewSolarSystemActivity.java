package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivityCreatePlanetBinding;
import com.example.project_2_space_recorder.databinding.ActivityCreateSolarSystemBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class CreateNewSolarSystemActivity extends AppCompatActivity {
    ActivityCreateSolarSystemBinding mainBinding;
    private static final String idGetter = "LandingPage.userID";

    int USERID;
    User USER;
    UserDAO userDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        setupUser();
    }

    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, com.example.project_2_space_recorder.CreateNewSolarSystemActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }

    void setupVariables(){
        mainBinding = ActivityCreateSolarSystemBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

    }

    void setupUser(){
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        System.out.println("LandingPage " + USER);
        setupVariables();
    }
}
