package beans;

import java.sql.Timestamp;

public class UserProperties {

	private Long id;
	private Long idUser;
	private String sshHost;
	private String sshUser;
	private String sshPassword;
	private Boolean isDefined;
	private Timestamp creation_date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getSshHost() {
		return sshHost;
	}
	public void setSshHost(String sshHost) {
		this.sshHost = sshHost;
	}
	public String getSshUser() {
		return sshUser;
	}
	public void setSshUser(String sshUser) {
		this.sshUser = sshUser;
	}
	public String getSshPassword() {
		return sshPassword;
	}
	public void setSshPassword(String sshPassword) {
		this.sshPassword = sshPassword;
	}
	public Boolean getIsDefined() {
		return isDefined;
	}
	public void setIsDefined(Boolean isDefined) {
		this.isDefined = isDefined;
	}
	public Timestamp getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}
	
}
