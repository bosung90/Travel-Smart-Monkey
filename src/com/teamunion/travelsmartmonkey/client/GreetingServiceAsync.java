package com.teamunion.travelsmartmonkey.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.teamunion.travelsmartmonkey.shared.ResultFields;
import com.teamunion.travelsmartmonkey.shared.SearchFields;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(SearchFields search, AsyncCallback<ResultFields> callback)
			throws IllegalArgumentException;
}
