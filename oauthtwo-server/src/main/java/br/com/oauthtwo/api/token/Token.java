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

	private static ObjectMapper	jackson	= new ObjectMapper();

	private String				accessToken;
	private String				refreshToken;
	private String				type;
	private Long				expiresIn;
	private String				scope;
	private String				username;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	private Map<String, String>	params	= new HashMap<String, String>();

	private Token() {
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
