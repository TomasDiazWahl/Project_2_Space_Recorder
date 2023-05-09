package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import Utils.Toaster;


public class CreateNewPlanetActivity extends AppCompatActivity {
    ActivityCreatePlanetBinding mainBinding;
    private static final String idGetter = "LandingPage.userID";

    int USERID;
    User USER;
    UserDAO userDAO;
    PlanetDAO planetDAO;

    Planet planet;
    Button backButton;
    Button buttonSubmit;
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

        dialogApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlanet();
                Toaster.showToast(getApplicationContext(), "Changes saved!", Color.GREEN);
                dialog.cancel();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPlanet()){
                    Toaster.showToast(getApplicationContext(), "Planet Created!", Color.GREEN);
                    planetDAO.Insert(planet);
                    startActivity(LandingPageActivity.getIntent(getApplicationContext(), USERID));
                }
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
        if (planet.getName() != null && !planet.getName().equals(""))
        {
            dialogPlanetName.setText(planet.getName());
        }

        if (planet.getPopulation() != null && !planet.getPopulation().equals(""))
        {
            dialogPlanetPopulation.setText(planet.getPopulation());
        }

        if (planet.getClimate() != null && !planet.getClimate().equals(""))
        {
            dialogClimate.setText(planet.getClimate());
        }
        dialog.show();
    }

    void updatePlanet(){
        String s;

        s = dialogPlanetName.getText().toString();
        if (!s.equals("")) {
            planet.setName(s);
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
        dialogPlanetName.setText(planet.getName());
    }

    boolean checkPlanet(){
        if (planetDAO.getPlanetByName(planet.getName()) != null) {
            Toaster.showToast(getApplicationContext(), "Planet name already taken", Color.RED);
            planet.setName("");
            return false;
        }
        return true;
    }



    void setupVariables(){
        mainBinding = ActivityCreatePlanetBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        backButton = mainBinding.buttonBack;
        buttonSubmit = mainBinding.buttonSubmit;
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
        setupVariables();
    }

    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, com.example.project_2_space_recorder.CreateNewPlanetActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
