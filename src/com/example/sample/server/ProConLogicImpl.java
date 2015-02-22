package com.example.sample.server;

import java.util.Arrays;

import com.example.sample.shared.ResultFields;

public class ProConLogicImpl {
	
	private final static String time1 = "Fastest method of transportation\n";
	private final static String time2 = "Fast method of transportation\n";
	private final static String time3 = "Slow method of trnasportation\n";
	private final static String time4 = "Slowest method of transportation\n";
	
	private final static String Carbon1 = "Earth Saver, Friends of cute bunnies\n";
	private final static String Carbon2 = "Satisfying Carbon Emission\n";
	private final static String Carbon3 = "Earth Terminator - try to avoid this transportation\n";
	
//	private final static String Walking1 = "Pumping up those leg muscles heh?\n";
//	private final static String Walking2 = "Good excercise for legs\n";
	
	private final static String ShortestDistance = "This is the shortest Distance you can get! \n";
	private final static String ShortDistance = "Hey this is not bad choice of transportation \n";
	private final static String LongDistance = "There are better methods of transportation \n";
	private final static String LongestDistance = "Do you really want this method of transportation? \n";
	
	private final static String Price1 = "Very Expensive way of transportation\n";
	private final static String Price2 = "If you do not choose this method of transportation you can save $";
	private final static String Price3 = "If you choose this method, you are very economic person!\n";
	
	private final static String none = "None";
	
	public static ResultFields ProConLogicSys(ResultFields result) {
		
		
		//checking for price
		double price = Double.parseDouble(result.getCarCost());
		
		if(price >= 10){
			result.setCarPros(Price1);
		}else if(price < 10 && price > 5 ){
			result.setCarPros(Price2 + price + "\n");
		}else if(price <= 5)
			result.setCarPros(Price3);
		
		//checking for carbon emission
		
		double emission = Double.parseDouble(result.getCarCO2());
		
		if(emission > 3000)
			result.setCarPros(result.getCarPros() + " " + Carbon3);
		else if(emission <= 3000 && emission > 1000)
			result.setCarPros(result.getCarPros() + " " + Carbon2);
		else if(emission <= 1000)
			result.setCarPros(result.getCarPros() + " " + Carbon1);
		
		// distance logic
		Double[] Dist = {Double.parseDouble(result.getWalkDist()), Double.parseDouble(result.getBikeDist()), Double.parseDouble(result.getTransitDist()),
				Double.parseDouble(result.getCarDist())};
		
		Arrays.sort(Dist);
		
		for(int i = 0; i < Dist.length; i ++ ) {
			if(Dist[i] == Double.parseDouble(result.getWalkDist())){
				result.setWalkPros(result.getWalkPros() + " " + getDistComment(i));
			}else if(Dist[i] == Double.parseDouble(result.getBikeDist())){
				result.setBikePros(result.getBikePros() + " " +getDistComment(i));
			}else if(Dist[i] == Double.parseDouble(result.getTransitDist())){
				result.setTransitPros(result.getTransitPros() + " " +getDistComment(i));
			}else if(Dist[i] == Double.parseDouble(result.getCarDist())){
				result.setCarPros(result.getCarPros() + " " +getDistComment(i));
			}
		}
		
		Double[] Time = {Double.parseDouble(result.getWalkTime()), Double.parseDouble(result.getBikeTime()), Double.parseDouble(result.getBusTime()),
				Double.parseDouble(result.getCarTime())};
		
		Arrays.sort(Time);
		
		for(int i = 0; i < Time.length; i ++ ) {
			if(Time[i] == Double.parseDouble(result.getWalkTime())){
				result.setWalkPros(result.getWalkPros() + " " + getTimeComment(i));
			}else if(Time[i] == Double.parseDouble(result.getBikeTime())){
				result.setBikePros(result.getBikePros() + " " +getTimeComment(i));
			}else if(Time[i] == Double.parseDouble(result.getBusTime())){
				result.setTransitPros(result.getTransitPros() + " " +getTimeComment(i));
			}else if(Time[i] == Double.parseDouble(result.getCarTime())){
				result.setCarPros(result.getCarPros() + " " +getTimeComment(i));
			}
		}
		
		return result;
		
		
	}
	
	private static String getDistComment(int s) {
		if(s == 0)
			return ShortestDistance;
		else if(s == 1)
			return ShortDistance;
		else if(s == 2)
			return LongDistance;
		else if(s == 3)
			return LongestDistance;
		else
			return none;
			
	}
	
	private static String getTimeComment(int s) {
		if(s == 0)
			return time1;
		else if(s == 1)
			return time2;
		else if(s == 2)
			return time3;
		else if(s == 3)
			return time4;
		else
			return none;
			
	}

}
