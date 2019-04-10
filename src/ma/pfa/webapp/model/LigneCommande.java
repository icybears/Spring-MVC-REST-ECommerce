
package ma.pfa.webapp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LigneCommande {

	@EmbeddedId
	@JsonIgnore
	private IdLigneCommande pk;

	private int quantite;


	@ManyToOne
	@JoinColumn(name = "id_avoir")
	private Avoir avoir;

	@Transient
	@JsonIgnore
	private Panier panier;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id_produit") private Produit produit;
	 */

	@ManyToOne
	@JoinColumn(name = "id_commandeCooperative")
	private CommandeCooperative commandeCooperative;

	public LigneCommande() {
		super();
		this.pk = new IdLigneCommande();
	}

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="id_commandeClient") private CommandeClient commandeClient;
	 */

	public LigneCommande(Produit produit,CommandeClient commande, int qte) {
		super();
		this.pk = new IdLigneCommande(produit, commande);
		this.quantite = qte;
	}

	public Avoir getAvoir() {
		return avoir;
	}
	
	@Transient
	public CommandeClient getCommandeClient() {
		return pk.getCommandeClient();
	}

	public CommandeCooperative getCommandeCooperative() {
		return commandeCooperative;
	}

	public Panier getPanier() {
		return panier;
	}

	public IdLigneCommande getPk() {
		return pk;
	}

	@Transient
	public Produit getProduit() {
		return pk.getProduit();
	}

	public int getQuantite() {
		return quantite;
	}

	public void setAvoir(Avoir avoir) {
		this.avoir = avoir;
	}

	public void setCommandeClient(CommandeClient commandeClient) {
		pk.setCommandeClient(commandeClient);
	}

	public void setCommandeCooperative(CommandeCooperative commandeCooperative) {
		this.commandeCooperative = commandeCooperative;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public void setPk(IdLigneCommande pk) {
		this.pk = pk;
	}

	public void setProduit(Produit produit) {
		pk.setProduit(produit);
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}