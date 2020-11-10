package com.mustafabayar.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerview
        mRecyclerView = findViewById(R.id.recycler_view);
        //set its properties
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //LinearLayout
       //mRecyclerView.setLayoutManager(new GridLayoutManager(this,2)); //GridLayout

        //adapter
        myAdapter = new MyAdapter(this,getPlayers());
        mRecyclerView.setAdapter(myAdapter);
    }

    //add models yo arraylist
    private ArrayList<Model> getPlayers(){
        ArrayList<Model> models = new ArrayList<>();

        Model p = new Model();
        p.setTitle("Kenny");
        p.setDescription("Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.Lorem Ipsum Kenny.");
        p.setImg(R.drawable.kenny);
        models.add(p);

        p = new Model();
        p.setTitle("LondonBridge");
        p.setDescription("Lorem Ipsum London Bridge.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
        p.setImg(R.drawable.londonbridge);
        models.add(p);

        p = new Model();
        p.setTitle("Collesseum");
        p.setDescription("Lorem Ipsum Collesseum.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
        p.setImg(R.drawable.colosseum);
        models.add(p);

        p = new Model();
        p.setTitle("Dunya");
        p.setDescription("Lorem Ipsum Dunya.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
        p.setImg(R.drawable.dunya);
        models.add(p);

        p = new Model();
        p.setTitle("Eiffel");
        p.setDescription("Lorem Ipsum Eiffel.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
        p.setImg(R.drawable.eiffel);
        models.add(p);

        p = new Model();
        p.setTitle("Pisa");
        p.setDescription("Lorem Ipsum Pisa.Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .Lorem Ipsum .");
        p.setImg(R.drawable.pisa);
        models.add(p);

        return  models;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //called when you click search
                myAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //called whenever you type each letter in searchview
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
            return  true;

        }
        return super.onOptionsItemSelected(item);
    }
}