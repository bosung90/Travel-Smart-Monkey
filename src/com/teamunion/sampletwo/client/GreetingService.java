package com.teamunion.sampletwo.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.teamunion.sampletwo.shared.ResultFields;
import com.teamunion.sampletwo.shared.SearchFields;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	ResultFields greetServer(SearchFields name) throws IllegalArgumentException;
}
