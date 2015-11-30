package radio;

import static org.junit.Assert.*;

import org.junit.Test;

import car.CarController;
import phone.Phone;
import user.User;

public class RadioTest {

	@Test
	public void test() {
		CarController c1 = new CarController();
		User u1 = new User("ahmed");
		Radio r1 = new Radio(u1, c1);
		Phone p1 = new Phone(u1, c1);
		
		c1.setPhone(p1);
		c1.setRadio(r1);
		
		assertEquals(r1.isOn(), false);
		
		r1.setPower(true);
		
		assertEquals(new Double(r1.getSpeakerVol()), (double) 5.0, 0.0001);
		
		r1.incSpeakerVol();
		assertEquals(new Double(r1.getSpeakerVol()) > (double) 5.0, true);
		
		r1.speakerMute();
		assertEquals(r1.getSpeakerVol(), "MUTE" );
		
		r1.speakerUnmute();
		assertFalse(r1.getSpeakerVol() == "MUTE");
	}

}

