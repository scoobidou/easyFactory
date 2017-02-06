package beans;

import java.sql.Timestamp;

public class AvaibleService {

	private Long id;
	private String serviceName;
	private String type;
	private Timestamp creationDate;
	
	@Override
	public String toString() {
		return "AvaibleService [id=" + id + ", serviceName=" + serviceName + ", type=" + type + ", creationDate="
				+ creationDate + "]";
	}
	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
}
