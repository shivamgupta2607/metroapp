package com.practice.metroapp.service;

import com.practice.metroapp.model.Route;
import com.practice.metroapp.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author shivamgupta
 * Created on 1/9/19
 */
@Component
public class RouteService {

    @Autowired
    RouteRepository routeRepository;

    public static List<Route> routes = new ArrayList<>();

    @PostConstruct
    private void loadRoutesData() {
        Iterator<Route> routeIterator = routeRepository.findAll().iterator();
        while (routeIterator.hasNext())
        {
            this.routes.add(routeIterator.next());
        }
    }


}
