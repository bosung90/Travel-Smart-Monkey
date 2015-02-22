package com.example.sample.server;

import java.util.Hashtable;

import com.example.sample.client.GreetingService;
import com.example.sample.shared.CarData;
import com.example.sample.shared.ResultFields;
import com.example.sample.shared.SearchFields;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	@Override
	public ResultFields greetServer(SearchFields search) {
		
		Hashtable<String, CarData> d = new Hashtable<String, CarData>();
		
		Hashtable<String, CarData> a = CarParser.parseFuelConsumption("data/MY1995-1999.csv");
		d.putAll(a);
		for(int i=2000; i<=2015; i++)
		{
			Hashtable<String, CarData> b = CarParser.parseFuelConsumption("data/MY"+i+".csv");
			d.putAll(b);
		}
		
		String carModel = search.getCarType();
		//find carModel in one of the CarData.
		
		CarData car = d.get(carModel);
		
		
		
		return null;
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
