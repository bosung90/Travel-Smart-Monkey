package com.example.sample.shared;

import java.io.Serializable;

public class CarData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4628605114900050025L;
	double year;
	String make;
	String model;
	double fuelConsumpmtion;
	double co2Emission;
	
	public CarData(double year, String make, String model, double fuelConsumption, double co2Emission){
		this.year = year;
		this.make = make;
		this.model = model;
		this.fuelConsumpmtion = fuelConsumption;
		this.co2Emission = co2Emission;
	}

	public double getYear() {
		return year;
	}

	public String getMake() {
		return make;
	}


	public String getModel() {
		return model;
	}

	public double getFuelConsumpmtion() {
		return fuelConsumpmtion;
	}

	public double getCo2Emission() {
		return co2Emission;
	}

}
