package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivityExploreBinding;

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

        wormHole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CreateNewGalaxyActivity.getIntent(getApplicationContext(), USERID);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item=menu.add("Home"); //your desired title here
        item.setIcon(R.drawable.home_button); //your desired icon here
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(LandingPageActivity.getIntent(getApplicationContext(), USERID));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
