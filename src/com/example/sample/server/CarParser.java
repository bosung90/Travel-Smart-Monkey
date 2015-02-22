package com.example.sample.server;

import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;

import com.example.sample.shared.CarData;

public class CarParser {
	
	public static Hashtable<String, CarData> cars = new Hashtable<String, CarData>();
	
	public static Hashtable<String, CarData> parseFuelConsumption(String filepath){
		try {
			Scanner scanner = new Scanner(new FileReader(filepath));
			String line;
			CarData data;
			scanner.nextLine();
			scanner.nextLine();
			while (scanner.hasNextLine()){
				line = scanner.nextLine(); // get the line
				String[] results = line.split(","); // split it on ,
				if(results.length < 13){
					scanner.close();
					return cars;					
				}
				double year = Double.parseDouble(results[0]);
				String make = results[1];
				String model = results[2];
				double fuelConsumption = Double.parseDouble(results[10]);
				double co2Emission = Double.parseDouble(results[12]);	
				
//				System.out.println(year + " " + make + " "+ model + " " + fuelConsumption + " " + co2Emission) ;

				
				// create the car data
				data = new CarData(year,make,model,fuelConsumption,co2Emission);
				String key = year +" "+ make +" "+ model;
				cars.put(key, data);
			}
			scanner.close();
		}
		catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return cars;
	}

}
