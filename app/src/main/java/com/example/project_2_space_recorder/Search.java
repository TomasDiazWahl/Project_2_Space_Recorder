package com.example.project_2_space_recorder;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import Universe_Structure.Planet;
import Universe_Structure.PlanetListItem;
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

    ArrayList<Planet> searchList;
    ArrayAdapter<Planet> adapter;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Planet p = (Planet) adapterView.getItemAtPosition(i);
                System.out.println(p.getPlanetId() + " " + p.getName());
                Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
                startActivity(intent);
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
//        searchItem.setIcon(R.drawable.baseline_search_24);
//        searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
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
        mainBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        // initialise ListView with id
        listView = findViewById(R.id.listAllPlanets);
        planetDAO = AppDataBaseSpace.getInstance(getApplicationContext()).PlanetDAO();
        searchList = (ArrayList<Planet>) planetDAO.getPlanets();
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
        Intent intent = new Intent(context, Search.class);
        intent.putExtra(idGetter, userID);
        return intent;
    }
}
