package phone;

import static org.junit.Assert.*;

import org.junit.Test;

import car.CarController;
import radio.Radio;
import user.User;

public class PhoneTest {

	@Test
	public void test() {
		CarController c1 = new CarController();
		User u1 = new User("ahmed");
		Radio r1 = new Radio(u1, c1);
		Phone p1 = new Phone(u1, c1);
		
		//assert(p1.getCallDuration(), 
		c1.setPhone(p1);
		c1.setRadio(r1);
		p1.makeCall("111-111-1111");
		
		assertTrue(p1.getOnCall());
		assertEquals(p1.getNumberCalled(), "111-111-1111");
		
		p1.endCall();
		assertFalse(p1.getOnCall());
		
		assertEquals(new Double(p1.getSpeakerVol()), (double) 5.0, 0.0001);
		
		p1.incSpeakerVol();
		assertEquals(new Double(p1.getSpeakerVol()) > (double) 5.0, true);
		
		p1.speakerMute();
		assertEquals(p1.getSpeakerVol(), "MUTE" );
		
		p1.speakerUnmute();
		assertFalse(p1.getSpeakerVol() == "MUTE");
		
		assertEquals(new Double(p1.getMicVol()), (double) 5.0, 0.0001);
		
		p1.incMicVol();
		assertEquals(new Double(p1.getMicVol()) > (double) 5.0, true);
		
		p1.micMute();
		assertEquals(p1.getMicVol(), "MUTE" );
		
		p1.micUnmute();
		assertFalse(p1.getMicVol() == "MUTE");
		
		
		
	}

}
