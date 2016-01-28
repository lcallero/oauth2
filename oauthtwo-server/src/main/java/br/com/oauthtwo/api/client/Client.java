package br.com.oauthtwo.api.client;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@JsonInclude(Include.NON_EMPTY)
public class Client {

	private static ObjectMapper	jackson	= new ObjectMapper();

	private String				clientId;
	private String				clientName;
	private String				clientSecret;
	private String				callBackURL;
	private String[]			scopes;

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCallBackURL() {
		return callBackURL;
	}

	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	private Map<String, Object>	parameters	= new HashMap<String, Object>();

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String id) {
		this.clientId = id;
	}

	public String getSecret() {
		return clientSecret;
	}

	public void setSecret(String secret) {
		this.clientSecret = secret;
	}

	public String[] getScopes() {
		return scopes;
	}

	public void setScopes(String[] scopes) {
		this.scopes = scopes;
	}

	@Override
	public String toString() {
		try {
			return jackson.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
