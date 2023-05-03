package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.CreateAccountBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;
import Utils.Toaster;

public class CreateAccountActivity extends AppCompatActivity {
    
    CreateAccountBinding mainBinding;
    EditText username;
    EditText password;
    EditText password2;
    Button createButton;
    UserDAO userDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        setupVariables();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (createUser()) {
                    Intent intent = MainActivity.getIntent(getApplicationContext());
                    startActivity(intent);
                }
            }
        });
    }

    boolean createUser(){
        String un = username.getText().toString();
        String text = "Username already taken";

        if (userDAO.getUserByName(un).isEmpty()){
            String pw = password.getText().toString();
            String pw2 = password2.getText().toString();
            if (!pw.equals("") && pw.equals(pw2)){
                userDAO.Insert(new User(un, pw, 0, 0));
                text = "User Created!";
                Toaster.showToast(getApplicationContext(), text, Color.GREEN);
                return true;
            }
            //Error: not same password
            text = "Invalid password";
            Toaster.showToast(getApplicationContext(), text, Color.RED);
            return false;
        }
        //Error: username taken
        Toaster.showToast(getApplicationContext(), text, Color.RED);
        return false;
    }


    void setupVariables(){
        mainBinding = CreateAccountBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        username = mainBinding.editTextCrtUsername;
        password = mainBinding.editTextCrtPassword;
        password2 = mainBinding.editTextReenterPassword;
        createButton = mainBinding.buttonCreateAccount;

        userDAO = AppDataBaseUser.getInstance(getApplicationContext()).UserDAO();
    }

    public static Intent getIntent(Context context){
        return new Intent(context, CreateAccountActivity.class);
    }
}