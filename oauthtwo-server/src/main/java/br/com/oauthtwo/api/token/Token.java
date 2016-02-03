package br.com.oauthtwo.api.token;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * AccessToken
 * 
 * @see https://tools.ietf.org/html/rfc6750#page-10
 * 
 *      <pre>
 * 4.  Example Access Token Response
 * 
 *   Typically, a bearer token is returned to the client as part of an
 *   OAuth 2.0 [RFC6749] access token response.  An example of such a
 *   response is:
 * 
 *     HTTP/1.1 200 OK
 *     Content-Type: application/json;charset=UTF-8
 *     Cache-Control: no-store
 *     Pragma: no-cache
 * 
 *     {
 *       "access_token":"mF_9.B5f-4.1JqM",
 *       "token_type":"Bearer",
 *       "expires_in":3600,
 *       "refresh_token":"tGzv3JOkF0XG5Qx2TlKWIA"
 *     }
 * </pre>
 */

public class Token {

    private static ObjectMapper jackson = new ObjectMapper();

    private String access_token;
    private String refreshToken;
    private String token_type;
    private Long expires_in;
    private String[] scopes;
    private String audience;
    private Principal principal;
    private Map<String, String> params = new HashMap<String, String>();

    public Principal getPrincipal() {
	return this.principal;
    }

    public void setPrincipal(Principal principal) {
	this.principal = principal;
    }

    public String getAccess_token() {
	return access_token;
    }

    public void setAccessToken(String accessToken) {
	this.access_token = accessToken;
    }

    public String getRefreshToken() {
	return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
	this.refreshToken = refreshToken;
    }

    public Long getExpires_in() {
	return expires_in;
    }

    public void setExpires_in(Long expires_in) {
	this.expires_in = expires_in;
    }

    public String getToken_type() {
	return token_type;
    }

    public void setType(String type) {
	this.token_type = type;
    }

    public String getAudience() {
	return audience;
    }

    public void setAudience(String audience) {
	this.audience = audience;
    }

    public String[] getScopes() {
	return scopes;
    }

    public void setScopes(String[] scopes) {
	this.scopes = scopes;
    }

    public Map<String, String> getParams() {
	return params;
    }

    public void setParams(Map<String, String> params) {
	this.params = params;
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
