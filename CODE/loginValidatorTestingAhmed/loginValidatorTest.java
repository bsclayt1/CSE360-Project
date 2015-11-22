package helloworld;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class loginValidatorTest {

	loginValidator lv;
	@Before
	public void setUp(){
		lv = new loginValidator();
	}
	
	@Test
	public void test() {
		//tries to login with correct username and password, should return true
		assertEquals(lv.validate("ahmed", "5566"), true);
		assertEquals(lv.validate("ben", "7891"), true);
		
		
		//tries wrong password, validate should return false
		assertEquals(lv.validate("ahmed", "5565"), false);
		assertEquals(lv.validate("robert", "1235"), false);
	}

}
