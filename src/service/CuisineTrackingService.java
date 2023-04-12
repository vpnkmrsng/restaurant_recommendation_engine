package service;

import entity.Cuisine;
import entity.CuisineTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CuisineTrackingService {

    public List<Cuisine> getPrimaryAndSecondaryCuisines(CuisineTracking[] cuisineTrackings){
//        if(cuisineTrackings.length<3)
//            throw new Exception("cuisine tracking not enough to calculate primary and secondary cuisine brackets");
        List<Cuisine> cuisines = new ArrayList<>();
        Arrays.sort(cuisineTrackings, (o1, o2) -> o2.getNoOfOrders() - o1.getNoOfOrders());
        for(int i=0 ; i<3 ;i++){
            cuisines.add(cuisineTrackings[i].getType());
        }
        return cuisines;
    }
}
