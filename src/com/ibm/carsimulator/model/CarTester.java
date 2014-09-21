package com.ibm.carsimulator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CarTester {

	@Before
	public void setup(){
		Car.setTestMode(true);
	}
	
	@Test
	public void testByAssertions() {
		String[][] carParts = {
				{"eng", "engine"},
				{"exh", "exhaust"},
				{"ign", "ignition"}
		};
		
		Car car = new Car(carParts);
		assertEquals(3, car.getOverallHealth());
		car.tear(null);
		assertEquals(2, car.getOverallHealth());
		car.tear(null);
		assertEquals(1, car.getOverallHealth());
		car.tear(null);
		assertEquals(0, car.getOverallHealth());
		car.serve(null);
		assertEquals(3, car.getOverallHealth());
		car.tear("eng");
		car.tear("eng");
		assertEquals(2, car.getOverallHealth());		
	}
	
	@Test
	public void testByLoging() {
		String[][] carParts = {
				{"eng", "engine"},
				{"exh", "exhaust"},
				{"ign", "ignition"}
		};
		
		Car car = new Car(carParts);
		System.out.println(car.getHealthStatus(null));
		
		car.tear("eng");
		System.out.println(car.getHealthStatus("eng"));
		
		car.tear("eng");
		System.out.println(car.getHealthStatus("eng"));
		
		car.tear("exh");
		System.out.println(car.getHealthStatus("exh"));
		
		car.tear(null);
		System.out.println(car.getHealthStatus(null));
		
		car.tear("eng");
		System.out.println(car.getHealthStatus("eng"));
		
		car.tear("eng");
		System.out.println(car.getHealthStatus("eng"));
		
		car.tear(null);
		System.out.println(car.getHealthStatus(null));
		
		car.serve(null);
		System.out.println(car.getHealthStatus(null));

		car.tear("ign");
		System.out.println(car.getHealthStatus("ign"));
		
	}	
}
