package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivityMainBinding;

import java.util.List;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.GalaxyDAO;
import Universe_DB.PlanetDAO;
import Universe_DB.SolarSystemDAO;
import Universe_Structure.Galaxy;
import Universe_Structure.SolarSystem;
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
    PlanetDAO planetDAO;
    SolarSystemDAO solarSystemDAO;
    GalaxyDAO galaxyDAO;

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
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        planetDAO = AppDataBaseSpace.getInstance(getApplicationContext()).PlanetDAO();
        solarSystemDAO = AppDataBaseSpace.getInstance(getApplicationContext()).SolarSystemDAO();
        galaxyDAO = AppDataBaseSpace.getInstance(getApplicationContext()).GalaxyDAO();

        addUser();
        addSpaceObjects();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast t = new Toast(getApplicationContext());
                t.setText("Invalid name or password");
                if (login()){
                    t.setText("Successfully logged in");
                    startActivity(LandingPageActivity.getIntent(getApplicationContext(), user.getUserId()));
                }
                t.show();
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

    void addSpaceObjects(){
        if (solarSystemDAO.getSolarSystemByName("Sol").isEmpty()){
            solarSystemDAO.Insert(new SolarSystem("Sol", "Unknown",  0));
        }

        if (galaxyDAO.getGalaxyByName("Milky Way").isEmpty()){
            galaxyDAO.Insert(new Galaxy("Milky Way", "Unknown"));
        }
    }
}