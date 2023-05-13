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

import com.example.project_2_space_recorder.databinding.ActivityDisplayGalaxyBinding;
import com.example.project_2_space_recorder.databinding.CreateGalaxyDialogBinding;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.GalaxyDAO;
import Universe_Structure.Galaxy;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class DisplayGalaxyActivity extends AppCompatActivity {

    int USERID;
    int GALAXYID;
    private static final String userIdGetter = "displayGalaxy.userID";
    private static final String galaxyIdGetter = "displayGalaxy.galaxyID";
    User USER;
    Galaxy GALAXY;

    UserDAO userDAO;
    GalaxyDAO galaxyDAO;


    @NonNull
    ActivityDisplayGalaxyBinding mainBinding;
    TextView viewGalaxyName;
    TextView viewGalaxyID;
    Button editGalaxyButton;
    Button deleteGalaxyButton;



    CreateGalaxyDialog dialog;
    CreateGalaxyDialogBinding dialogBinding;
    EditText dialogGalaxyName;
    EditText dialogColor;
    Button dialogApply;
    Button dialogCancel;
    

    ConfirmDialog confirmDialog;
    TextView question;
    TextView confirmCancelButton;
    TextView confirmConfirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_galaxy);
        setupUser();

        editGalaxyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        deleteGalaxyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.show();
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
                galaxyDAO.Delete(GALAXY);
                confirmDialog.cancel();
            }
        });
    }


    void updateGalaxy(){
        String s;

        s = dialogGalaxyName.getText().toString();
        if (!s.equals("")) {
            GALAXY.setName(s);
        }

        s = dialogColor.getText().toString();
        if (!s.equals("")) {
            GALAXY.setColor(s);
        }


        galaxyDAO.Update(GALAXY);
        dialogGalaxyName.setText(GALAXY.getName());
    }




    @SuppressLint("SetTextI18n")
    void setupVariables(){
        mainBinding = ActivityDisplayGalaxyBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        viewGalaxyName = mainBinding.viewGalaxyName;
        viewGalaxyName.setText(GALAXY.getName());
        viewGalaxyID = mainBinding.viewGalaxyID;
        viewGalaxyID.setText(GALAXY.getGalaxyId());
        editGalaxyButton = mainBinding.editGalaxy;
        deleteGalaxyButton = mainBinding.deleteGalaxy;

        dialog = new CreateGalaxyDialog(DisplayGalaxyActivity.this);
        dialogBinding = CreateGalaxyDialogBinding.inflate(dialog.getLayoutInflater());
        dialogGalaxyName = dialog.findViewById(R.id.edit_text_name);
        dialogColor = dialog.findViewById(R.id.edit_text_star);
        dialogApply = dialog.findViewById(R.id.button_apply);
        dialogCancel = dialog.findViewById(R.id.button_cancel);

        confirmDialog = new ConfirmDialog(DisplayGalaxyActivity.this);
        question = confirmDialog.findViewById(R.id.question);
        question.setText("Do u wanna delete lol?");
        confirmCancelButton = confirmDialog.findViewById(R.id.cancel);
        confirmConfirmButton = confirmDialog.findViewById(R.id.confirm);


        if ((USER.getIsAdmin() == 1 || GALAXY.getDiscoverer().equals(USER.getName()) && galaxyDAO.getGalaxiesByOwner(USER.getName()).size() > 1)){
            editGalaxyButton.setVisibility(View.VISIBLE);
            deleteGalaxyButton.setVisibility(View.VISIBLE);
        }
    }

    void setupUser(){
        galaxyDAO = AppDataBaseSpace.getInstance(getApplicationContext()).GalaxyDAO();
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(userIdGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        GALAXYID = getIntent().getIntExtra(galaxyIdGetter, 0);
        GALAXY = galaxyDAO.getGalaxyById(GALAXYID);

        setupVariables();
    }



    public static Intent getIntent(Context context, int userID, int galaxyID){
        Intent intent = new Intent(context, DisplayGalaxyActivity.class);
        intent.putExtra(userIdGetter, userID);
        intent.putExtra(galaxyIdGetter, galaxyID);
        return intent;
    }
}