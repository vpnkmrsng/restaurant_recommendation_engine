package entity;

import java.time.LocalDate;

public class Restaurant {
    private String restaurantId;
    private Cuisine cuisine;
    private int costBracket;
    private float rating;
    private boolean isRecommended;
    private LocalDate onboardedTime;

    public Restaurant(String restaurantId, Cuisine cuisine, int costBracket, float rating, boolean isRecommended, LocalDate onboardedTime) {
        this.restaurantId = restaurantId;
        this.cuisine = cuisine;
        this.costBracket = costBracket;
        this.rating = rating;
        this.isRecommended = isRecommended;
        this.onboardedTime = onboardedTime;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public int getCostBracket() {
        return costBracket;
    }

    public void setCostBracket(int costBracket) {
        this.costBracket = costBracket;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }

    public LocalDate getOnboardedTime() {
        return onboardedTime;
    }

    public void setOnboardedTime(LocalDate onboardedTime) {
        this.onboardedTime = onboardedTime;
    }
}
