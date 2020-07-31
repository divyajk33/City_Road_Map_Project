package com.divya.service;


import java.util.ArrayDeque;


import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;


import java.util.Set;


import org.springframework.stereotype.Service;

import com.divya.pojo.City;
@Service
public class RoadServiceImpl implements RoadService {

	
	public boolean isConnected(City origin,City destination) {
		if (origin.equals(destination)) return true;

        if (origin.getAdjacentCity().contains(destination)) return true;

        /*
         * The origin city was already commuted since we have started from it
         */
        Set<City> commuted = new HashSet<>(Collections.singleton(origin));

        /*
         * Put all the adjacent cities into cities to vist list
         */
        Deque<City> cityToVisit = new ArrayDeque<>(origin.getAdjacentCity());


        while (!cityToVisit.isEmpty()) {


            City city = cityToVisit.getLast();

            if (city.equals(destination)) return true;

            // remove the city from the cities to visit list

            // first time visit?
            if (!commuted.contains(city)) {

                commuted.add(city);

                // add adjacent cities to the cities to visit list and
                // remove already commuted cities from the list
                cityToVisit.addAll(city.getAdjacentCity());
                cityToVisit.removeAll(commuted);

               
            } else {
                // the city has been commuted, so remove it from the cities to visit list
            	cityToVisit.removeAll(Collections.singleton(city));
            }
        }

        return false;
	}

}
