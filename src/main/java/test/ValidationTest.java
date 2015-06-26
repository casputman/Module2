package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Validation;
import static org.hamcrest.CoreMatchers.*;

public class ValidationTest {
		
	@Test
	public void getConnectionTest(){
		assertNotNull("The connection should not be null", Validation.getConnection());
		
	}
	@Test
	public void validateTest() {
		core.User user = Validation.validate("test", "test");
		assertEquals("The id should be 4", 4, user.getIdUser());
		assertEquals("The Surname should be: 'Test'", "Test", user.getSurname());
		assertEquals("The firstname should be: 'test'", "test", user.getFirstname());
		assertEquals("The Length should be: 179", 179, user.getLength(),0);
		assertEquals("The username should be: 'test'", "test", user.getUsername());
		assertEquals("The passwordhash should be: '	348385729e0ecf4da881cafbfb3c09c8029c719a'", "348385729e0ecf4da881cafbfb3c09c8029c719a", user.getPasswordHash());
		assertEquals("The email should be: test123@test.com", "test123@test.com", user.getEmail());
	}
	@Test
	public void hashPasswordTest(){
		assertEquals("The hashes of the passwords should be equals", Validation.hashPassword("kaas"), Validation.hashPassword("kaas"));
		assertThat("The hashes of the passwords must not be equal", Validation.hashPassword("kaas"), not(equalTo("kaas1")));
		assertThat("The hashes of the passwords must not be equal", Validation.hashPassword("kaas"), not(equalTo(Validation.hashPassword("kaas1"))));
	}
	@Test
	public void hashCookieTest(){
		assertEquals("The hashes of the passwords should be equals", Validation.hashCookie("kaas"), Validation.hashCookie("kaas"));
		assertThat("The hashes of the passwords must not be equal", Validation.hashCookie("kaas"), not(equalTo(Validation.hashCookie("kaas1"))));
		assertThat("The hashes of the passwords must not be equal", Validation.hashCookie("kaas"), not(equalTo("kaas1")));
	}
}
