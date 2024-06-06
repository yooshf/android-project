package com.example.recyclevapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomeAdapter adapter;
    private ArrayList<DataModel> dataList;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        dataList = new ArrayList<>();
        dataList.add(new DataModel("Monkey D. Luffy", "The  rubber man", R.drawable.luffy));
        dataList.add(new DataModel("Zoro", "The swordsman", R.drawable.zoro));
        dataList.add(new DataModel("Portgas D. Ace", "The man of flames", R.drawable.ace));
        dataList.add(new DataModel("Boa Hancock(Pirate Empress)", "The woman with the power of love", R.drawable.boa));
        dataList.add(new DataModel("Brook(Soul King) ", "The music man", R.drawable.brok));
        dataList.add(new DataModel("God Enel", "The electricity man", R.drawable.enel));
        dataList.add(new DataModel("Franky(Iron Man) ", "The shipbuilder", R.drawable.franky));
        dataList.add(new DataModel("Nami(Cat Burglar)", "The navigator of the Straw Hat Pirates", R.drawable.nami));
        dataList.add(new DataModel("Jinbe(The Knight of the Sea)", "The cruise of the ships", R.drawable.jinbe));
        dataList.add(new DataModel("Usopp(Sogeking)", "The sniper of the Straw Hat Pirates", R.drawable.usopp));


        adapter = new CustomeAdapter(dataList);
        recyclerView.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void filter(String text) {
        ArrayList<DataModel> filteredList = new ArrayList<>();

        for (DataModel item : dataList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter = new CustomeAdapter(filteredList);
        recyclerView.setAdapter(adapter);
    }
}
