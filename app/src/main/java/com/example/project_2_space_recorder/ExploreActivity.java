package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivityExploreBinding;
import com.example.project_2_space_recorder.databinding.CreateAccountBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class ExploreActivity extends AppCompatActivity {
    ActivityExploreBinding  mainBinding;
    private static final String idGetter = "ExploreActivity.userID";
    Button findNewPlanet;
    Button findNewSolarSystem;
    Button wormHole;
    Button searchTheUniverse;
    int USERID;
    User USER;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        setupUser();

        findNewPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CreateNewPlanetActivity.getIntent(getApplicationContext(), USERID);
                startActivity(intent);
            }
        });

        findNewSolarSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CreateNewSolarSystemActivity.getIntent(getApplicationContext(), USERID);
                startActivity(intent);
            }
        });

    }

    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, com.example.project_2_space_recorder.ExploreActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }

    void setupVariables(){
        mainBinding = ActivityExploreBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        findNewPlanet = mainBinding.findNewPlanet;
        findNewSolarSystem = mainBinding.findNewSolarSystem;
        wormHole = mainBinding.jumpThroughWormhole;
        searchTheUniverse = mainBinding.buttonSearch;
    }

    void setupUser(){
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        System.out.println("LandingPage " + USER);
        setupVariables();
    }
}
