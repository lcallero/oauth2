package br.com.oauthtwo.model;

import java.util.HashMap;
import java.util.Map;

import br.com.oauthtwo.util.ChaveCriptografica;

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

public class AccessToken {

	private static final int	SEIS_MINUTOS	= 3600000;

	private String				clientId;
	private TokenType			type;
	private Long				expiresIn;
	private String				scope;
	private String				username;
	private Map<String, String>	params			= new HashMap<String, String>();

	private AccessToken() {
	}

	public AccessToken(final String clientId, final TokenType type, final String scope, final String username) {
		this.clientId = clientId;
		this.type = type;
		this.expiresIn = System.currentTimeMillis() + SEIS_MINUTOS;
		this.scope = scope;
		this.username = username;
	}

	public AccessToken(final String tokenCifrado) {
		ObjectMapper mapper = new ObjectMapper();
		final ChaveCriptografica chaveCriptografica = new ChaveCriptografica();
		try {
			AccessToken accessTokenTemp = mapper.readValue(chaveCriptografica.decrypt(tokenCifrado), AccessToken.class);
			this.clientId = accessTokenTemp.clientId;
			this.type = accessTokenTemp.type;
			this.expiresIn = accessTokenTemp.expiresIn;
			this.scope = accessTokenTemp.scope;
			this.params = accessTokenTemp.params;
			this.username = accessTokenTemp.username;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String toJson() {
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			final String tokenString = objectMapper.writeValueAsString(this);
			return tokenString;
		} catch (Exception e) {
			throw new RuntimeException("it was not possible to serialize the token into json", e);
		}
	}

	public String token() {
		final String aCriptografar = this.toJson();
		final ChaveCriptografica chaveCriptografica = new ChaveCriptografica();
		return chaveCriptografica.encrypt(aCriptografar);
	}

	public String getClientId() {
		return clientId;
	}

	public TokenType getType() {
		return type;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public void addParam(final String key, final String value) {
		params.put(key, value);
	}

	public String getParam(final String key) {
		return params.get(key);
	}

	public Map<String, String> getParams() {
		return params;
	}

	public boolean active() {
		return this.expiresIn > System.currentTimeMillis();
	}

	public void addAllParams(Map<String, String> params) {
		for (String key : params.keySet()) {
			this.params.put(key, params.get(key));
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return this.toJson();
	}
}
