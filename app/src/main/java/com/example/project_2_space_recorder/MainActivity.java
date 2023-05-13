package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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
import Utils.Toaster;

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

        setupVariables();

        addUser();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "Login failed";
                int color = Color.RED;

                if (login()){
                    addSpaceObjects();
                    userDAO.Update(user);
                    text = "Login successful";
                    color = Color.GREEN;
                    Toaster.showToast(getApplicationContext(), text, color);
                    startActivity(LandingPageActivity.getIntent(getApplicationContext(), user.getUserId()));
                    return;
                }
                Toaster.showToast(getApplicationContext(), text, color);
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

    void addUser(){
        if (userDAO.getUserByName("testuser1").isEmpty()){
            userDAO.Insert(new User("testuser1", "testuser1", 0, 0));
        }

        if (userDAO.getUserByName("admin2").isEmpty()){
            userDAO.Insert(new User("admin2", "admin2", 0, 1));
        }
    }

    void addSpaceObjects(){
        if (solarSystemDAO.getSolarSystemByName("Sol") == null){
            SolarSystem s = new SolarSystem("Sol", "Unknown");
            s.setGalaxyId(0);
            solarSystemDAO.Insert(s);
        }

        if (galaxyDAO.getGalaxyByName("Milky Way") == null){
            Galaxy g = new Galaxy("Milky Way", "Unknown");
            galaxyDAO.Insert(g);
        }
    }

    void setupVariables(){
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
    }

    public static Intent getIntent(Context context){
        return new Intent(context, MainActivity.class);
    }
}