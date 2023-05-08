package com.example.project_2_space_recorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivityLandingPageBinding;
import com.example.project_2_space_recorder.databinding.CreatePlanetDialogBinding;

import java.util.List;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.PlanetDAO;
import Universe_Structure.Planet;
import Universe_Structure.SpaceObject;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class LandingPageActivity extends AppCompatActivity {

    int USERID;
    private static final String idGetter = "LandingPage.userID";
    User USER;
    Planet planet;

    UserDAO userDAO;
    PlanetDAO planetDAO;
    ActivityLandingPageBinding mainBinding;
    Button profileButton;
    TextView planetName;
    Button adminButton;
    Button logOutButton;
    Button exploreButton;
    Button editPlanetButton;

    List<String> spaceObjectImages;

    SpaceObject objectToDisplay;
    Menu menu;

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
        setContentView(R.layout.activity_landing_page);
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

        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ExploreActivity.getIntent(getApplicationContext(), USERID));
            }
        });

        editPlanetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
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
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }



    @SuppressLint("SetTextI18n")
    void setupVariables(){
        mainBinding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        profileButton = mainBinding.buttonProfile;
        planetName = mainBinding.planetName;
        adminButton = mainBinding.admin;
        logOutButton = mainBinding.buttonLogOut;
        exploreButton = mainBinding.buttonExplore;
        editPlanetButton = mainBinding.buttonEditPlanet;

        dialog = new CreatePlanetDialog(LandingPageActivity.this);
        dialogBinding = CreatePlanetDialogBinding.inflate(dialog.getLayoutInflater());
        dialogPlanetName = dialog.findViewById(R.id.edit_text_name);
        dialogPlanetName.setText(planet.getName());
        dialogPlanetPopulation = dialog.findViewById(R.id.edit_text_population);
        dialogPlanetPopulation.setText(planet.getPopulation());
        dialogClimate = dialog.findViewById(R.id.edit_text_climate);
        dialogClimate.setText(planet.getClimate());
        dialogSize = dialog.findViewById(R.id.edit_text_size);
        dialogSize.setText(planet.getSize() + "");
        dialogDistance = dialog.findViewById(R.id.edit_text_distance);
        dialogDistance.setText(planet.getDistanceFromStar() + "");
        dialogSystem = dialog.findViewById(R.id.edit_text_system);
        dialogSystem.setText(planet.getSolarSystemId() + "");
        dialogApply = dialog.findViewById(R.id.button_apply);
        dialogCancel = dialog.findViewById(R.id.button_cancel);


        profileButton.setText(USER.getName().substring(0, 1));
        //planetID.setText("Planet ID: " + planet.getPlanetId());
        planetName.setText("Name: " + planet.getName());


        if (USER.getIsAdmin() == 1){
            adminButton.setVisibility(View.VISIBLE);
        }
    }

    void setupUser(){
        planetDAO = AppDataBaseSpace.getInstance(getApplicationContext()).PlanetDAO();
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);

        List<Planet> p = planetDAO.getPlanetByOwner(USER.getName());

        if (p.isEmpty()){
            planet = new Planet("Home Planet", USER.getName());
            planetDAO.Insert(planet);
        }
        else{
            planet = p.get(0);
        }

        setupVariables();
    }

    @SuppressLint("SetTextI18n")
    void updatePlanet(){
        String s;

        s = dialogPlanetName.getText().toString();
        if (!s.equals("")) {
            planet.setName(s);
            planetName.setText("Name: " + planet.getName());
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
        planetName.setText("Name: " + planet.getName());
    }


    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, LandingPageActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
