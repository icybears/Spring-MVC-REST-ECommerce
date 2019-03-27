package ma.pfa.webapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CommandeClient implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commandeClient")

	private int id;

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

	@OneToMany(mappedBy="pk.commandeClient",fetch=FetchType.LAZY)
	private Set<LigneCommande> ligneCommandes = new HashSet<LigneCommande>();
	
	public Client getClient() {
		return client;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
	
}