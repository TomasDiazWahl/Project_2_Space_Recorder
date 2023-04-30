package com.example.project_2_space_recorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivityExploreBinding;
import com.example.project_2_space_recorder.databinding.CreateAccountBinding;

import UserDB.AppDataBaseUser;

public class ExploreActivity extends AppCompatActivity {
    ActivityExploreBinding  mainBinding;
    Button findNewPlanet;
    Button findNewSolarSystem;
    Button wormHole;
    Button searchTheUniverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        mainBinding = ActivityExploreBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

    }
}
