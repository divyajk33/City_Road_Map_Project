package com.divya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.divya.pojo.City;
import com.divya.pojo.CsvFileReaderImpl;

import com.divya.service.RoadServiceImpl;

@RestController
public class RoadController {
	@Autowired
	private CsvFileReaderImpl road;
	@Autowired
	RoadServiceImpl service;
	//http://localhost:4343/connected?origin=Boston&destination=Newark
	//Should return yes
	//http://localhost:4343/connected?origin=Boston&destination=Philadelphia
	//Should return yes
	//http://localhost:4343/connected?origin=Philadelphia&destination=Albany
		//Should return no
	
	@GetMapping(value="/connected",produces = "text/plain")
public String roadExist(@RequestParam("origin")String orgin,@RequestParam("destination")String dest)  {
	
	String msg="";
	
	
	City originCity = road.getName(orgin.toUpperCase());
    City destCity = road.getName(dest.toUpperCase());
		  
		  
	      
		msg=String.valueOf(service.isConnected(originCity, destCity));
		
	
	return msg;
	}
	
	
	 @ExceptionHandler(NullPointerException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public String cityError() {
	        return "Either destination or origin city does not exist or invalid";
	    }
	
}

