package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivitySuperSecretBinding;
import com.example.project_2_space_recorder.databinding.ActivityUltraSuperSecretBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class SuperSecretActivity extends AppCompatActivity {
    ActivitySuperSecretBinding mainBinding;
    private static final String idGetter = "LandingPage.userID";

    int USERID;
    User USER;
    UserDAO userDAO;
    private String[] code = new String[] { "up", "up", "down", "down", "left", "right", "b", "a"};
    private String inputPressedButton;
    private int counter;
    Button b;
    Button a;
    Button up;
    Button down;
    Button left;
    Button right;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_secret);
        setupUser();

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            startActivity(AdminMenuActivity.getIntent(getApplicationContext(), USERID));
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPressedButton = "b";
                if (code[counter].equals(inputPressedButton)){
                    counter++;
                }
                else{
                    counter = 0;
                }
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPressedButton = "a";
                if (code[counter].equals(inputPressedButton)){
                    Intent intent = UltraSuperSecretActivity.getIntent(getApplicationContext(), USERID);
                    startActivity(intent);
                }
                else{
                    counter = 0;
                }
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPressedButton = "up";
                if (code[counter].equals(inputPressedButton)){
                    counter++;
                }
                else{
                    counter = 0;
                }
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPressedButton = "down";
                if (code[counter].equals(inputPressedButton)){
                    counter++;
                }
                else{
                    counter = 0;
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPressedButton = "right";
                if (code[counter].equals(inputPressedButton)){
                    counter++;
                }
                else{
                    counter = 0;
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputPressedButton = "left";
                if (code[counter].equals(inputPressedButton)){
                    counter++;
                }
                else{
                    counter = 0;
                }
            }
        });
    }

    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, com.example.project_2_space_recorder.SuperSecretActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }

    void setupVariables(){
        mainBinding = ActivitySuperSecretBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        b = mainBinding.buttonB;
        a = mainBinding.buttonA;
        up = mainBinding.buttonUp;
        down = mainBinding.buttonDown;
        right = mainBinding.buttonRight;
        left = mainBinding.buttonLeft;
    }

    void setupUser(){
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        setupVariables();
    }
}
