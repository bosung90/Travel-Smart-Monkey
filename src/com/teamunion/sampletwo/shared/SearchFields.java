package com.teamunion.sampletwo.shared;

import java.io.Serializable;

public class SearchFields implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2936848290896175168L;
	
	private String carType;
	private String startingPoint;
	private String destination;

	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getStartingPoint() {
		return startingPoint;
	}
	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

}
