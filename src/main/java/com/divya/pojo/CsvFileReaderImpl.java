package com.divya.pojo;

import java.io.FileReader;
import java.util.HashMap;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;


import com.opencsv.CSVReader;

@Component
public class CsvFileReaderImpl implements CSVFileReader {
	
	Map<String,City>roadCity=new HashMap<>();
	public Map<String, City> getRoadCity() {
		return roadCity;
	}
	@SuppressWarnings("resource")
	@Override
	@PostConstruct
	public void readFile() throws Exception {
		// TODO Auto-generated method stub
		
		CSVReader reader=null;
		String[] items=null;
		//create CsvReader object
		reader=new CSVReader(new FileReader("C://Users/jmuralee/Downloads/java_works/coding/Cities_Roads/src/main/resources/static/city.txt"));
		
		while((items=reader.readNext())!=null) {
			
			String city1 = items[0].toUpperCase();
            String city2 = items[1].toUpperCase();
            if(!city1.equals(city2)) {
	   			City cty1=roadCity.getOrDefault(city1, City.build(city1));
	   			City cty2=roadCity.getOrDefault(city2, City.build(city2));
	   			cty1.addAdjacentCity(cty2);
	   			cty2.addAdjacentCity(cty1);
              roadCity.put(cty1.getCname(),cty1);	
           roadCity.put(cty2.getCname(),cty2);	
           }
		}
		if(roadCity !=null) {
			System.out.println("cities added");
		}
		 System.out.println(roadCity.values());	
	}

	public City getName(String name) {
		return roadCity.get(name);
	}
	
	
	
	
}


