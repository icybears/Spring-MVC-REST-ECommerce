package ma.pfa.webapp.model;

import java.util.Date;
import java.util.HashSet;
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
public class CommandeCooperative {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commandeCooperative")
	
		
	private int id;
	
	private Date dateCreation;

	private int total;
	
	@OneToMany(mappedBy="commandeCooperative")
	private Set<LigneCommande> lignesDeCommandes = new HashSet<LigneCommande>();
	
	@ManyToOne
	@JoinColumn(name="id_etat")
	private Etat etat;

}