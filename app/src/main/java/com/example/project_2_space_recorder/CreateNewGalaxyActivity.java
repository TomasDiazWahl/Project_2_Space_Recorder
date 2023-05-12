/*
 * Authors: Tomas Diaz-Wahl & Alexis Petignat
 * Date: 2023-05-12
 * Purpose: This class allows a user to a new galaxy object to the database as the discoverer
 * */

package com.example.project_2_space_recorder;

import android.annotation.SuppressLint;
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

import com.example.project_2_space_recorder.databinding.ActivityCreateGalaxyBinding;
import com.example.project_2_space_recorder.databinding.CreateGalaxyDialogBinding;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.GalaxyDAO;
import Universe_Structure.Galaxy;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;
import Utils.Toaster;

public class CreateNewGalaxyActivity extends AppCompatActivity {
    ActivityCreateGalaxyBinding mainBinding;
    private static final String idGetter = "LandingPage.userID";

    int USERID;
    User USER;
    Galaxy galaxy;
    UserDAO userDAO;
    GalaxyDAO galaxyDAO;
    Button backButton;
    Button submitButton;
    Button createGalaxyButton;

    CreateGalaxyDialog dialog;
    CreateGalaxyDialogBinding dialogBinding;
    EditText dialogGalaxyName;
    EditText dialogColor;
    Button dialogApply;
    Button dialogCancel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_galaxy);
        setupUser();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galaxyDAO.Delete(galaxy);
                startActivity(ExploreActivity.getIntent(getApplicationContext(), USERID));
            }
        });

        createGalaxyButton.setOnClickListener(new View.OnClickListener() {
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
                updateGalaxy();
                Toaster.showToast(getApplicationContext(), "Changes saved!", Color.GREEN);
                dialog.cancel();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkGalaxy()){
                    Toaster.showToast(getApplicationContext(), "Galaxy Created!", Color.GREEN);
                    galaxyDAO.Update(galaxy);
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
                galaxyDAO.Delete(galaxy);
                startActivity(LandingPageActivity.getIntent(getApplicationContext(), USERID));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("SetTextI18n")
    void showDialog(){
        if (galaxy.getName() != null && !galaxy.getName().equals(""))
        {
            dialogGalaxyName.setText(galaxy.getName());
        }

        if (galaxy.getColor() != null && !galaxy.getColor().equals(""))
        {
            dialogColor.setText(galaxy.getColor());
        }
        dialog.show();
    }

    void updateGalaxy(){
        String s;

        s = dialogGalaxyName.getText().toString();
        if (!s.equals("")) {
            galaxy.setName(s);
        }

        s = dialogColor.getText().toString();
        if (!s.equals("")) {
            galaxy.setColor(s);
        }


        galaxyDAO.Update(galaxy);
        dialogGalaxyName.setText(galaxy.getName());
    }

    boolean checkGalaxy(){
        Galaxy g = galaxyDAO.getGalaxyByName(galaxy.getName());
        if (g != null && g.getGalaxyId() != galaxy.getGalaxyId()){
            Toaster.showToast(getApplicationContext(), "Galaxy name already taken", Color.RED);
            galaxy.setName("");
            return false;
        }
        return true;
    }

    void setupVariables(){
        mainBinding = ActivityCreateGalaxyBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        backButton = mainBinding.buttonBack;
        submitButton = mainBinding.buttonSubmit;

        createGalaxyButton = mainBinding.createGalaxyButton;
        galaxyDAO = AppDataBaseSpace.getInstance(getApplicationContext()).GalaxyDAO();
        galaxy = new Galaxy("Your Galaxy", USER.getName());
        galaxyDAO.Insert(galaxy);
        galaxy = galaxyDAO.getGalaxyByName(galaxy.Name);

        dialog = new CreateGalaxyDialog(CreateNewGalaxyActivity.this);
        dialogBinding = CreateGalaxyDialogBinding.inflate(dialog.getLayoutInflater());
        dialogGalaxyName = dialog.findViewById(R.id.edit_text_name);
        dialogColor = dialog.findViewById(R.id.edit_text_star);
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
        Intent intent = new Intent(context, com.example.project_2_space_recorder.CreateNewGalaxyActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
