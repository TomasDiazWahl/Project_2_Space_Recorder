package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivityLandingPageBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class LandingPageActivity extends AppCompatActivity {

    int USERID;
    private static final String idGetter = "LandingPage.userID";
    User USER;

    UserDAO userDAO;

    ActivityLandingPageBinding mainBinding;
    Button profileButton;
    TextView planetID;
    Button adminButton;
    Button logOutButton;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        mainBinding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        setupUser();

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MainActivity.getIntent(getApplicationContext()));
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ProfileActivity.getIntent(getApplicationContext(), USERID));
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AdminMenuActivity.getIntent(getApplicationContext(), USERID));
            }
        });
    }


    void setupVariables(){
        profileButton = mainBinding.buttonProfile;
        planetID = mainBinding.planetId;
        adminButton = mainBinding.admin;
        logOutButton = mainBinding.buttonLogOut;

        profileButton.setText(USER.getName().substring(0, 1));

        if (USER.getIsAdmin() == 1){
            adminButton.setVisibility(View.VISIBLE);
        }
    }

    void setupUser(){
        userDAO = Room.databaseBuilder(this, AppDataBaseUser.class, AppDataBaseUser.DATABASE_NAME)
                .allowMainThreadQueries().build().UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        System.out.println("LandingPage " + USER);
        setupVariables();
    }

    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, LandingPageActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
