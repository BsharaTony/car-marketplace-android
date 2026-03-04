package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private void sortByPrice(List<Car> list, boolean ascending) {
        Collections.sort(list, (a, b) -> ascending
                ? Integer.compare(a.getPrice(), b.getPrice())
                : Integer.compare(b.getPrice(), a.getPrice()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.carsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EditText searchEditText = findViewById(R.id.searchEditText);
        Button sortButton = findViewById(R.id.sortButton);

        // Master list (all cars)
        ArrayList<Car> allCars = new ArrayList<>();
        allCars.add(new Car("Toyota Corolla 2018", 91000));
        allCars.add(new Car("Mazda 3 2016", 43000));
        allCars.add(new Car("Hyundai i10 2020", 50000));
        allCars.add(new Car("Kia Sportage 2017", 72000));
        allCars.add(new Car("Honda Civic 2015", 40000));

        // Display list (filtered/sorted)
        ArrayList<Car> shownCars = new ArrayList<>(allCars);

        CarAdapter adapter = new CarAdapter(shownCars);
        recyclerView.setAdapter(adapter);

        // Sort toggle: true = ascending, false = descending
        final boolean[] sortAscending = {true};

        sortButton.setOnClickListener(v -> {
            sortAscending[0] = !sortAscending[0];
            sortButton.setText(sortAscending[0] ? "Sort ↑" : "Sort ↓");

            sortByPrice(shownCars, sortAscending[0]);
            adapter.notifyDataSetChanged();
        });

        // Search filter
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().trim().toLowerCase();

                shownCars.clear();
                for (Car car : allCars) {
                    if (car.getTitle().toLowerCase().contains(query)) {
                        shownCars.add(car);
                    }
                }

                // Keep current sort after filtering
                sortByPrice(shownCars, sortAscending[0]);
                adapter.notifyDataSetChanged();
            }
        });
    }
}