package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivityMainBinding;

import java.util.List;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    EditText username;
    EditText password;
    Button loginButton;
    Button createButton;

    UserDAO userDAO;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        username = mainBinding.editTextUsername;
        password = mainBinding.editText;
        loginButton = mainBinding.buttonLogIn;
        createButton = mainBinding.buttonCreate;
        userDAO = Room.databaseBuilder(this, AppDataBaseUser.class, AppDataBaseUser.DATABASE_NAME)
                .allowMainThreadQueries().build().UserDAO();

        addUser();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (login()){
                    startActivity(LandingPageActivity.getIntent(getApplicationContext(), user.getUserId()));
                }
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CreateAccountActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    boolean login(){
        String un = username.getText().toString();
        String pw = password.getText().toString();
        List<User> users = userDAO.getUserByName(un);

        //No such user
        if (users.isEmpty()){
            return false;
        }

        user = users.get(0);
        return user.getPassword().equals(pw);
    }

    public static Intent getIntent(Context context){
        return new Intent(context, MainActivity.class);
    }

    void addUser(){
        if (userDAO.getUserByName("testuser1").isEmpty()){
            userDAO.Insert(new User("testuser1", "testuser1", 0, 0));
        }

        if (userDAO.getUserByName("admin2").isEmpty()){
            userDAO.Insert(new User("admin2", "admin2", 0, 1));
        }
    }
}