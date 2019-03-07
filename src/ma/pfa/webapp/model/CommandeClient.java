package ma.pfa.webapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CommandeClient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commandeClient")

	private int id;

	private Date dateCreation;

	private double total;
	
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client ;
	
	@ManyToOne
	@JoinColumn(name="id_etat")
	private Etat etat;
	
	@ManyToOne
	@JoinColumn(name="id_factureClient")
	private FactureClient factureClient;
	
	@OneToMany(mappedBy="commandeClient")
	private Set<LigneCommande> lignesDeCommandes= new HashSet<LigneCommande>();
	
}