package beans;

public class DockerService {

	private String Name;
	private String image;
	private String tag;
	private String containerName;
	private String environnement;
	private String ports;
	private String volumes;
	private String restart;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public String getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(String environnement) {
		this.environnement = environnement;
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public String getVolumes() {
		return volumes;
	}

	public void setVolumes(String volumes) {
		this.volumes = volumes;
	}

	public String getRestart() {
		return restart;
	}

	public void setRestart(String restart) {
		this.restart = restart;
	}

	@Override
	public String toString() {
		return "DockerService [Name=" + Name + ", image=" + image + ", tag=" + tag + ", containerName=" + containerName
				+ ", environnement=" + environnement + ", ports=" + ports + ", volumes=" + volumes + ", restart="
				+ restart + "]";
	}

}
