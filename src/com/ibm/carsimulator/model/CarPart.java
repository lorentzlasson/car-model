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
	
	protected byte[] getHealthStatusAsBytes() {
		
		String msg = "";	
		switch (health) {
		case Car.HEALTH_FULL:
			msg = Car.testMode ?
					String.format(Car.HEALTH_MSG_TEST + Car.HEALTH_MSG_FULL , health, name.toUpperCase()): 
					String.format(Car.HEALTH_MSG_FULL, name.toUpperCase());
			return msg.getBytes();

		case Car.HEALTH_MEDIUM:
			msg = Car.testMode ?
					String.format(Car.HEALTH_MSG_TEST + Car.HEALTH_MSG_MEDIUM , health, name.toUpperCase()): 
					String.format(Car.HEALTH_MSG_MEDIUM, name.toUpperCase());
			return msg.getBytes();

		case Car.HEALTH_LOW:
			msg = Car.testMode ?
					String.format(Car.HEALTH_MSG_TEST + Car.HEALTH_MSG_LOW , health, name.toUpperCase()): 
					String.format(Car.HEALTH_MSG_LOW, name.toUpperCase());
			return msg.getBytes();

		case Car.HEALTH_NONE:
			msg = Car.testMode ?
					String.format(Car.HEALTH_MSG_TEST + Car.HEALTH_MSG_NONE , health, name.toUpperCase()): 
					String.format(Car.HEALTH_MSG_NONE, name.toUpperCase());
			return msg.getBytes();
			
		default:
			return "Default.".getBytes();
		}
	}	
}