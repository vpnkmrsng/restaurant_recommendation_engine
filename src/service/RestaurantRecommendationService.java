package service;

import entity.Cuisine;
import entity.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class RestaurantRecommendationService {

    public List<String> applyConditions(Restaurant[] availableRestaurants, List<Integer> primaryAndSecondaryCostBracket, List<Cuisine> primaryAndSecondaryCuisines) {
        Set<String> restaurantIds = new LinkedHashSet<>();
        /*
        Another approach to apply conditions is to maintain different list for each condition and iterate
        availableRestaurants just once populating all the list at one go (O(n)) and later sort them accordingly.
         */
        if(!primaryAndSecondaryCostBracket.isEmpty() && !primaryAndSecondaryCuisines.isEmpty()) {
            for (Restaurant restaurant : availableRestaurants) {
                if (restaurant.isRecommended() && restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(0)
                        && restaurant.getCuisine() == primaryAndSecondaryCuisines.get(0))
                    restaurantIds.add(restaurant.getRestaurantId());
            }
        }

        if(restaurantIds.isEmpty() && primaryAndSecondaryCostBracket.size()>=3 && primaryAndSecondaryCuisines.size()>=3){
            for(Restaurant restaurant : availableRestaurants){
                if(restaurant.isRecommended())
                    if ((restaurant.getCuisine() == primaryAndSecondaryCuisines.get(0)
                            && (restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(1)
                            || restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(2)))
                            || ((restaurant.getCuisine() == primaryAndSecondaryCuisines.get(1)
                            || restaurant.getCuisine() == primaryAndSecondaryCuisines.get(2))
                            && restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(0)))
                        restaurantIds.add(restaurant.getRestaurantId());
            }
        }
        if(!primaryAndSecondaryCostBracket.isEmpty() && !primaryAndSecondaryCuisines.isEmpty()) {
            for (Restaurant restaurant : availableRestaurants) {
                if (restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(0)
                        && restaurant.getCuisine() == primaryAndSecondaryCuisines.get(0)
                        && restaurant.getRating() >= 4)
                    restaurantIds.add(restaurant.getRestaurantId());
            }
        }

        if(primaryAndSecondaryCostBracket.size()>=3 && !primaryAndSecondaryCuisines.isEmpty()) {
            for (Restaurant restaurant : availableRestaurants) {
                if ((restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(1)
                        || restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(2))
                        && restaurant.getCuisine() == primaryAndSecondaryCuisines.get(0)
                        && restaurant.getRating() >= 4.5)
                    restaurantIds.add(restaurant.getRestaurantId());
            }
        }

        if(primaryAndSecondaryCuisines.size()>=3 && !primaryAndSecondaryCostBracket.isEmpty()) {
            for (Restaurant restaurant : availableRestaurants) {
                if ((restaurant.getCuisine() == primaryAndSecondaryCuisines.get(1)
                        || restaurant.getCuisine() == primaryAndSecondaryCuisines.get(2))
                        && restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(0)
                        && restaurant.getRating() >= 4.5)
                    restaurantIds.add(restaurant.getRestaurantId());
            }
        }

        List<Restaurant> newRestaurant = new ArrayList<>();
        LocalDate twoDaysAgo = LocalDateTime.now().minusHours(48).toLocalDate();
        for (Restaurant restaurant : availableRestaurants){
            if(restaurant.getOnboardedTime().isAfter(twoDaysAgo))
                newRestaurant.add(restaurant);
        }
        newRestaurant.sort((o1, o2) -> Float.compare(o2.getRating(), o1.getRating()));
        int counter =0;
        for(int i=0; i<newRestaurant.size() && counter<4; i++){
            if(!restaurantIds.contains(newRestaurant.get(i).getRestaurantId())) {
                restaurantIds.add(newRestaurant.get(i).getRestaurantId());
                counter++;
            }
        }

        if(!primaryAndSecondaryCostBracket.isEmpty() && !primaryAndSecondaryCuisines.isEmpty()) {
            for (Restaurant restaurant : availableRestaurants) {
                if (restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(0)
                        && restaurant.getCuisine() == primaryAndSecondaryCuisines.get(0)
                        && restaurant.getRating() < 4)
                    restaurantIds.add(restaurant.getRestaurantId());
            }
        }

        if(primaryAndSecondaryCostBracket.size()>=3 && !primaryAndSecondaryCuisines.isEmpty()) {
            for (Restaurant restaurant : availableRestaurants) {
                if ((restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(1)
                        || restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(2))
                        && restaurant.getCuisine() == primaryAndSecondaryCuisines.get(0)
                        && restaurant.getRating() < 4.5)
                    restaurantIds.add(restaurant.getRestaurantId());
            }
        }

        if(primaryAndSecondaryCuisines.size()>=3 && !primaryAndSecondaryCostBracket.isEmpty()) {
            for (Restaurant restaurant : availableRestaurants) {
                if ((restaurant.getCuisine() == primaryAndSecondaryCuisines.get(1)
                        || restaurant.getCuisine() == primaryAndSecondaryCuisines.get(2))
                        && restaurant.getCostBracket() == primaryAndSecondaryCostBracket.get(0)
                        && restaurant.getRating() < 4.5)
                    restaurantIds.add(restaurant.getRestaurantId());
            }
        }

        List<String> restaurant_ids = new ArrayList<>(restaurantIds);
        return restaurant_ids.stream().limit(100).collect(Collectors.toList());
    }
}
