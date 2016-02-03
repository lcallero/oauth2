package br.com.oauthtwo.api.client;

import java.util.HashMap;
import java.util.Map;

public class ClientStore {

    private static final ClientStore INSTANCE = new ClientStore();

    // Mapa de clients. Deve ser substituido por um database
    private Map<Long, Client> clientStoreMap = new HashMap<>();
    private Map<String, Long> clientIdIndex = new HashMap<>();

    private ClientStore() {
    }

    public static ClientStore getInstance() {
	return INSTANCE;
    }

    public Map<Long, Client> listAllClients() {
	return this.clientStoreMap;
    }

    public void removeClient(Long id) {
	clientIdIndex.remove(clientStoreMap.get(id).getClientId());
	System.out.println("Removed:" + clientStoreMap.remove(id));
	return;
    }

    public Client storeClient(Client c) {
	clientStoreMap.put(c.getId(), c);
	clientIdIndex.put(c.getClientId(), c.getId());
	System.out.println("Client index:" + c.getClientId() + ">" + c.getId() + " Client stored:" + c);
	return clientStoreMap.get(c.getId());
    }

    public Client retrieveClient(Long id) {
	return clientStoreMap.get(id);
    }

    public Client retriveClientByClientId(String clientId) {
	if (null != clientId && clientIdIndex.containsKey(clientId)) {
	    return clientStoreMap.get(clientIdIndex.get(clientId));
	}
	return null;
    }
}
