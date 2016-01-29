package br.com.oauthtwo.api.client;

import java.util.HashMap;
import java.util.Map;


public class ClientStore {

	private static final ClientStore	INSTANCE	= new ClientStore();

	// Mapa de clients. Deve ser substituido por um database
	private Map<String, Client>			clientStore	= new HashMap<>();

	private ClientStore() {
	}

	public static ClientStore getInstance() {
		return INSTANCE;
	}

	public Client storeClient(Client c) {
		clientStore.put(c.getId(), c);
		return clientStore.get(c.getId());
	}

	public Client retrieveClient(String id) {
		return clientStore.get(id);
	}

	public Client removeClient(String id) {
		return clientStore.remove(id);
	}
}
