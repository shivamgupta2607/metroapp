package com.practice.metroapp.util;

import com.practice.metroapp.model.Journey;
import com.practice.metroapp.model.Route;
import com.practice.metroapp.service.RouteService;

/**
 * @author shivamgupta
 * Created on 2/9/19
 */
public class Util {
    public static double getJourneyAmount(Journey journey) {
        double routeAmount = 0;
        for (Route route : RouteService.routes) {
            if (route.getSrc().getId() == journey.getEntryStation().getId() && route.getDestination().getId() == journey.getExitStation().getId()) {
                routeAmount = route.getAmount();
                break;
            }
        }
        return routeAmount;
    }
}
