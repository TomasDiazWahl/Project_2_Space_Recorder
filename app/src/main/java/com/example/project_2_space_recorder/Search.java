package com.example.project_2_space_recorder;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivitySearchBinding;

import java.util.List;

import Universe_Structure.SpaceObject;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener{
    ActivitySearchBinding mainBinding;
    SearchView searchView;

    int USERID;
    private static final String idGetter = "Search.userID";
    User USER;

    UserDAO userDAO;
    ListView listView;
    ListAdapter adapter;
    List<SpaceObject> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupUser();


    }

    void setupVariables(){
        mainBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        // initialise ListView with id
        listView = findViewById(R.id.listView);
    }

    void setupUser(){
        userDAO = Room.databaseBuilder(this, AppDataBaseUser.class, AppDataBaseUser.DATABASE_NAME)
                .allowMainThreadQueries().build().UserDAO();
        USERID = getIntent().getIntExtra(idGetter, 0);
        USER = userDAO.getUserById(USERID).get(0);


        setupVariables();
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
