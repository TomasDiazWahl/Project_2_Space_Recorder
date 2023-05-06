package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivityCreateGalaxyBinding;
import com.example.project_2_space_recorder.databinding.ActivityCreatePlanetBinding;
import com.example.project_2_space_recorder.databinding.ActivityExploreBinding;

import Universe_DB.PlanetDAO;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;


public class CreateNewPlanetActivity extends AppCompatActivity {
    ActivityCreatePlanetBinding mainBinding;
    private static final String idGetter = "LandingPage.userID";

    int USERID;
    User USER;
    UserDAO userDAO;
    PlanetDAO planetDAO;
    Button backButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        setupUser();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ExploreActivity.getIntent(getApplicationContext(), USERID));
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
        Intent intent = new Intent(context, com.example.project_2_space_recorder.CreateNewPlanetActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }

    void setupVariables(){
        mainBinding = ActivityCreatePlanetBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        backButton = mainBinding.buttonBack;
    }

    void setupUser(){
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        System.out.println("LandingPage " + USER);
        setupVariables();
    }
}
