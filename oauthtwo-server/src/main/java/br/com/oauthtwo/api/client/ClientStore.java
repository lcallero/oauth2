package br.com.oauthtwo.store;

import java.util.HashMap;
import java.util.Map;

import br.com.oauthtwo.model.Client;


public class Keeper {

	private static final Keeper	INSTANCE	= new Keeper();

	// Mapa de clients. Deve ser substituido por um database
	private Map<String, Client>	clientStore	= new HashMap<>();

	private Keeper() {
	}

	public static Keeper getInstance() {
		return INSTANCE;
	}

	public Client storeClient(Client c) {
		return clientStore.put(c.getId(), c);
	}

	public Client retrieveClient(String id) {
		return clientStore.get(id);
	}

}
