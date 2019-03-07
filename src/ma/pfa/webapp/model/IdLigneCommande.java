package ma.pfa.webapp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable

public class IdLigneCommande implements Serializable{
	@ManyToOne
	@JoinColumn(name="id_produit")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name="id_commande")
	private CommandeClient commandeClient;

	
	
	public IdLigneCommande() {
		super();
	}

	public IdLigneCommande(Produit produit, CommandeClient commandeClient) {
		super();
		this.produit = produit;
		this.commandeClient = commandeClient;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public CommandeClient getCommandeClient() {
		return commandeClient;
	}

	public void setCommandeClient(CommandeClient commandeClient) {
		this.commandeClient = commandeClient;
	}

}
