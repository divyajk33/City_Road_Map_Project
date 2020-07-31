package com.divya;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.divya.pojo.City;
import com.divya.pojo.CsvFileReaderImpl;
import com.divya.service.RoadServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class CitiesRoadsApplicationTests {

	 @Autowired
	    CsvFileReaderImpl road;
@Autowired
RoadServiceImpl service;
	    @Autowired
	    private TestRestTemplate restTemplate;

	    @Test
	    public void readFile() {
	        Assert.assertFalse("File load failed", road.getRoadCity().isEmpty());
	    }

	    @Test
	    public void issameCity() {
	        City city = City.build("Fremont");
	        Assert.assertTrue(service.isConnected(city, city));
	    }

	    @Test
	    public void adjacentCities() {
	        City city1 = road.getName("Boston".toUpperCase());
	        City city2 = road.getName("Newark".toUpperCase());

	        Assert.assertNotNull("Invalid test data. City not found: Boston", city1);
	        Assert.assertNotNull("Invalid test data. City not found: Newark", city2);

	        Assert.assertTrue(service.isConnected(city1, city2));
	    }

	    @Test
	    public void isRoute() {
	        City city1 = road.getName("Boston".toUpperCase());
	        City city2 = road.getName("Philadelphia".toUpperCase());

	        Assert.assertNotNull("Invalid test data. City not found: Boston", city1);
	        Assert.assertNotNull("Invalid test data. City not found: Philadelphia", city2);

	        Assert.assertTrue(service.isConnected(city1, city2));
	    }

	    @Test
	    public void routeMap() {

	        Map<String, String> cities = new HashMap<>();
	        cities.put("origin", "Boston");
	        cities.put("destination", "Newark");

	        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, cities);
	        Assert.assertEquals("true", body);
	    }

	    @Test
	    public void noRoute() {

	        Map<String, String> cities = new HashMap<>();
	        cities.put("origin", "Philadelphia");
	        cities.put("destination", "Albany");

	        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, cities);
	        Assert.assertEquals("false", body);
	    }

	    @Test
	    public void requestNotFound() {
	        
	        ResponseEntity<String> response = restTemplate.exchange("/connected?origin=none&destination=none", HttpMethod.GET, HttpEntity.EMPTY, String.class);
	        Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	    }


}
