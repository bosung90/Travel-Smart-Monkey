package com.teamunion.travelsmartmonkey.server;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.teamunion.travelsmartmonkey.shared.CarData;

public class CarParser {
	
	public static ArrayList<CarData> cars = new ArrayList<CarData>();
	
	public static ArrayList<CarData> parseFuelConsumption(String filepath){
		try {
			Scanner scanner = new Scanner(new FileReader(filepath));
			String line;
			CarData data;
			scanner.nextLine();
			scanner.nextLine();
			while (scanner.hasNextLine()){
				line = scanner.nextLine(); // get the line
				String[] results = line.split(","); // split it on ,
				if(results[0].trim().isEmpty()){
					scanner.close();
					return cars;					
				}
				double year = Double.parseDouble(results[0]);
				String make = results[1];
				String model = results[2];
				double fuelConsumption = Double.parseDouble(results[10]);
				double co2Emission = Double.parseDouble(results[12]);	
				
				System.out.println(year + " " + make + " "+ model + " " + fuelConsumption + " " + co2Emission) ;

				
				// create the car data
				data = new CarData(year,make,model,fuelConsumption,co2Emission);
				cars.add(data);
			}
			scanner.close();
		}
		catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return cars;
	}

}
