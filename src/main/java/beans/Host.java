package beans;

import com.google.gson.JsonObject;

public class Host {

	@Override
	public String toString() {
		return "Host [idHost=" + idHost + ", nbContainers=" + nbContainers + ", nbRunningContainers="
				+ nbRunningContainers + ", nbPausedContainers=" + nbPausedContainers + ", nbStoppedContainers="
				+ nbStoppedContainers + ", nbImages=" + nbImages + ", kernelVersion=" + kernelVersion + ", osType="
				+ osType + ", architecture=" + architecture + "]";
	}

	private String idHost;

	private int nbContainers;
	private int nbRunningContainers;
	private int nbPausedContainers;
	private int nbStoppedContainers;
	private int nbImages;

	private String kernelVersion;
	private String os;
	private String osType;
	private String architecture;

	public String getIdHost() {
		return idHost;
	}

	public int getNbPausedContainers() {
		return nbPausedContainers;
	}

	public void setNbPausedContainers(int nbPausedContainers) {
		this.nbPausedContainers = nbPausedContainers;
	}

	public void setIdHost(String idHost) {
		this.idHost = idHost;
	}

	public int getNbImages() {
		return nbImages;
	}

	public void setNbImages(int nbImages) {
		this.nbImages = nbImages;
	}

	public int getNbContainers() {
		return nbContainers;
	}

	public void setNbContainers(int nbContainers) {
		this.nbContainers = nbContainers;
	}

	public int getNbRunningContainers() {
		return nbRunningContainers;
	}

	public void setNbRunningContainers(int nbRunningContainers) {
		this.nbRunningContainers = nbRunningContainers;
	}

	public int getNbStoppedContainers() {
		return nbStoppedContainers;
	}

	public void setNbStoppedContainers(int nbStoppedContainers) {
		this.nbStoppedContainers = nbStoppedContainers;
	}

	public String getKernelVersion() {
		return kernelVersion;
	}

	public void setKernelVersion(String kernelVersion) {
		this.kernelVersion = kernelVersion;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}
	
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Host(JsonObject jso) {
		super();

		this.idHost = jso.get("ID").getAsString();
		this.nbContainers = jso.get("Containers").getAsInt();
		this.nbRunningContainers = jso.get("ContainersRunning").getAsInt();
		this.nbPausedContainers = jso.get("ContainersPaused").getAsInt();
		this.nbStoppedContainers = jso.get("ContainersStopped").getAsInt();
		this.nbImages = jso.get("Images").getAsInt();
		this.kernelVersion = jso.get("KernelVersion").getAsString();
		this.os = jso.get("OperatingSystem").getAsString();
		this.osType = jso.get("OSType").getAsString();
		this.architecture = jso.get("Architecture").getAsString();
	}

}
