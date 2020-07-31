package com.divya;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.divya.pojo.City;



public class CityRoadTest {
	@Test
	public void cityBuild() {
		City city=City.build("SanJose");
		Assert.assertEquals("SANJOSE",city.getCname());
	}
	@Test
	public void addNeighboursCheck() {
		City city = City.build("Milpitas");
        city.addAdjacentCity(City.build("SanJose"))
                .addAdjacentCity(City.build("Fremont"));

        Set<City> nearby = city.getAdjacentCity();
        Assert.assertEquals(2, nearby.size());
        Assert.assertTrue(nearby.contains(City.build("SanJose")));
		
	}
	
	@Test
    public void adjacentRepeats() {
        City city = City.build("SanJose");
        city.addAdjacentCity((City.build("Fremont"))
                .addAdjacentCity(City.build("FREMONT")));

        Assert.assertEquals(1, city.getAdjacentCity().size());
    }

}
