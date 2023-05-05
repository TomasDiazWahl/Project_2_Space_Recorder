package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.ActivityUltraSuperSecretBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class UltraSuperSecretActivity extends AppCompatActivity {
    ActivityUltraSuperSecretBinding mainBinding;
    private static final String idGetter = "LandingPage.userID";

    int USERID;
    User USER;
    UserDAO userDAO;
    EditText query;
    TextView answer;
    Button confirm;
//    Button backButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_super_secret);
        setupUser();

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            startActivity(AdminMenuActivity.getIntent(getApplicationContext(), USERID));
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("42");
            }
        });

//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(SuperSecretActivity.getIntent(getApplicationContext(), USERID));
//            }
//        });
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

    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, com.example.project_2_space_recorder.UltraSuperSecretActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }

    void setupVariables(){
        mainBinding = ActivityUltraSuperSecretBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        query = mainBinding.query;
        answer = mainBinding.fortyTwo;
        confirm = mainBinding.confirmButton;
//        backButton = mainBinding.buttonBack;
    }

    void setupUser(){
        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
        setupVariables();
    }
}
