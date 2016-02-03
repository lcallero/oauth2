package br.com.oauthtwo.api.token;

public class Principal {

    private String clientId;
    private String name;

    public Principal(String clientId, String name) {
	this.clientId = clientId;
	this.name = name;
    }

    public String getClientId() {
	return clientId;
    }

    public void setClientId(String clientId) {
	this.clientId = clientId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
