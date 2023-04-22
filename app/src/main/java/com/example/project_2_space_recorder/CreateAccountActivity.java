package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_2_space_recorder.databinding.CreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity {
    
    CreateAccountBinding mainBinding;
    EditText username;
    EditText password;
    EditText password2;
    Button createButton;



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

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context){
        return new Intent(context, CreateAccountActivity.class);
    }
}