package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.CreateAccountBinding;

import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

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

        mainBinding = CreateAccountBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        username = mainBinding.editTextCrtUsername;
        password = mainBinding.editTextCrtPassword;
        password2 = mainBinding.editTextReenterPassword;
        createButton = mainBinding.buttonCreateAccount;

        userDAO = Room.databaseBuilder(this, AppDataBaseUser.class, AppDataBaseUser.DATABASE_NAME)
                .allowMainThreadQueries().build().UserDAO();

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
        Toast t = new Toast(getApplicationContext());

        if (userDAO.getUserByName(un).isEmpty()){
            String pw = password.getText().toString();
            String pw2 = password2.getText().toString();
            if (!pw.equals("") && pw.equals(pw2)){
                userDAO.Insert(new User(un, pw, 0, 0));
                System.out.println("User Created");
                t.setText("Successfully logged in");
                t.show();
                return true;
            }
            //Error: not same password
            t.setText("Passwords are not the same");
            t.show();
            return false;
        }
        //Error: username taken
        t.setText("Username already taken");
        t.show();
        return false;
    }

    public static Intent getIntent(Context context){
        return new Intent(context, CreateAccountActivity.class);
    }
}