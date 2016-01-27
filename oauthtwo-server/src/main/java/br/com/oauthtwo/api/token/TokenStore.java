package br.com.oauthtwo.token;

import java.util.HashMap;
import java.util.Map;


public class TokenStore {

	private static final TokenStore	INSTANCE	= new TokenStore();

	// Mapa de clients. Deve ser substituido por um database
	private Map<String, Token>		tokenStore	= new HashMap<>();

	private TokenStore() {
	}

	public static TokenStore getInstance() {
		return INSTANCE;
	}

	public Token storeToken(Token t) {
		return tokenStore.put(t.getAccessToken(), t);
	}

	public Token retrieveToken(String id) {
		return tokenStore.get(id);
	}

}
