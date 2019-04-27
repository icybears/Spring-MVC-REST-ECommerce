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

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class CommandeCooperative {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commandeCooperative")
	private int id;
	
	@CreationTimestamp
	private Date dateCreation;

	private int total;

	@OneToMany(mappedBy="commandeCooperative")
	private Set<LigneCommande> lignesDeCommandes = new HashSet<LigneCommande>();

	@ManyToOne
	@JoinColumn(name="id_etat")
	private Etat etat;

	public CommandeCooperative() {
		super();
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public Etat getEtat() {
		return etat;
	}

	public int getId() {
		return id;
	}

	public Set<LigneCommande> getLignesDeCommandes() {
		return lignesDeCommandes;
	}

	public int getTotal() {
		return total;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setLignesDeCommandes(Set<LigneCommande> lignesDeCommandes) {
		this.lignesDeCommandes = lignesDeCommandes;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}

}