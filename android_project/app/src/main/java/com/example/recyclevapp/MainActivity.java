package com.example.recyclevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<PokemonModel> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter adapter;
    private SearchView searchView;
    // Inside your MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSet = new ArrayList<>();
        recyclerView = findViewById(R.id.resView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Assuming you have arrays for Pokemon data similar to myPokemonData
        for (int i = 0; i < myPokemonData.nameArray.length; i++) {
            dataSet.add(new PokemonModel(
                    myPokemonData.nameArray[i],
                    myPokemonData.typeArray[i],
                    myPokemonData.drawableArray[i],
                    myPokemonData.id_[i]
            ));
        }

        // Pass both dataSet and this as context
        adapter = new CustomeAdapter(dataSet, this);
        recyclerView.setAdapter(adapter);
        // Move the following lines inside the onCreate method
        searchView = findViewById(R.id.searchView);
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
    }
}
