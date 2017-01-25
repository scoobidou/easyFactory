package beans;

import java.security.Key;
import java.sql.Timestamp;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import security.SecurityUtils;

public class UserProperties {

	private Long id;
	private Long idUser;
	private String sshHost;
	private String sshUser;
	private String sshPassword;
	private Timestamp creationDate;
	
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
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreation_date(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		
		try{
			sshPassword = SecurityUtils.decrypt(sshPassword);
		}catch(Exception e){
			return null;
		}
		return "UserProperties [id=" + id + ", idUser=" + idUser + ", sshHost=" + sshHost + ", sshUser=" + sshUser
				+ ", sshPassword=" + sshPassword + ", creationDate=" + creationDate + "]";
	}
	
}
