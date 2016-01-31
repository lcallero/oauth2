package br.com.oauthtwo.api.client;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_EMPTY)
public class Client {

    private static ObjectMapper jackson = new ObjectMapper();

    private long id;
    private String name;
    private String clientId;
    private String secret;
    private String contactName;
    private String contactEmail;
    private String callBackURL;
    private String[] scopes;
    private long expireDuration = 100;
    private boolean useRefreshToken = false;
    private boolean allowedImplicitGrant = true;
    private boolean allowedClientCredentials = true;

    public String getContactName() {
	return contactName;
    }

    public void setContactName(String contactName) {
	this.contactName = contactName;
    }

    public String getClientId() {
	return clientId;
    }

    public void setClientId(String clientId) {
	this.clientId = clientId;
    }

    public long getExpireDuration() {
	return expireDuration;
    }

    public void setExpireDuration(long expireDuration) {
	this.expireDuration = expireDuration;
    }

    public boolean isUseRefreshToken() {
	return useRefreshToken;
    }

    public void setUseRefreshToken(boolean useRefreshToken) {
	this.useRefreshToken = useRefreshToken;
    }

    public boolean isAllowedImplicitGrant() {
	return allowedImplicitGrant;
    }

    public void setAllowedImplicitGrant(boolean allowedImplicitGrant) {
	this.allowedImplicitGrant = allowedImplicitGrant;
    }

    public boolean isAllowedClientCredentials() {
	return allowedClientCredentials;
    }

    public void setAllowedClientCredentials(boolean allowedClientCredentials) {
	this.allowedClientCredentials = allowedClientCredentials;
    }

    public String getContactEmail() {
	return contactEmail;
    }

    public void setContactEmail(String clientEmail) {
	this.contactEmail = clientEmail;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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

    private Map<String, Object> parameters = new HashMap<String, Object>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getSecret() {
	return secret;
    }

    public void setSecret(String secret) {
	this.secret = secret;
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
