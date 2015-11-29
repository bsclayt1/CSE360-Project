package GUI;

import static org.junit.Assert.*;

import org.junit.Test;
import car.*;
import radio.*;
import phone.*;
import user.*;
public class MainGUITest {

	@Test
	public void test() {
		CarController c1 = new CarController();
		User u1 = new User("ahmed");
		
		Phone p1 = new Phone(u1, c1);
		
		Radio r1 = new Radio(u1, c1);
		
		c1.setPhone(p1);
		c1.setRadio(r1);
		
		p1.makeCall("111-111-1111");
		assertEquals(r1.isOn(), false);
		
		p1.endCall();
		r1.setPower(true);
		assertEquals(r1.isOn(), true);
		
		
		
	}

}
