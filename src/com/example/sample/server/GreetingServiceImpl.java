package com.example.sample.server;

import com.example.sample.client.GreetingService;
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
		
		System.out.println("running");
		
		CarParser.parseFuelConsumption("data/Fuel2001.csv");
		
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
