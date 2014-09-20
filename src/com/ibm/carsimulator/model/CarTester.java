package com.ibm.carsimulator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CarTester {

	@Test
	public void test1() {
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
}
