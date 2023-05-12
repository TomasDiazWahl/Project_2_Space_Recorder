package com.example.project_2_space_recorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivityDisplayPlanetBinding;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.PlanetDAO;
import Universe_Structure.Planet;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class DisplayPlanetActivity extends AppCompatActivity {

    int USERID;
    int PLANETID;
    private static final String userIdGetter = "displayPlanet.userID";
    private static final String planetIdGetter = "displayPlanet.userID";
    User USER;
    Planet PLANET;

    UserDAO userDAO;
    PlanetDAO planetDAO;


    @NonNull ActivityDisplayPlanetBinding mainBinding;
    TextView planetName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_planet);
        setupUser();
    }




    @SuppressLint("SetTextI18n")
    void setupVariables(){
        mainBinding = ActivityDisplayPlanetBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        planetName = mainBinding.viewPlanetName;
        planetName.setText(PLANET.getName());
    }

    void setupUser(){
        planetDAO = AppDataBaseSpace.getInstance(getApplicationContext()).PlanetDAO();
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(userIdGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        PLANETID = getIntent().getIntExtra(planetIdGetter, 0);
        PLANET = planetDAO.getPlanetById(PLANETID);

        setupVariables();
    }



    public static Intent getIntent(Context context, int userID, int planetID){
        Intent intent = new Intent(context, DisplayPlanetActivity.class);
        intent.putExtra(userIdGetter, userID);
        intent.putExtra(planetIdGetter, planetID);
        return intent;
    }
}