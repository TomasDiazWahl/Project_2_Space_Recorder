package com.example.project_2_space_recorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivityDisplayPlanetBinding;
import com.example.project_2_space_recorder.databinding.CreatePlanetDialogBinding;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.PlanetDAO;
import Universe_Structure.Planet;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class DisplayPlanetActivity extends AppCompatActivity {

    int USERID;
    int PLANETID;
    private static final String userIdGetter = "displayPlanet.userID";
    private static final String planetIdGetter = "displayPlanet.planetID";
    User USER;
    Planet PLANET;

    UserDAO userDAO;
    PlanetDAO planetDAO;


    @NonNull ActivityDisplayPlanetBinding mainBinding;
    TextView planetName;
    TextView planetID;
    Button editPlanetButton;
    Button deletePlanetButton;


    CreatePlanetDialog dialog;

    CreatePlanetDialogBinding dialogBinding;
    EditText dialogPlanetName;
    EditText dialogPlanetPopulation;
    EditText dialogPlanetClimate;
    EditText dialogPlanetSize;
    EditText dialogPlanetDistance;
    EditText dialogPlanetSystem;
    Button dialogPlanetApply;
    Button dialogPlanetCancel;

    ConfirmDialog confirmDialog;
    TextView question;
    TextView confirmCancelButton;
    TextView confirmConfirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_planet);
        setupUser();

        editPlanetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        deletePlanetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.show();
            }
        });

        dialogPlanetCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialogPlanetApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlanet();
                dialog.cancel();
            }
        });

        confirmCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.cancel();
            }
        });

        confirmConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                planetDAO.Delete(PLANET);
                confirmDialog.cancel();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    void updatePlanet(){
        String s;

        s = dialogPlanetName.getText().toString();
        if (!s.equals("")) {
            PLANET.setName(s);
            planetName.setText("Name: " + PLANET.getName());
        }

        s = dialogPlanetPopulation.getText().toString();
        if (!s.equals("")) {
            PLANET.setPopulation(s);
        }

        s = dialogPlanetClimate.getText().toString();
        if (!s.equals("")) {
            PLANET.setClimate(s);
        }

        s = dialogPlanetSize.getText().toString();
        if (!s.equals("")) {
            PLANET.setSize(Double.parseDouble(s));
        }

        s = dialogPlanetDistance.getText().toString();
        if (!s.equals("")) {
            PLANET.setDistanceFromStar(Double.parseDouble(s));
        }

        s = dialogPlanetSystem.getText().toString();
        if (!s.equals("")) {
            PLANET.setSolarSystemId(Integer.parseInt(s));
        }


        planetDAO.Update(PLANET);
        planetName.setText("Name: " + PLANET.getName());
    }




    @SuppressLint("SetTextI18n")
    void setupVariables(){
        mainBinding = ActivityDisplayPlanetBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        planetName = mainBinding.viewPlanetName;
        planetName.setText(PLANET.getName());
        planetID = mainBinding.viewPlanetID;
        planetID.setText(PLANET.getPlanetId());
        editPlanetButton = mainBinding.editPlanet;
        deletePlanetButton = mainBinding.deletePlanet;

        dialog = new CreatePlanetDialog(DisplayPlanetActivity.this);
        dialogBinding = CreatePlanetDialogBinding.inflate(dialog.getLayoutInflater());
        dialogPlanetName = dialog.findViewById(R.id.edit_text_name);
        dialogPlanetName.setText(PLANET.getName());
        dialogPlanetPopulation = dialog.findViewById(R.id.edit_text_population);
        dialogPlanetPopulation.setText(PLANET.getPopulation());
        dialogPlanetClimate = dialog.findViewById(R.id.edit_text_climate);
        dialogPlanetClimate.setText(PLANET.getClimate());
        dialogPlanetSize = dialog.findViewById(R.id.edit_text_size);
        dialogPlanetSize.setText(PLANET.getSize() + "");
        dialogPlanetDistance = dialog.findViewById(R.id.edit_text_distance);
        dialogPlanetDistance.setText(PLANET.getDistanceFromStar() + "");
        dialogPlanetSystem = dialog.findViewById(R.id.edit_text_system);
        dialogPlanetSystem.setText(PLANET.getSolarSystemId() + "");
        dialogPlanetApply = dialog.findViewById(R.id.button_apply);
        dialogPlanetCancel = dialog.findViewById(R.id.button_cancel);

        confirmDialog = new ConfirmDialog(DisplayPlanetActivity.this);
        question = confirmDialog.findViewById(R.id.question);
        question.setText("Do u wanna delete lol?");
        confirmCancelButton = confirmDialog.findViewById(R.id.cancel);
        confirmConfirmButton = confirmDialog.findViewById(R.id.confirm);


        if ((USER.getIsAdmin() == 1 || PLANET.getDiscoverer().equals(USER.getName()) && planetDAO.getPlanetByOwner(USER.getName()).size() > 1)){
            editPlanetButton.setVisibility(View.VISIBLE);
            deletePlanetButton.setVisibility(View.VISIBLE);
        }
    }

    void setupUser(){
        planetDAO = AppDataBaseSpace.getInstance(getApplicationContext()).PlanetDAO();
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(userIdGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        PLANETID = getIntent().getIntExtra(planetIdGetter, 0);
        PLANET = planetDAO.getPlanetById(PLANETID);

        setupVariables();
    }



    public static Intent getIntent(Context context, int userID, int planetID){
        Intent intent = new Intent(context, DisplayPlanetActivity.class);
        intent.putExtra(userIdGetter, userID);
        intent.putExtra(planetIdGetter, planetID);
        return intent;
    }
}