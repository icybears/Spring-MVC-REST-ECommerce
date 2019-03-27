
package ma.pfa.webapp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class LigneCommande {

	@EmbeddedId
	private IdLigneCommande pk;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_ligneCommande")
//	private int id;

	private int quantite;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id_produit") private Produit produit;
	 */

	@ManyToOne
	@JoinColumn(name = "id_avoir")
	private Avoir avoir;

	@Transient
	private Panier panier;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="id_commandeClient") private CommandeClient commandeClient;
	 */

	@ManyToOne
	@JoinColumn(name = "id_commandeCooperative")
	private CommandeCooperative commandeCooperative;

	public LigneCommande() {
		super();
		this.pk = new IdLigneCommande();
	}

	public Avoir getAvoir() {
		return avoir;
	}

	public CommandeCooperative getCommandeCooperative() {
		return commandeCooperative;
	}

	public Panier getPanier() {
		return panier;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setAvoir(Avoir avoir) {
		this.avoir = avoir;
	}

	public void setCommandeCooperative(CommandeCooperative commandeCooperative) {
		this.commandeCooperative = commandeCooperative;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Transient
	public Produit getProduit() {
		return pk.getProduit();
	}

	@Transient
	public CommandeClient getCommandeClient() {
		return pk.getCommandeClient();
	}

	public void setProduit(Produit produit) {
		pk.setProduit(produit);
	}

	public void setCommande(CommandeClient commandeClient) {
		pk.setCommandeClient(commandeClient);
	}
}