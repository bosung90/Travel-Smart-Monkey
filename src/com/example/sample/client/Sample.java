package com.example.sample.client;

import com.example.sample.shared.ResultFields;
import com.example.sample.shared.SearchFields;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.Position.Coordinates;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sample implements EntryPoint {
	public AsyncCallback<ResultFields> travelSmartService;
	
	
	//================Widgets===================
	private final TextBox _carModel = TextBox.wrap(DOM.getElementById("carModel"));
	private final TextBox _startingPoint = TextBox.wrap(DOM.getElementById("startingPoint"));
	private final TextBox _destination = TextBox.wrap(DOM.getElementById("destination"));
	private final Button _searchButton = Button.wrap(DOM.getElementById("searchBtn"));
	//==========================================
	
	private Geolocation _geoposition; 

	private double _latitude;
	private double _longitude;
	private double _accuracy;
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad(){
		RefreshCurrentGPSLocation();
		_searchButton.addClickHandler(new SearchButtonClickHandler());
		
		
		//		final Button sendButton = new Button("Send");
		//		final TextBox nameField = new TextBox();
		//		nameField.setText("GWT User");
		//		final Label errorLabel = new Label();

		// We can add style names to widgets
		//		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		//		RootPanel.get("nameFieldContainer").add(nameField);
		//		RootPanel.get("sendButtonContainer").add(sendButton);
		//		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		//		nameField.setFocus(true);
		//		nameField.selectAll();

		// Create the popup dialog box
		//		final DialogBox dialogBox = new DialogBox();
		//		dialogBox.setText("Remote Procedure Call");
		//		dialogBox.setAnimationEnabled(true);
		//		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		//		closeButton.getElement().setId("closeButton");
		//		final Label textToServerLabel = new Label();
		//		final HTML serverResponseLabel = new HTML();
		//		VerticalPanel dialogVPanel = new VerticalPanel();
		//		dialogVPanel.addStyleName("dialogVPanel");
		//		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		//		dialogVPanel.add(textToServerLabel);
		//		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		//		dialogVPanel.add(serverResponseLabel);
		//		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		//		dialogVPanel.add(closeButton);
		//		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		//		closeButton.addClickHandler(new ClickHandler() {
		//			public void onClick(ClickEvent event) {
		//				dialogBox.hide();
		//				sendButton.setEnabled(true);
		//				sendButton.setFocus(true);
		//			}
		//		});


		travelSmartService = new AsyncCallback<ResultFields>(){
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught.getMessage());
			}

			@Override
			public void onSuccess(ResultFields result) {
				
				
				
				
			}
		};
	}
	
	private class SearchButtonClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) 
		{
			
			Window.alert("click be determined!");
			
			String car = _carModel.getText();
			String start = _startingPoint.getText();
			String end = _destination.getText();

			SearchFields search = new SearchFields();
			
			search.setCarType(car);
			if(start.trim().isEmpty())
			{
				search.setStartingPoint(_latitude + "," + _longitude);
			}
			else
			{
				search.setStartingPoint(start);
			}
			search.setDestination(end);
			
			greetingService.greetServer(search, travelSmartService);

		}
	}
	
//	private class RefreshClickHandler implements ClickHandler{
//		@Override
//		public void onClick(ClickEvent event) {
//			RefreshCurrentGPSLocation();
//		}			
//	}
	
	private void RefreshCurrentGPSLocation() {
//		SetLoadingGifVisibility(true);
		_geoposition = Geolocation.getIfSupported();
		if (_geoposition == null) {
			Window.alert("Sorry, your browser doesn't support the Geolocation feature!");
			return;
		}

		_geoposition.getCurrentPosition(new CurrentPositionCallBack());
	}
	
	private class CurrentPositionCallBack implements Callback<Position, PositionError>
	{
		@Override
		public void onSuccess(Position result) {
//			SetLoadingGifVisibility(false);
			Coordinates coordinates = result.getCoordinates();
			_latitude = coordinates.getLatitude();
			_longitude = coordinates.getLongitude();
			_accuracy = coordinates.getAccuracy();
			
			System.out.println("Current GPS location (" + _latitude + ", " + _longitude +")");
		}

		@Override
		public void onFailure(PositionError reason) {
//			SetLoadingGifVisibility(false);
			Window.alert("Sorry, your location cannot be determined!: " + reason.getMessage());
		}
	}
}
