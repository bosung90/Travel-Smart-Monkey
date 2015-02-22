package com.teamunion.travelsmartmonkey.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;




public class DistanceTimeImpl {
	

	public String[] GetDistandTime(String origin, String dest, String mode) throws Exception {
		
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+ origin + 
				"&destinations=" + dest + "&mode=" + mode + "&language=fr-EN";
		final String key = "AIzaSyCISEYz0RnyfORigxu4y_5jRQIWJbJWJIU";
		//Test case
		if(mode.equals("transit"))
			url = url +"&key=" + key;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", "Chrome 41.0.2228.0");
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		//System.out.println(response.toString());
		return parseJson(response.toString());
 
	}
	
	private String[] parseJson(String s) {
				
		JsonElement jelement = new JsonParser().parse(s);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonArray _row = jobject.getAsJsonArray("rows");
		jobject = _row.get(0).getAsJsonObject();
		JsonArray _ele = jobject.getAsJsonArray("elements");
		jobject = _ele.get(0).getAsJsonObject();
		JsonObject distance = jobject.getAsJsonObject("distance");
		String _dist = distance.get("text").getAsString();
		JsonObject duration = jobject.getAsJsonObject("duration");
		String _time = duration.get("text").getAsString();
		
		System.out.println("Distance : " + _dist + " Time : " + _time );
		String[] result = {_dist, _time};
		return result;
		
	}

}
