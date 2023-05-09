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

import com.example.project_2_space_recorder.databinding.ActivityCreateSolarSystemBinding;
import com.example.project_2_space_recorder.databinding.CreateSystemDialogBinding;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.SolarSystemDAO;
import Universe_Structure.SolarSystem;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;
import Utils.Toaster;

public class CreateNewSolarSystemActivity extends AppCompatActivity {
    ActivityCreateSolarSystemBinding mainBinding;
    private static final String idGetter = "LandingPage.userID";

    int USERID;
    User USER;
    SolarSystem system;
    UserDAO userDAO;
    SolarSystemDAO systemDAO;
    Button backButton;
    Button buttonSubmit;

    Button createSystemButton;

    CreateSystemDialog dialog;
    CreateSystemDialogBinding dialogBinding;
    EditText dialogSystemName;
    EditText dialogStar;
    EditText dialogDistance;
    EditText dialogGalaxy;
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

        createSystemButton.setOnClickListener(new View.OnClickListener() {
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
                updateSystem();
                Toaster.showToast(getApplicationContext(), "Changes saved!", Color.GREEN);
                dialog.cancel();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSystem()){
                    Toaster.showToast(getApplicationContext(), "Solar System Created!", Color.GREEN);
                    systemDAO.Insert(system);
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


    @SuppressLint("SetTextI18n")
    void showDialog(){
        if (system.getName() != null && !system.getName().equals(""))
        {
            dialogSystemName.setText(system.getName());
        }

        if (system.getTypeOfStar() != null && !system.getTypeOfStar().equals(""))
        {
            dialogStar.setText(system.getTypeOfStar());
        }
        dialogDistance.setText(system.getDistanceFromGalaxyCenter() + "");
        dialogGalaxy.setText(system.getGalaxyId() + "");

        dialog.show();
    }

    void updateSystem(){
        String s;

        s = dialogSystemName.getText().toString();
        if (!s.equals("")) {
            system.setName(s);
            dialogSystemName.setText(system.getName());
        }

        s = dialogStar.getText().toString();
        if (!s.equals("")) {
            system.setTypeOfStar(s);
        }

        s = dialogDistance.getText().toString();
        if (!s.equals("")) {
            system.setDistanceFromGalaxyCenter(Double.parseDouble(s));
        }

        s = dialogGalaxy.getText().toString();
        if (!s.equals("")) {
            system.setGalaxyId(Integer.parseInt(s));
        }


        systemDAO.Update(system);
        dialogSystemName.setText(system.getName());
    }

    boolean checkSystem(){
        if (systemDAO.getSolarSystemByName(system.getName()) != null){
            Toaster.showToast(getApplicationContext(), "System name already taken", Color.RED);
            system.setName("");
            return false;
        }
        return true;
    }




    void setupVariables(){
        mainBinding = ActivityCreateSolarSystemBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        backButton = mainBinding.buttonBack;

            mainBinding = ActivityCreateSolarSystemBinding.inflate(getLayoutInflater());
            setContentView(mainBinding.getRoot());
            backButton = mainBinding.buttonBack;
            buttonSubmit = mainBinding.buttonSubmit;
            createSystemButton = mainBinding.createSystemButton;
            systemDAO = AppDataBaseSpace.getInstance(getApplicationContext()).SolarSystemDAO();
            system = new SolarSystem("Your Solar System", USER.getName());

            dialog = new CreateSystemDialog(CreateNewSolarSystemActivity.this);
            dialogBinding = CreateSystemDialogBinding.inflate(dialog.getLayoutInflater());
            dialogSystemName = dialog.findViewById(R.id.edit_text_name);
            dialogStar = dialog.findViewById(R.id.edit_text_star);
            dialogDistance = dialog.findViewById(R.id.edit_text_distance);
            dialogGalaxy = dialog.findViewById(R.id.edit_text_galaxy);
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
        Intent intent = new Intent(context, com.example.project_2_space_recorder.CreateNewSolarSystemActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
