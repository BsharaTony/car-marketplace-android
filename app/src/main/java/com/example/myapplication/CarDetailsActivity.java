package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
public class CarDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String phone = getIntent().getStringExtra("phone");
        setContentView(R.layout.activity_car_details);
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
        Button contactButton = findViewById(R.id.contactButton);
        contactButton.setOnClickListener(v -> {
            if (phone == null || phone.isEmpty()) return;

            // Open phone dialer with seller number (no call permission required)
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + phone));
            startActivity(dialIntent);
        });
        TextView titleText = findViewById(R.id.detailsTitleText);
        TextView priceText = findViewById(R.id.detailsPriceText);

        // Retrieve car data sent from MainActivity
        String title = getIntent().getStringExtra("title");
        int price = getIntent().getIntExtra("price", -1);

        titleText.setText(title);
        priceText.setText("₪" + price);
    }
}