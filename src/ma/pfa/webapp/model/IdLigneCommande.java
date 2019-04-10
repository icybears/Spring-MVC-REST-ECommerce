package ma.pfa.webapp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable

public class IdLigneCommande implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_produit")
	private Produit produit;

	@ManyToOne
	@JoinColumn(name = "id_commandeClient")
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof IdLigneCommande))
			return false;
		return this.produit != null && this.commandeClient != null
				&& (this.produit.getId() == ((IdLigneCommande) o).getProduit().getId())
				&& (this.commandeClient.getId() == ((IdLigneCommande) o).getCommandeClient().getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
}
