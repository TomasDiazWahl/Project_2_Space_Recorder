package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.List;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.PlanetDAO;
import Universe_Structure.SpaceObject;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener{
    ActivitySearchBinding mainBinding;
    int USERID;
    private static final String idGetter = "Search.userID";
    User USER;
    UserDAO userDAO;
    PlanetDAO planetDAO;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupUser();

        if(listView != null){
            if(adapter != null){
                listView.setAdapter(adapter);
            }
        }
        else{
            System.out.println("Bad Stuff");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item=menu.add("Home"); //your desired title here
        item.setIcon(R.drawable.baseline_home_24); //your desired icon here
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        MenuItem item1=menu.add("Search");
        item1.setIcon(R.drawable.baseline_search_24);
        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        SearchView searchView = (SearchView) item1.getActionView();
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    void setupVariables(){
        mainBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        // initialise ListView with id
        listView = findViewById(R.id.listAllPlanets);
        planetDAO = AppDataBaseSpace.getInstance(getApplicationContext()).PlanetDAO();
        searchList = (ArrayList<String>) planetDAO.getAllPlanetsName();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searchList);
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

    public static Intent getIntent(Context context, int userID){
        Intent intent = new Intent(context, Search.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
