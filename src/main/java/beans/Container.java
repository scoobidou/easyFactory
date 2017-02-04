package beans;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Container {

	private String idContainer;
	private String name;
	private String image;
	private String command;
	private String state;
	private String status;
	private String ip;
	private String privatePort;
	private String publicPort;
	private String type;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPrivatePort() {
		return privatePort;
	}

	public void setPrivatePort(String privatePort) {
		this.privatePort = privatePort;
	}

	public String getPublicPort() {
		return publicPort;
	}

	public void setPublicPort(String publicPort) {
		this.publicPort = publicPort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdContainer() {
		return idContainer;
	}

	public void setIdContainer(String idContainer) {
		this.idContainer = idContainer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public Container(JsonObject jso) {
		super();
		
		JsonArray jArrayPorts = jso.get("Ports").getAsJsonArray();
		//JsonArray jArrayLabels = jso.get("Labels").getAsJsonArray();
		String fullName = "";
		for (int i = 0; i < jso.get("Names").getAsJsonArray().size(); i++){
			fullName += jso.get("Names").getAsJsonArray().get(i).getAsString() +" ";
		}
		this.name = fullName.replaceAll("/", "");
		this.idContainer = jso.get("Id").getAsString();
		this.image = jso.get("Image").getAsString();
		this.command = jso.get("Command").getAsString();
		this.state = jso.get("State").getAsString();
		this.status = jso.get("Status").getAsString();
		this.ip = jArrayPorts.get(0).getAsJsonObject().get("IP").getAsString();
		this.privatePort = jArrayPorts.get(0).getAsJsonObject().get("PrivatePort").getAsString();
		this.publicPort = jArrayPorts.get(0).getAsJsonObject().get("PublicPort").getAsString();
		this.type = jArrayPorts.get(0).getAsJsonObject().get("Type").getAsString();
	}

	@Override
	public String toString() {
		return "Container [idContainer=" + idContainer + ", name=" + name + ", image=" + image + ", command=" + command
				+ ", state=" + state + ", status=" + status + ", ip=" + ip + ", privatePort=" + privatePort
				+ ", publicPort=" + publicPort + ", type=" + type + "]";
	}


}
