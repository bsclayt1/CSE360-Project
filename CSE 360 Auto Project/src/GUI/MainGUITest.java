package GUI;

import static org.junit.Assert.*;

import org.junit.Test;
import car.*;
import radio.*;
import phone.*;
import user.*;
public class MainGUITest {

	@Test
	public void test1() {
		CarController c1 = new CarController();
//initiating the separate components that are to be connected together to test their interactions
		User u1 = new User("ahmed");
		Phone p1 = new Phone(u1, c1);
		Radio r1 = new Radio(u1, c1);
		
//actually connecting the phone and the radio to the car controller
		c1.setPhone(p1);
		c1.setRadio(r1);
		
//initiating a call and testing to see if the current call number matches the one 
//used when initiating the call	
		p1.makeCall("111-111-1111");
		assertEquals(p1.getNumberCalled(), "111-111-1111");

//showing that the initial state of radio is false, 
//and that it won't turn on until the phone call is ended
		assertEquals(r1.isOn(), false);
		r1.setPower(true);
		assertEquals(r1.isOn(), false);

//showing that when the call is ended, 
//the radio can now be turned on		
		p1.endCall();
		r1.setPower(true);
		assertEquals(r1.isOn(), true);
	}

}
