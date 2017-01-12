package beans;

import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	@Test
	public void testGetFirstName() {
		User user = new User();
		user.setEmail("email@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setPassword("azerty");
		assertEquals(user.getFirstName(),"John");
	}

	public void testSetFirstName() {
		User user = new User();
		user.setEmail("email@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setPassword("azerty");
		assertEquals(user.getFirstName(),"John");
	}

	public void testGetLastName() {
		User user = new User();
		user.setEmail("email@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setPassword("azerty");
		assertEquals(user.getLastName(),"Doe");
	}

	public void testSetLastName() {
		User user = new User();
		user.setEmail("email@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setPassword("azerty");
		assertEquals(user.getLastName(),"Doe");
	}

	public void testGetEmail() {
		User user = new User();
		user.setEmail("email@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setPassword("azerty");
		assertEquals(user.getEmail(),"email@test.com");
	}

	public void testSetEmail() {
		User user = new User();
		user.setEmail("email@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setPassword("azerty");
		assertEquals(user.getEmail(),"email@test.com");
	}

	public void testGetPassword() {
		User user = new User();
		user.setEmail("email@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setPassword("azerty");
		assertEquals(user.getPassword(),"azerty");
	}

	public void testSetPassword() {
		User user = new User();
		user.setEmail("email@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setPassword("azerty");
		assertEquals(user.getPassword(),"azerty");
	}

}
