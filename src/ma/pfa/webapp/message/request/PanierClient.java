package ma.pfa.webapp.message.request;

import java.util.Set;

import ma.pfa.webapp.model.Client;
import ma.pfa.webapp.model.LigneCommande;

public class PanierClient {
	
	private Set<LigneCommande> items;

	private Client client;
	
	public Client getClient() {
		return client;
	}
	public Set<LigneCommande> getItems() {
		return items;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void setItems(Set<LigneCommande> items) {
		this.items = items;
	}
	
	
}
