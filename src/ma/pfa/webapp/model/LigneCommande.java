
package ma.pfa.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class LigneCommande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ligneCommande")
	private int id;
	private int quantite;

	@ManyToOne
	@JoinColumn(name = "id_produit")
	private Produit produit;

	@ManyToOne
	@JoinColumn(name = "id_avoir")
	private Avoir avoir;
	
	@Transient
	private Panier panier;


	@ManyToOne
	@JoinColumn(name="id_commandeClient")
	private CommandeClient commandeClient;


	@ManyToOne
	@JoinColumn(name="id_commandeCooperative")
	private CommandeCooperative commandeCooperative;


	public LigneCommande() {
		super();
	}


	public LigneCommande(int quantite, Produit produit) {
		super();
		this.quantite = quantite;
		this.produit = produit;
	}


	public Avoir getAvoir() {
		return avoir;
	}


	public CommandeClient getCommandeClient() {
		return commandeClient;
	}


	public CommandeCooperative getCommandeCooperative() {
		return commandeCooperative;
	}


	public int getId() {
		return id;
	}


	public Panier getPanier() {
		return panier;
	}


	public Produit getProduit() {
		return produit;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setAvoir(Avoir avoir) {
		this.avoir = avoir;
	}


	public void setCommandeClient(CommandeClient commandeClient) {
		this.commandeClient = commandeClient;
	}


	public void setCommandeCooperative(CommandeCooperative commandeCooperative) {
		this.commandeCooperative = commandeCooperative;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setPanier(Panier panier) {
		this.panier = panier;
	}
    
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}