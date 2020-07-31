package com.divya.pojo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class City {
	
	private String cname;
	private Set<City> adjacentCity=new HashSet<>();
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<City> getAdjacentCity() {
		return adjacentCity;
	}
	public City addAdjacentCity(City city) {
		adjacentCity.add(city);
		return this;
	}
	public City(String cname) {
		Objects.requireNonNull(cname);
		this.cname = cname.trim().toUpperCase();
	}
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 public static City build(String name) {
	        return new City(name);
	    }

	
	 
	
}
