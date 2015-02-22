package com.teamunion.sampletwo.server;

import java.util.HashMap;

import com.teamunion.sampletwo.client.GreetingService;
import com.teamunion.sampletwo.shared.CarData;
import com.teamunion.sampletwo.shared.ResultFields;
import com.teamunion.sampletwo.shared.SearchFields;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public ResultFields greetServer(SearchFields search) throws IllegalArgumentException {
		HashMap<String, CarData> d = new HashMap<String, CarData>();
		
		HashMap<String, CarData> a = CarParser.parseFuelConsumption("data/MY1995-1999.csv");
		d.putAll(a);
		for(int i=2000; i<=2015; i++)
		{
			HashMap<String, CarData> b = CarParser.parseFuelConsumption("data/MY"+i+".csv");
			d.putAll(b);
		}
		
		String carModel = search.getCarType();
		//find carModel in one of the CarData.
		
		CarData car = d.get(carModel);
		
		ResultFields result = new ResultFields();
		
		String[] disTime = DistanceTimeImpl.GetDistandTime(search.getStartingPoint(), search.getDestination(), "driving");
		int carDistMeter = Integer.parseInt(disTime[0]);
		int carTimeSeconds = Integer.parseInt(disTime[1]);
		
		disTime = DistanceTimeImpl.GetDistandTime(search.getStartingPoint(), search.getDestination(), "transit");
		int transitDistMeter = Integer.parseInt(disTime[0]);
		int transitTimeSeconds = Integer.parseInt(disTime[1]);
		
		disTime = DistanceTimeImpl.GetDistandTime(search.getStartingPoint(), search.getDestination(), "bicycling");
		int bikeDistMeter = Integer.parseInt(disTime[0]);
		int bikeTimeSeconds = Integer.parseInt(disTime[1]);
		
		disTime = DistanceTimeImpl.GetDistandTime(search.getStartingPoint(), search.getDestination(), "walking");
		int walkDistMeter = Integer.parseInt(disTime[0]);
		int walkTimeSeconds = Integer.parseInt(disTime[1]);
		
		String carCO2 = "" + (int)(car.getCo2Emission() * carDistMeter * 1000);
		double gasPricePerLiter = 1.2;
		String carCost = "" + (int)(car.getFuelConsumpmtion() * carDistMeter * 100000 * gasPricePerLiter);
		
		result.setCarCO2(carCO2);
		result.setCarCost(carCost);
		result.setCarTime("" + carTimeSeconds/60);
		
		String busCO2 = "" + (int)(50 * transitDistMeter * 1000);
		String transitCost = "2.75";
		
		result.setBusCO2(busCO2);
		result.setTransitCost(transitCost);
		result.setBusTime("" + transitTimeSeconds/60);
		
		result.setBikeCO2("0");
		result.setBikeCost("0");
		result.setBikeTime("" + bikeTimeSeconds/60);
		
		result.setWalkCO2("0");
		result.setWalkCost("0");
		result.setWalkTime("" + bikeTimeSeconds/60);
		
//		result = ProConLogicImpl.ProConLogicSys(result);
		return result;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
