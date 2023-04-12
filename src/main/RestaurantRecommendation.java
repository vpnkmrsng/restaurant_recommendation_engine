package main;

import entity.*;
import service.CostBracketService;
import service.CuisineTrackingService;
import service.RestaurantRecommendationService;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class RestaurantRecommendation {
    public static void main(String[] args){
        CostTracking costTracking1 = new CostTracking(5, 7);
        CuisineTracking cuisineTracking1 = new CuisineTracking(Cuisine.NORTH_INDIAN, 15);

        CostTracking costTracking2 = new CostTracking(4, 5);
        CuisineTracking cuisineTracking2 = new CuisineTracking(Cuisine.SOUTH_INDIAN, 5);

        CostTracking costTracking3 = new CostTracking(3, 7);
        CuisineTracking cuisineTracking3 = new CuisineTracking(Cuisine.CHINESE, 10);
        CuisineTracking[] cuisines = {cuisineTracking1, cuisineTracking2, cuisineTracking3};
        CostTracking[] costBrackets = {costTracking1, costTracking2, costTracking3};
        User user = new User(cuisines, costBrackets);

        Restaurant r1 = new Restaurant("1", Cuisine.NORTH_INDIAN,5, 4.5f, true, LocalDate.of(2023, Month.APRIL, 8));
        Restaurant r2 = new Restaurant("2", Cuisine.SOUTH_INDIAN,5, 5f, true, LocalDate.of(2023, Month.APRIL, 9));
        Restaurant r3 = new Restaurant("3", Cuisine.NORTH_INDIAN,5, 3.5f, true, LocalDate.of(2023, Month.APRIL, 5));
        Restaurant r4 = new Restaurant("4", Cuisine.NORTH_INDIAN,5, 4.5f, false, LocalDate.of(2023, Month.APRIL, 11));
        Restaurant r5 = new Restaurant("5", Cuisine.CHINESE,5, 4f, true, LocalDate.of(2023, Month.APRIL, 12));

        Restaurant[] restaurants = {r1, r2, r3, r4, r5};
        RestaurantRecommendation restaurantRecommendation = new RestaurantRecommendation();
        List<String> restaurantIds = restaurantRecommendation.getRestaurantRecommendation(user, restaurants);
        System.out.println(restaurantIds);
    }

    public List<String> getRestaurantRecommendation(User user, Restaurant[] availableRestaurants){
        List<Cuisine> primaryAndSecondaryCuisines = new CuisineTrackingService().getPrimaryAndSecondaryCuisines(user.getCuisines());
        List<Integer> primaryAndSecondaryCostBracket = new CostBracketService().getPrimaryAndSecondaryCostBracket(user.getCostBracket());

        return new RestaurantRecommendationService().applyConditions(availableRestaurants, primaryAndSecondaryCostBracket, primaryAndSecondaryCuisines);
    }

}
