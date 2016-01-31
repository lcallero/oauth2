package br.com.oauthtwo.api.token;

import java.util.HashMap;
import java.util.Map;

public class TokenStore {

    private static final TokenStore INSTANCE = new TokenStore();

    // Mapa de clients. Deve ser substituido por um database
    private Map<String, Token> tokenStore = new HashMap<>();

    private TokenStore() {
    }

    public static TokenStore getInstance() {
	return INSTANCE;
    }

    public Token storeToken(Token t) {
	tokenStore.put(t.getAccessToken(), t);
	System.out.println("Token stored:" + t);
	return tokenStore.get(t.getAccessToken());
    }

    public Token retrieveToken(String accessToken) {
	return tokenStore.get(accessToken);
    }

}
