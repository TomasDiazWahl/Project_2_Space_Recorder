package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivityCreatePlanetBinding;
import com.example.project_2_space_recorder.databinding.CreatePlanetDialogBinding;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.PlanetDAO;
import Universe_Structure.Planet;
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

    Planet planet;
    Button backButton;
    Button createPlanetButton;

    CreatePlanetDialog dialog;
    CreatePlanetDialogBinding dialogBinding;
    EditText dialogPlanetName;
    EditText dialogPlanetPopulation;
    EditText dialogClimate;
    EditText dialogSize;
    EditText dialogDistance;
    EditText dialogSystem;
    Button dialogApply;
    Button dialogCancel;


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

        createPlanetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlanet();
                dialog.cancel();
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

    void showDialog(){
        dialogPlanetName.setText(planet.getName());
        dialogPlanetPopulation.setText(planet.getPopulation());
        dialogClimate.setText(planet.getClimate());
        dialog.show();
    }

    void updatePlanet(){
        String s;

        s = dialogPlanetName.getText().toString();
        if (!s.equals("")) {
            planet.setName(s);
            dialogPlanetName.setText("Name: " + planet.getName());
        }

        s = dialogPlanetPopulation.getText().toString();
        if (!s.equals("")) {
            planet.setPopulation(s);
        }

        s = dialogClimate.getText().toString();
        if (!s.equals("")) {
            planet.setClimate(s);
        }

        s = dialogSize.getText().toString();
        if (!s.equals("")) {
            planet.setSize(Double.parseDouble(s));
        }

        s = dialogDistance.getText().toString();
        if (!s.equals("")) {
            planet.setDistanceFromStar(Double.parseDouble(s));
        }

        s = dialogSystem.getText().toString();
        if (!s.equals("")) {
            planet.setSolarSystemId(Integer.parseInt(s));
        }


        planetDAO.Update(planet);
        dialogPlanetName.setText("Name: " + planet.getName());
    }



    void setupVariables(){
        mainBinding = ActivityCreatePlanetBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        backButton = mainBinding.buttonBack;
        createPlanetButton = mainBinding.createPlanetButton;
        planetDAO = AppDataBaseSpace.getInstance(getApplicationContext()).PlanetDAO();
        planet = new Planet("New Planet", USER.getName());

        dialog = new CreatePlanetDialog(CreateNewPlanetActivity.this);
        dialogBinding = CreatePlanetDialogBinding.inflate(dialog.getLayoutInflater());
        dialogPlanetName = dialog.findViewById(R.id.edit_text_name);
        dialogPlanetPopulation = dialog.findViewById(R.id.edit_text_population);
        dialogClimate = dialog.findViewById(R.id.edit_text_climate);
        dialogSize = dialog.findViewById(R.id.edit_text_size);
        dialogDistance = dialog.findViewById(R.id.edit_text_distance);
        dialogSystem = dialog.findViewById(R.id.edit_text_system);
        dialogApply = dialog.findViewById(R.id.button_apply);
        dialogCancel = dialog.findViewById(R.id.button_cancel);
    }

    void setupUser(){
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        System.out.println("LandingPage " + USER);
        setupVariables();
    }

    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, com.example.project_2_space_recorder.CreateNewPlanetActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
