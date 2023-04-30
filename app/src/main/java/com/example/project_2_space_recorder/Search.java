package com.example.project_2_space_recorder;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.project_2_space_recorder.databinding.ActivityExploreBinding;
import com.example.project_2_space_recorder.databinding.ActivitySearchBinding;

import Universe_Structure.SpaceObject;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener{
    ActivitySearchBinding mainBinding;
    SearchView searchView;
    ListView listView;
    ListAdapter adapter;
    List<SpaceObject> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // initialise ListView with id
        listView = findViewById(R.id.listView);



        mainBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());



    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
