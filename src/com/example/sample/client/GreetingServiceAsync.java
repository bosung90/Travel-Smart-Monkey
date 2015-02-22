package com.example.sample.client;

import com.example.sample.shared.ResultFields;
import com.example.sample.shared.SearchFields;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(SearchFields search, AsyncCallback<ResultFields> callback)
			throws IllegalArgumentException;
}
