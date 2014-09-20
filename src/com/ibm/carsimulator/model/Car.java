package com.ibm.carsimulator.model;

import java.util.HashMap;
import java.util.Map;

public class Car {
	public final static int HEALTH_FULL = 3;
	public final static int HEALTH_MEDIUM = 2;
	public final static int HEALTH_LOW = 1;
	public final static int HEALTH_NONE = 0;
	
	public final static String HEALTH_MSG_FULL = "The %s is newly served. Drive safe.";
	public final static String HEALTH_MSG_MEDIUM = "The %s is getting worn down. Serving it would be a good idea.";
	public final static String HEALTH_MSG_LOW = "The %s is breaking down. It needs imidiate service.";
	public final static String HEALTH_MSG_NONE = "The %s is broken. Call a towing service";
	
//	public final static String HEALTH_MSG_FULL = "%s = 3";
//	public final static String HEALTH_MSG_MEDIUM = "%s = 2";
//	public final static String HEALTH_MSG_LOW = "%s = 1";
//	public final static String HEALTH_MSG_NONE = "%s = 0";
	
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
	 * Returns the health of specified car part.
	 * If the car part does not exist, the car health will be returned.
	 * @param carPart
	 * @return Status message as byte array
	 */
	public byte[] getHealthStatus(String carPart){
		CarPart part = carParts.get(carPart);
		if (part != null) {
			return part.getHealthStatus();			
		}
		
		int overAllHealth = getOverallHealth();
		switch (overAllHealth) {
		case Car.HEALTH_FULL:
			return String.format(Car.HEALTH_MSG_FULL, name.toUpperCase()).getBytes();

		case Car.HEALTH_MEDIUM:
			return String.format(Car.HEALTH_MSG_MEDIUM, name.toUpperCase()).getBytes();

		case Car.HEALTH_LOW:
			return String.format(Car.HEALTH_MSG_LOW, name.toUpperCase()).getBytes();

		case Car.HEALTH_NONE:
			return String.format(Car.HEALTH_MSG_NONE, name.toUpperCase()).getBytes();
			
		default:
			return "Default.".getBytes();
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