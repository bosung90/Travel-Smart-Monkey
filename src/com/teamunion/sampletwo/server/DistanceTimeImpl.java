package com.teamunion.sampletwo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class DistanceTimeImpl {

    public static String[] GetDistandTime(String origin, String dest, String mode){
        
        StringBuffer response = new StringBuffer();
		try {
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
 
			while ((inputLine = in.readLine()) != null) {
			    response.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			return parseJson(response.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    private static String[] parseJson(String s) throws JSONException {
                
    	JSONObject jobject = new JSONObject(s);
    	JSONArray _row = jobject.getJSONArray("rows");
        jobject = _row.getJSONObject(0);
        JSONArray _ele = jobject.getJSONArray("elements");
        jobject = _ele.getJSONObject(0);
        JSONObject distance = jobject.getJSONObject("distance");
        String _dist = distance.getString("value");
        JSONObject duration = jobject.getJSONObject("duration");
        String _time = duration.getString("value");
        
        System.out.println("Distance : " + _dist + " Time : " + _time );
        String[] result = {_dist, _time};
//    	String[] result = {"10", "10"};
        return result;
        
    }

}
