package com.example.project_2_space_recorder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.project_2_space_recorder.databinding.ActivityExploreBinding;
import com.example.project_2_space_recorder.databinding.ActivitySearchBinding;

public class Search extends AppCompatActivity {
    ActivitySearchBinding mainBinding;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        mainBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());



    }
}
