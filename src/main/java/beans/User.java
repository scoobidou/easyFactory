package beans;

import java.sql.Timestamp;

public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Timestamp inscriptionDate;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public Timestamp getInscriptionDate() {
		return inscriptionDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setInscriptionDate(Timestamp timestamp) {
		this.inscriptionDate = timestamp;
	}

}
