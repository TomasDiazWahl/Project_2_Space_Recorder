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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivityDisplaySystemBinding;
import Universe_DB.AppDataBaseSpace;
import Universe_DB.SolarSystemDAO;
import Universe_Structure.SolarSystem;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class DisplaySystemActivity extends AppCompatActivity {

    int USERID;
    int SYSTEMID;
    private static final String userIdGetter = "displaySystem.userID";
    private static final String systemIdGetter = "displaySystem.systemID";
    User USER;
    SolarSystem SYSTEM;

    UserDAO userDAO;
    SolarSystemDAO solarSystemDAO;


    @NonNull
    ActivityDisplaySystemBinding mainBinding;
    TextView systemName;
    TextView systemID;
    Button editSystemButton;
    Button deleteSystemButton;



    CreateSystemDialog dialog;
    EditText dialogSystemName;
    EditText dialogStar;
    EditText dialogDistance;
    EditText dialogGalaxy;
    Button dialogApply;
    Button dialogCancel;

    ConfirmDialog confirmDialog;
    TextView question;
    TextView confirmCancelButton;
    TextView confirmConfirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_system);
        setupUser();

        editSystemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        deleteSystemButton.setOnClickListener(new View.OnClickListener() {
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
                updateSystem();
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
                solarSystemDAO.Delete(SYSTEM);
                confirmDialog.cancel();
                startActivity(LandingPageActivity.getIntent(getApplicationContext(), USERID));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item=menu.add("Home"); //your desired title here
        item.setIcon(R.drawable.baseline_home_24); //your desired icon here
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

    void updateSystem(){
        String s;

        s = dialogSystemName.getText().toString();
        if (!s.equals("")) {
            SYSTEM.setName(s);
            dialogSystemName.setText(SYSTEM.getName());
        }

        s = dialogStar.getText().toString();
        if (!s.equals("")) {
            SYSTEM.setTypeOfStar(s);
        }

        s = dialogDistance.getText().toString();
        if (!s.equals("")) {
            SYSTEM.setDistanceFromGalaxyCenter(Double.parseDouble(s));
        }

        s = dialogGalaxy.getText().toString();
        if (!s.equals("")) {
            SYSTEM.setGalaxyId(Integer.parseInt(s));
        }


        solarSystemDAO.Update(SYSTEM);
        dialogSystemName.setText(SYSTEM.getName());
    }

    @SuppressLint("SetTextI18n")
    void showDialog(){
        if (SYSTEM.getName() != null && !SYSTEM.getName().equals(""))
        {
            dialogSystemName.setText(SYSTEM.getName());
        }

        if (SYSTEM.getTypeOfStar() != null && !SYSTEM.getTypeOfStar().equals(""))
        {
            dialogStar.setText(SYSTEM.getTypeOfStar());
        }
        dialogDistance.setText(SYSTEM.getDistanceFromGalaxyCenter() + "");
        dialogGalaxy.setText(SYSTEM.getGalaxyId() + "");

        dialog.show();
    }




    @SuppressLint("SetTextI18n")
    void setupVariables(){
        mainBinding = ActivityDisplaySystemBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        systemName = mainBinding.viewSystemName;
        systemName.setText("Name: " + SYSTEM.getName());
        systemID = mainBinding.viewSystemID;
        systemID.setText("ID: " + SYSTEM.getSolarSystemId());
        editSystemButton = mainBinding.editSystem;
        deleteSystemButton = mainBinding.deleteSystem;

        dialog = new CreateSystemDialog(DisplaySystemActivity.this);
        dialogSystemName = dialog.findViewById(R.id.edit_text_name);
        dialogStar = dialog.findViewById(R.id.edit_text_star);
        dialogDistance = dialog.findViewById(R.id.edit_text_distance);
        dialogGalaxy = dialog.findViewById(R.id.edit_text_galaxy);
        dialogApply = dialog.findViewById(R.id.button_apply);
        dialogCancel = dialog.findViewById(R.id.button_cancel);

        confirmDialog = new ConfirmDialog(DisplaySystemActivity.this);
        question = confirmDialog.findViewById(R.id.question);
        question.setText("Do u wanna delete lol?");
        confirmCancelButton = confirmDialog.findViewById(R.id.cancel);
        confirmConfirmButton = confirmDialog.findViewById(R.id.confirm);


        if ((USER.getIsAdmin() == 1 || SYSTEM.getDiscoverer().equals(USER.getName()) && solarSystemDAO.getSolarSystemByOwner(USER.getName()).size() > 1)){
            editSystemButton.setVisibility(View.VISIBLE);
            deleteSystemButton.setVisibility(View.VISIBLE);
        }
    }

    void setupUser(){
        solarSystemDAO = AppDataBaseSpace.getInstance(getApplicationContext()).SolarSystemDAO();
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(userIdGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        SYSTEMID = getIntent().getIntExtra(systemIdGetter, 0);
        SYSTEM = solarSystemDAO.getSolarSystemById(SYSTEMID);

        setupVariables();
    }



    public static Intent getIntent(Context context, int userID, int systemID){
        Intent intent = new Intent(context, DisplaySystemActivity.class);
        intent.putExtra(userIdGetter, userID);
        intent.putExtra(systemIdGetter, systemID);
        return intent;
    }
}