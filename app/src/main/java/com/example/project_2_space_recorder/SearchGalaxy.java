package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.room.Room;

import com.example.project_2_space_recorder.databinding.ActivitySearchGalaxyBinding;
import com.example.project_2_space_recorder.databinding.ActivitySearchSolarSystemBinding;

import java.util.ArrayList;

import Universe_DB.AppDataBaseSpace;
import Universe_DB.GalaxyDAO;
import Universe_DB.SolarSystemDAO;
import Universe_Structure.Galaxy;
import Universe_Structure.SolarSystem;
import UserDB.AppDataBaseUser;
import UserDB.User;
import UserDB.UserDAO;

public class SearchGalaxy extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ActivitySearchGalaxyBinding mainBinding;
    int USERID;
    private static final String idGetter = "Search.userID";
    User USER;
    UserDAO userDAO;
    GalaxyDAO galaxyDAO;
    ListView listView;

    ArrayList<Galaxy> searchList;
    ArrayAdapter<Galaxy> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_galaxy);
        setupUser();

        if(listView != null){
            if(adapter != null){
                listView.setAdapter(adapter);
            }
        }
        else{
            System.out.println("Bad Stuff");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Galaxy g = (Galaxy) adapterView.getItemAtPosition(i);
                System.out.println(g.getGalaxyId() + " " + g.getName());
                startActivity(DisplayGalaxyActivity.getIntent(getApplicationContext(), USERID, g.getGalaxyId()));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.home_icon);
        item.setIcon(R.drawable.baseline_home_24); //your desired icon here
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(LandingPageActivity.getIntent(getApplicationContext(), USERID));
                return false;
            }
        });

        MenuItem searchItem = menu.findItem(R.id.search_icon);
        SearchView searchView;
        searchView = (SearchView) searchItem.getActionView();
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

        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    void setupVariables(){
        mainBinding = ActivitySearchGalaxyBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        // initialise ListView with id
        listView = findViewById(R.id.list_all_galaxies);
        galaxyDAO = AppDataBaseSpace.getInstance(getApplicationContext()).GalaxyDAO();
        searchList = (ArrayList<Galaxy>) galaxyDAO.getGalaxies();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, searchList);
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
        Intent intent = new Intent(context, SearchGalaxy.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
