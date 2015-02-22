package com.example.sample.shared;

import java.io.Serializable;

public class ResultFields implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6998109621977333533L;
	
	private String carPros, carCons, carDist;
	private String transitPros, transitCons, transitDist;
	private String bikePros, bikeCons, bikeDist;
	private String walkPros, walkCons, walkDist;
	
	private String carCost, transitCost, bikeCost, walkCost;
	
	private String carTime, transitTime, bikeTime, walkTime;
	
	private String carCO2, transitCO2, bikeCO2, walkCO2;

	public String getCarPros() {
		return carPros;
	}

	public String getCarCons() {
		return carCons;
	}

	public String getTransitPros() {
		return transitPros;
	}

	public String getTransitCons() {
		return transitCons;
	}

	public String getBikePros() {
		return bikePros;
	}

	public String getBikeCons() {
		return bikeCons;
	}

	public String getWalkPros() {
		return walkPros;
	}

	public String getWalkCons() {
		return walkCons;
	}

	public String getCarCost() {
		return carCost;
	}

	public String getTransitCost() {
		return transitCost;
	}

	public String getBikeCost() {
		return bikeCost;
	}

	public String getWalkCost() {
		return walkCost;
	}

	public String getCarTime() {
		return carTime;
	}

	public String getBusTime() {
		return transitTime;
	}

	public String getBikeTime() {
		return bikeTime;
	}

	public String getWalkTime() {
		return walkTime;
	}

	public String getCarCO2() {
		return carCO2;
	}

	public String getBusCO2() {
		return transitCO2;
	}

	public String getBikeCO2() {
		return bikeCO2;
	}

	public String getWalkCO2() {
		return walkCO2;
	}

	public void setCarPros(String carPros) {
		this.carPros = carPros;
	}

	public void setCarCons(String carCons) {
		this.carCons = carCons;
	}

	public void setTransitPros(String transitPros) {
		this.transitPros = transitPros;
	}

	public void setTransitCons(String transitCons) {
		this.transitCons = transitCons;
	}

	public void setBikePros(String bikePros) {
		this.bikePros = bikePros;
	}

	public void setBikeCons(String bikeCons) {
		this.bikeCons = bikeCons;
	}

	public void setWalkPros(String walkPros) {
		this.walkPros = walkPros;
	}

	public void setWalkCons(String walkCons) {
		this.walkCons = walkCons;
	}

	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}

	public void setTransitCost(String transitCost) {
		this.transitCost = transitCost;
	}

	public void setBikeCost(String bikeCost) {
		this.bikeCost = bikeCost;
	}

	public void setWalkCost(String walkCost) {
		this.walkCost = walkCost;
	}

	public void setCarTime(String carTime) {
		this.carTime = carTime;
	}

	public void setBusTime(String busTime) {
		this.transitTime = busTime;
	}

	public void setBikeTime(String bikeTime) {
		this.bikeTime = bikeTime;
	}

	public void setWalkTime(String walkTime) {
		this.walkTime = walkTime;
	}

	public void setCarCO2(String carCO2) {
		this.carCO2 = carCO2;
	}

	public void setBusCO2(String busCO2) {
		this.transitCO2 = busCO2;
	}

	public void setBikeCO2(String bikeCO2) {
		this.bikeCO2 = bikeCO2;
	}

	public void setWalkCO2(String walkCO2) {
		this.walkCO2 = walkCO2;
	}

	public String getWalkDist() {
		return walkDist;
	}

	public void setWalkDist(String walkDist) {
		this.walkDist = walkDist;
	}

	public String getBikeDist() {
		return bikeDist;
	}

	public void setBikeDist(String bikeDist) {
		this.bikeDist = bikeDist;
	}

	public String getTransitDist() {
		return transitDist;
	}

	public void setTransitDist(String transitDist) {
		this.transitDist = transitDist;
	}

	public String getCarDist() {
		return carDist;
	}

	public void setCarDist(String carDist) {
		this.carDist = carDist;
	}

}
