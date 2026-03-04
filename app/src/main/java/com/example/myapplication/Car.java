package com.example.myapplication;

public class Car {
    private final String title;
    private final int price;
    private boolean favorite;

    public Car(String title, int price) {
        this.title = title;
        this.price = price;
        this.favorite = false;
    }

    public String getTitle() { return title; }
    public int getPrice() { return price; }

    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public void toggleFavorite() { this.favorite = !this.favorite; }
}