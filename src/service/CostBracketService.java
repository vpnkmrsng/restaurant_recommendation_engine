package service;

import entity.CostTracking;
import entity.Cuisine;
import entity.CuisineTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CostBracketService {

    public List<Integer> getPrimaryAndSecondaryCostBracket(CostTracking[] costTrackings){
//        if(costTrackings.length<3)
//            throw new Exception("cost tracking not enough to calculate primary and secondary cost brackets");
        List<Integer> costBrackets = new ArrayList<>();
        Arrays.sort(costTrackings, (o1, o2) -> o2.getNoOfOrders() - o1.getNoOfOrders());
        for(int i=0 ; i<3 ;i++){
            costBrackets.add(costTrackings[i].getType());
        }
        return costBrackets;
    }
}
