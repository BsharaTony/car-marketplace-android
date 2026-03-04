package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.content.Intent;
import android.widget.ImageButton;

// Adapter that binds a list of Car objects to the RecyclerView
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private final List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);

        holder.titleText.setText(car.getTitle());
        holder.priceText.setText("₪" + car.getPrice());

        // Set star icon based on favorite state
        holder.favoriteButton.setImageResource(
                car.isFavorite() ? android.R.drawable.btn_star_big_on
                        : android.R.drawable.btn_star_big_off
        );

        // Toggle favorite without opening details
        holder.favoriteButton.setOnClickListener(v -> {
            car.toggleFavorite();
            notifyItemChanged(holder.getAdapterPosition());
        });

        // Open details page on row click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CarDetailsActivity.class);
            intent.putExtra("title", car.getTitle());
            intent.putExtra("price", car.getPrice());
            intent.putExtra("phone", "0501234567");
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

        static class CarViewHolder extends RecyclerView.ViewHolder {

            TextView titleText;
            TextView priceText;
            ImageButton favoriteButton;

            public CarViewHolder(@NonNull View itemView) {
                super(itemView);
                titleText = itemView.findViewById(R.id.carTitleText);
                priceText = itemView.findViewById(R.id.carPriceText);
                favoriteButton = itemView.findViewById(R.id.favoriteButton);
            }
        }
}