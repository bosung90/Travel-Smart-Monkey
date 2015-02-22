package com.teamunion.sampletwo.client;

import com.teamunion.sampletwo.shared.FieldVerifier;
import com.teamunion.sampletwo.shared.ResultFields;
import com.teamunion.sampletwo.shared.SearchFields;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sampletwo implements EntryPoint {
	
	private final HTML table = new HTML();
	
	private final TextBox _carModel = TextBox.wrap(DOM.getElementById("carModel"));
	private final TextBox _startingPoint = TextBox.wrap(DOM.getElementById("startingPoint"));
	private final TextBox _destination = TextBox.wrap(DOM.getElementById("destination"));
	
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
	public void onModuleLoad() {
		final Button _searchButton = Button.wrap(DOM.getElementById("searchBtn"));


		table.setHTML("	<table class=\"table table-bordered\">\n" + 
				"	<tr>\n" + 
				"    <td>Mode of Travel</td>\n" + 
				"    <td>Pros</td>		\n" + 
				"    <td>Cons</td>\n" + 
				"    <td>Cost</td>\n" + 
				"    <td>Time</td>\n" + 
				"    <td>CO2 Emission</td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"    <td>Car</td>\n" + 
				"    <td></td>		\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"    <td>Bus</td>\n" + 
				"    <td></td>		\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"      <td>Bicyle</td>\n" + 
				"    <td></td>		\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"  </tr>\n" + 
				"    <tr>\n" + 
				"      <td>Walk</td>\n" + 
				"    <td></td>		\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"    <td></td>\n" + 
				"  </tr>\n" + 
				"\n" + 
				"</table>");

		RootPanel.get("travelTable").add(table);
		
		
		
		
		
		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
//				errorLabel.setText("");
//				String textToServer = nameField.getText();
//				if (!FieldVerifier.isValidName(textToServer)) {
//					errorLabel.setText("Please enter at least four characters");
//					return;
//				}
//
//				// Then, we send the input to the server.
//				sendButton.setEnabled(false);
//				textToServerLabel.setText(textToServer);
//				serverResponseLabel.setText("");
				String car = _carModel.getText();
				String start = _startingPoint.getText();
				String end = _destination.getText();
	//
				SearchFields search = new SearchFields();
	//
				search.setCarType(car);
				if(start.trim().isEmpty())
				{
					search.setStartingPoint("" + "," + "");
				}
				else
				{
					search.setStartingPoint(start);
				}
				search.setDestination(end);
				
				
				greetingService.greetServer(search,
						new AsyncCallback<ResultFields>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(ResultFields result) {
//								dialogBox.setText("Remote Procedure Call");
//								serverResponseLabel
//										.removeStyleName("serverResponseLabelError");
////								serverResponseLabel.setHTML(result);
//								dialogBox.center();
//								closeButton.setFocus(true);
								RootPanel.get("travelTable").clear();
								table.setHTML("	<table class=\"table table-bordered\">\n" + 
										"	<tr>\n" + 
										"    <td>Mode of Travel</td>\n" + 
										"    <td>Pros</td>		\n" + 
										"    <td>Cons</td>\n" + 
										"    <td>Cost</td>\n" + 
										"    <td>Time</td>\n" + 
										"    <td>CO2 Emission</td>\n" + 
										"  </tr>\n" + 
										"  <tr>\n" + 
										"    <td>Car</td>\n" + 
										"    <td>" + result.getCarPros()+ "</td>\n" + 
										"    <td>" + result.getCarCons()+ "</td>\n" + 
										"    <td>" + result.getCarCost() + "</td>\n" + 
										"    <td>" + result.getCarTime()+ "</td>\n" + 
										"    <td>" + result.getCarCO2()+ "</td>\n" + 
										"  </tr>\n" + 
										"  <tr>\n" + 
										"    <td>Bus</td>\n" + 
										"    <td>" + result.getTransitPros()+ "</td>\n" + 
										"    <td>" + result.getTransitCons()+ "</td>\n" + 
										"    <td>" + result.getTransitCost()+ "</td>\n" + 
										"    <td>" + result.getBusTime() + "</td>\n" + 
										"    <td>" + result.getBusCO2()+ "</td>\n" + 
										"  </tr>\n" + 
										"  <tr>\n" + 
										"      <td>Bicyle</td>\n" + 
										"    <td>"+ result.getBikePros()+ "</td>\n" + 
										"    <td>" + result.getBikeCons()+ "</td>\n" + 
										"    <td>" + result.getBikeCost()+"</td>\n" + 
										"    <td>" + result.getBikeTime()+ "</td>\n" + 
										"    <td>" + result.getBikeCO2() + "</td>\n" + 
										"  </tr>\n" + 
										"    <tr>\n" + 
										"      <td>Walk</td>\n" + 
										"    <td>"+ result.getWalkPros()+ "</td>\n" + 
										"    <td>"+ result.getWalkCons()+ "</td>\n" + 
										"    <td>"+ result.getWalkCost()+"</td>\n" + 
										"    <td>" + result.getWalkTime()+"</td>\n" + 
										"    <td>" + result.getWalkCO2()+ "</td>\n" + 
										"  </tr>\n" + 
										"\n" + 
										"</table>");

								RootPanel.get("travelTable").add(table);	
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		_searchButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	
	
		class SearchButtonClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) 
			{

//				String car = _carModel.getText();
//				String start = _startingPoint.getText();
//				String end = _destination.getText();
	//
				SearchFields search = new SearchFields();
	//
//				search.setCarType(car);
//				if(start.trim().isEmpty())
//				{
//					search.setStartingPoint(_latitude + "," + _longitude);
//				}
//				else
//				{
//					search.setStartingPoint(start);
//				}
//				search.setDestination(end);
				//			travelSmartService = new AsyncCallback<ResultFields>(){
				//				@Override
				//				public void onFailure(Throwable caught) {
				//					System.out.println(caught.getMessage());
				//				}
				//
				//				@Override
				//				public void onSuccess(ResultFields result) {
				//					RootPanel.get("travelTable").clear();
				//					table.setHTML("	<table class=\"table table-bordered\">\n" + 
				//							"	<tr>\n" + 
				//							"    <td>Mode of Travel</td>\n" + 
				//							"    <td>Pros</td>		\n" + 
				//							"    <td>Cons</td>\n" + 
				//							"    <td>Cost</td>\n" + 
				//							"    <td>Time</td>\n" + 
				//							"    <td>CO2 Emission</td>\n" + 
				//							"  </tr>\n" + 
				//							"  <tr>\n" + 
				//							"    <td>Car</td>\n" + 
				//							"    <td>" + result.getCarPros()+ "</td>\n" + 
				//							"    <td>" + result.getCarCons()+ "</td>\n" + 
				//							"    <td>" + result.getCarCost() + "</td>\n" + 
				//							"    <td>" + result.getCarTime()+ "</td>\n" + 
				//							"    <td>" + result.getCarCO2()+ "</td>\n" + 
				//							"  </tr>\n" + 
				//							"  <tr>\n" + 
				//							"    <td>Bus</td>\n" + 
				//							"    <td>" + result.getTransitPros()+ "</td>\n" + 
				//							"    <td>" + result.getTransitCons()+ "</td>\n" + 
				//							"    <td>" + result.getTransitCost()+ "</td>\n" + 
				//							"    <td>" + result.getBusTime() + "</td>\n" + 
				//							"    <td>" + result.getBusCO2()+ "</td>\n" + 
				//							"  </tr>\n" + 
				//							"  <tr>\n" + 
				//							"      <td>Bicyle</td>\n" + 
				//							"    <td>"+ result.getBikePros()+ "</td>\n" + 
				//							"    <td>" + result.getBikeCons()+ "</td>\n" + 
				//							"    <td>" + result.getBikeCost()+"</td>\n" + 
				//							"    <td>" + result.getBikeTime()+ "</td>\n" + 
				//							"    <td>" + result.getBikeCO2() + "</td>\n" + 
				//							"  </tr>\n" + 
				//							"    <tr>\n" + 
				//							"      <td>Walk</td>\n" + 
				//							"    <td>"+ result.getWalkPros()+ "</td>\n" + 
				//							"    <td>"+ result.getWalkCons()+ "</td>\n" + 
				//							"    <td>"+ result.getWalkCost()+"</td>\n" + 
				//							"    <td>" + result.getWalkTime()+"</td>\n" + 
				//							"    <td>" + result.getWalkCO2()+ "</td>\n" + 
				//							"  </tr>\n" + 
				//							"\n" + 
				//							"</table>");
				//					
				//					RootPanel.get("travelTable").add(table);	
				//				}
				//			};
				greetingService.greetServer(null, new AsyncCallback<ResultFields>() {
					@Override
					public void onFailure(Throwable caught) {
						System.out.println(caught.getMessage());
					}

					@Override
					public void onSuccess(ResultFields result) {
						RootPanel.get("travelTable").clear();
						table.setHTML("	<table class=\"table table-bordered\">\n" + 
								"	<tr>\n" + 
								"    <td>Mode of Travel</td>\n" + 
								"    <td>Pros</td>		\n" + 
								"    <td>Cons</td>\n" + 
								"    <td>Cost</td>\n" + 
								"    <td>Time</td>\n" + 
								"    <td>CO2 Emission</td>\n" + 
								"  </tr>\n" + 
								"  <tr>\n" + 
								"    <td>Car</td>\n" + 
								"    <td>" + result.getCarPros()+ "</td>\n" + 
								"    <td>" + result.getCarCons()+ "</td>\n" + 
								"    <td>" + result.getCarCost() + "</td>\n" + 
								"    <td>" + result.getCarTime()+ "</td>\n" + 
								"    <td>" + result.getCarCO2()+ "</td>\n" + 
								"  </tr>\n" + 
								"  <tr>\n" + 
								"    <td>Bus</td>\n" + 
								"    <td>" + result.getTransitPros()+ "</td>\n" + 
								"    <td>" + result.getTransitCons()+ "</td>\n" + 
								"    <td>" + result.getTransitCost()+ "</td>\n" + 
								"    <td>" + result.getBusTime() + "</td>\n" + 
								"    <td>" + result.getBusCO2()+ "</td>\n" + 
								"  </tr>\n" + 
								"  <tr>\n" + 
								"      <td>Bicyle</td>\n" + 
								"    <td>"+ result.getBikePros()+ "</td>\n" + 
								"    <td>" + result.getBikeCons()+ "</td>\n" + 
								"    <td>" + result.getBikeCost()+"</td>\n" + 
								"    <td>" + result.getBikeTime()+ "</td>\n" + 
								"    <td>" + result.getBikeCO2() + "</td>\n" + 
								"  </tr>\n" + 
								"    <tr>\n" + 
								"      <td>Walk</td>\n" + 
								"    <td>"+ result.getWalkPros()+ "</td>\n" + 
								"    <td>"+ result.getWalkCons()+ "</td>\n" + 
								"    <td>"+ result.getWalkCost()+"</td>\n" + 
								"    <td>" + result.getWalkTime()+"</td>\n" + 
								"    <td>" + result.getWalkCO2()+ "</td>\n" + 
								"  </tr>\n" + 
								"\n" + 
								"</table>");

						RootPanel.get("travelTable").add(table);	
					}
				});
			}
		}
		
		SearchButtonClickHandler searchHandler = new SearchButtonClickHandler();
//		_searchButton.addClickHandler(searchHandler);
	
	
	}
	
	
}
