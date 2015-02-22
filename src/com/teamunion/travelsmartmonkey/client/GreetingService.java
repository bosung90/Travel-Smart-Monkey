package com.teamunion.travelsmartmonkey.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.teamunion.travelsmartmonkey.shared.ResultFields;
import com.teamunion.travelsmartmonkey.shared.SearchFields;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	ResultFields greetServer(SearchFields search) throws IllegalArgumentException;
}
