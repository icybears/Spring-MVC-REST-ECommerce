package ma.pfa.webapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class CommandeClient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commandeClient")
	private int id;
	
	@CreationTimestamp
	private Date dateCreation;


	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client ;

	@ManyToOne
	@JoinColumn(name="id_etat")
	private Etat etat;

	@ManyToOne
	@JoinColumn(name="id_factureClient")
	private FactureClient factureClient;

	@OneToMany(mappedBy="pk.commandeClient",
			fetch=FetchType.LAZY,
			cascade = CascadeType.ALL,
	        orphanRemoval = true)
	@JsonIgnore
	private Set<LigneCommande> ligneCommandes = new HashSet<LigneCommande>();

	public CommandeClient() {
		super();
	}

	public Client getClient() {
		return client;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public Etat getEtat() {
		return etat;
	}

	public FactureClient getFactureClient() {
		return factureClient;
	}

	public int getId() {
		return id;
	}

	public Set<LigneCommande> getLigneCommandes() {
		return ligneCommandes;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
	public void setFactureClient(FactureClient factureClient) {
		this.factureClient = factureClient;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setLigneCommandes(Set<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}
	
	public void addLigneCommande(LigneCommande lc) {
		this.ligneCommandes.add(lc);
		lc.setCommandeClient(this);
	}
	
	public void removeLigneCommande(LigneCommande lc) {
		this.ligneCommandes.remove(lc);
		lc.setCommandeClient(null);
	}
	
	
}