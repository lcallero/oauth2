package br.com.oauthtwo.api.token;

import java.util.HashMap;
import java.util.Map;

import br.com.oauthtwo.api.client.Client;

public class TokenStore {

    private static final TokenStore INSTANCE = new TokenStore();

    // Mapa de clients. Deve ser substituido por um database
    private static Map<String, Token> tokenStore = new HashMap<>();

    private TokenStore() {
    }

    public static TokenStore getInstance() {
	return INSTANCE;
    }

    public Token storeToken(Token t) {
	tokenStore.put(t.getAccess_token(), t);
	System.out.println("Token stored:" + t);
	return tokenStore.get(t.getAccess_token());
    }

    public Token retrieveToken(String accessToken) {
	return tokenStore.get(accessToken);
    }

    public Map<String, Token> listAll() {
	return this.tokenStore;
    }

}
