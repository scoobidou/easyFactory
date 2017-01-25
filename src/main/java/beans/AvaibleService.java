package beans;

import java.sql.Timestamp;

public class AvaibleService {

	@Override
	public String toString() {
		return "AvaibleService [id=" + id + ", serviceName=" + serviceName + ", creationDate=" + creationDate + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	private Long id;
	private String serviceName;
	private Timestamp creationDate;
}
