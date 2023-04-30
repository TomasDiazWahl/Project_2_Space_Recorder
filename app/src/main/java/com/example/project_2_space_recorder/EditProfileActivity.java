package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivityEditprofileBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class EditProfileActivity extends AppCompatActivity {

    int USERID;
    private static final String idGetter = "EditProfile.userID";
    User USER;

    UserDAO userDAO;

    ActivityEditprofileBinding mainBinding;
    EditText username;
    EditText age;
    EditText password;
    EditText password2;
    TextView back;
    Button confirmChangesButton;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mainBinding = ActivityEditprofileBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        setupUser();
        setupVariables();
        System.out.println(USER);

        confirmChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validInput()) {
                    UpdateUser();
                    startActivity(ProfileActivity.getIntent(getApplicationContext(), USERID));
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ProfileActivity.getIntent(getApplicationContext(), USERID));
            }
        });
    }



    boolean validInput(){
        String un = username.getText().toString();
        String pw = password.getText().toString();
        String pw2 = password2.getText().toString();

        //Taken username
        if (!un.equals("") && !userDAO.getUserByName(un).isEmpty()){
            System.out.println("ok1");
            return false;
        }

        //Passwords not matching
        if (!pw.equals(pw2)){
            System.out.println("ok2");
            return false;
        }
        System.out.println("ok3");
        return true;
    }

    void UpdateUser(){
        String un = username.getText().toString();
        String a = age.getText().toString();
        String pw = password.getText().toString();

        if (!un.equals("")){
            USER.setName(un);
        }
        if (!a.equals("")){
            USER.setAge(Integer.parseInt(a));
        }
        if (!pw.equals("")){
            USER.setPassword(pw);
        }
        userDAO.Update(USER);
    }

    void setupVariables(){
        username = mainBinding.UsernameEdit;
        age = mainBinding.AgeDisplay;
        back = mainBinding.arrow;
        confirmChangesButton = mainBinding.confirmChangesButton;
    }

    void setupUser(){
        userDAO = Room.databaseBuilder(this, AppDataBaseUser.class, AppDataBaseUser.DATABASE_NAME)
                .allowMainThreadQueries().build().UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);
    }



    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, com.example.project_2_space_recorder.EditProfileActivity.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}