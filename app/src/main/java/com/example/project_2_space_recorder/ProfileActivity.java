package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivityProfileBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class ProfileActivity extends AppCompatActivity {

    int USERID;
    private static final String idGetter = "LandingPage.userID";
    User USER;

    UserDAO userDAO;

    ActivityProfileBinding mainBinding;
    TextView username;
    TextView age;
    TextView back;
    Button editProfileButton;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupUser();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(LandingPageActivity.getIntent(getApplicationContext(), USERID));
            }
        });

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(EditProfileActivity.getIntent(getApplicationContext(), USERID));
            }
        });
    }

    void setupVariables(){
        mainBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        username = mainBinding.UsernameEdit;
        age = mainBinding.AgeDisplay;
        back = mainBinding.arrow;
        editProfileButton = mainBinding.EditProfileButton;

        System.out.println("Profile activity " + USER);
        username.setText(USER.getName());
        age.setText(String.valueOf(USER.getAge()));
    }

    void setupUser(){
        userDAO = Room.databaseBuilder(this, AppDataBaseUser.class, AppDataBaseUser.DATABASE_NAME)
                .allowMainThreadQueries().build().UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);


        setupVariables();
    }



    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, com.example.project_2_space_recorder.ProfileActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
