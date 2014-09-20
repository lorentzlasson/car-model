package com.ibm.carsimulator.model;

class CarPart {
	
	private String name;
	private int health;
	
	protected CarPart(String name) {
		this.name = name;
		health = Car.HEALTH_FULL;
	}
	
	protected int getHealth() {
		return health;
	}	
	
	protected void tear() {
		if (health > 0) health--;		
	}
	
	protected void serve() {
		health = Car.HEALTH_FULL;
	}
	
	protected byte[] getHealthStatus() {
		switch (health) {
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
}