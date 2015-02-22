package com.teamunion.sampletwo.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.teamunion.sampletwo.shared.ResultFields;
import com.teamunion.sampletwo.shared.SearchFields;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(SearchFields input, AsyncCallback<ResultFields> asyncCallback)
			throws IllegalArgumentException;
}
