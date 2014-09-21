package com.ibm.carsimulator.model;

import java.util.HashMap;
import java.util.Map;

public class Car {
	protected final static int HEALTH_FULL = 3;
	protected final static int HEALTH_MEDIUM = 2;
	protected final static int HEALTH_LOW = 1;
	protected final static int HEALTH_NONE = 0;
	
	protected final static String HEALTH_MSG_FULL = "The %s is newly served. Drive safe. ";
	protected final static String HEALTH_MSG_MEDIUM = "The %s is getting worn down. Serving it would be a good idea. ";
	protected final static String HEALTH_MSG_LOW = "The %s is breaking down. It needs imidiate service. ";
	protected final static String HEALTH_MSG_NONE = "The %s is broken. Call a towing service. ";
	protected final static String HEALTH_MSG_TEST = "[Health: %d] ";
	
	protected static boolean testMode = false;
	
	private String name;
	private Map<String, CarPart> carParts;

	/**
	 * Initiates the car with car parts as the parameter.
	 * Every car part is injected as a string array. 
	 * The first value is used a the key and the second value is used as the display name.
	 * @param carParts
	 */
	public Car(String[][] carParts) {
		this.name = "car";
		
		this.carParts = new HashMap<String, CarPart>();
		for (String[] carPart : carParts) {
			this.carParts.put(carPart[0], new CarPart(carPart[1]));
		}
	}
	
	/**
	 * Select to true if you want to run in test mode. Set to false by default.
	 * Displays health values in status messages in test mode.
	 * @param bool
	 */
	public static void setTestMode(boolean bool){
		testMode = bool;
	}
	
	/**
	 * Decreases health of specified car part.
	 * If the car part does not exist, the health of all car parts will be decreased.
	 * @param carPart
	 */
	public void tear(String carPart) {
		CarPart part = carParts.get(carPart);
		if (part != null) {
			part.tear();
		
		}else {
			tearCarParts();
		}
	}
	
	/**
	 * Returns the health status of specified car part.
	 * If the car part does not exist, the car health will be returned.
	 * @param carPart
	 * @return Status message as a byte array
	 */
	public byte[] getHealthStatusAsBytes(String carPart){
		CarPart part = carParts.get(carPart);
		if (part != null) {
			return part.getHealthStatusAsBytes();			
		}
		
		int overAllHealth = getOverallHealth();		
		String msg = "";		
		switch (overAllHealth) {
		case HEALTH_FULL:
			msg = testMode ?
					String.format(HEALTH_MSG_TEST + HEALTH_MSG_FULL , overAllHealth, name.toUpperCase()): 
					String.format(HEALTH_MSG_FULL, name.toUpperCase());
			return msg.getBytes();

		case HEALTH_MEDIUM:
			msg = testMode ?
					String.format(HEALTH_MSG_TEST + HEALTH_MSG_MEDIUM , overAllHealth, name.toUpperCase()): 
					String.format(HEALTH_MSG_MEDIUM , name.toUpperCase());
			return msg.getBytes();

		case HEALTH_LOW:
			msg = testMode ?
					String.format(HEALTH_MSG_TEST + HEALTH_MSG_LOW , overAllHealth, name.toUpperCase()): 
					String.format(HEALTH_MSG_LOW, name.toUpperCase());
			return msg.getBytes();

		case HEALTH_NONE:
			msg = testMode ?
					String.format(HEALTH_MSG_TEST + HEALTH_MSG_NONE , overAllHealth, name.toUpperCase()): 
					String.format(HEALTH_MSG_NONE, name.toUpperCase());
			return msg.getBytes();
			
		default:
			return "Health not found.".getBytes();
		}	
	}
	
	/**
	 * Restores the health of all car parts.
	 * If the car part does not exist, all the car parts will be served.
	 * @param carPart
	 */
	public void serve(String carPart) {
		CarPart part = carParts.get(carPart);
		if (part != null) {
			part.serve();
		
		}else {
			serveCarParts();
		}
	}
	
	/**
	 * Returns the health status of specified car part.
	 * If the car part does not exist, the car health will be returned.
	 * @param carPart
	 * @return Status message as a string
	 */
	public String getHealthStatus(String carPart) {
		return new String(getHealthStatusAsBytes(carPart));
	}
	
	private void serveCarParts() {
		for (CarPart carPart : carParts.values()) {
			carPart.serve();
		}
	}
	
	private void tearCarParts() {
		for (CarPart carPart : carParts.values()) {
			carPart.tear();
		}
	}
	
	protected int getOverallHealth(){
		int healthSum = 0;
		for (CarPart carPart : carParts.values()) {
			healthSum += carPart.getHealth();
		}		
		int average = Math.round(healthSum / carParts.size());
		return average;
	}
}