package com.example.sample.client;

import com.example.sample.shared.ResultFields;
import com.example.sample.shared.SearchFields;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	ResultFields greetServer(SearchFields search) throws IllegalArgumentException;
}
