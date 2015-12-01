package car;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class CarControllerTest {

	@Before
	public void setUP(){
		
	}
	@Test
	public void test1() {
		CarController car1 = new CarController();

//Testing to see the initiated avg, max and current speeds are zero	before the car is set to drive	
		assertEquals(car1.getAvgSpeed(), 0.0 , 0.001 );
		assertEquals(car1.getMaxSpeed(), 0.0, 0.001);
		assertEquals(car1.getSpeed(), 0.0, 0.001);
		assertEquals(car1.getRadio(), null);
		
		car1.setDrive();//setting the car's state to drive
//set the car's acceleration to zero (by sending 0 to the updateEngine method) 
//and verify that the car's speed remains at zero 
		car1.updateEngine(0);
		assertEquals(0.0, car1.getSpeed(), 0.001);

//initiate the starting values of distance and fuel level to compare them in later on tests
		Double initialDistance = car1.getDistance();
		Double initialFuelLevel = car1.getFuel();
		
//accelerate the car (by sending 1 to the updateEngine method)
//and verify that the speed after acceleration is greater than the speed before acceleration.
		car1.updateEngine(1);
		Double speedAfterAcc = car1.getSpeed();
//verify that speed has increased to a value higher than zero 
//(car accelerated) when the updateEngine was sent a positive number
//and that the distance has increased since the car was started 
//since some positive speed was achieved
		assertEquals((speedAfterAcc > 0.0), true);
		assertEquals((car1.getDistance() > initialDistance), true);
		assertEquals((car1.getFuel() > initialFuelLevel), false);

//decelerate the car (by sending -1 to the updateEngine method)
		car1.updateEngine(-1);
//verify that speed has decreased upon sending a negative value to the updateEngine
		assertEquals((car1.getSpeed() < speedAfterAcc), true);
//making sure speed never gets below zero
		assertEquals((car1.getSpeed() < 0.0), false);
	}

}
