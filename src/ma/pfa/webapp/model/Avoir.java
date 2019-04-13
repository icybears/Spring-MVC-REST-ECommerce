package ma.pfa.webapp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Avoir {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_avoir")

	private int id;

	private int quantite;

	@CreationTimestamp
	private Date date;
	
	@OneToMany(mappedBy = "avoir")
	public Set<LigneCommande> ligneCommande = new HashSet<>();

	public Avoir() {
		super();
	}

	public Avoir(int qte) {
		super();
		this.quantite = qte;
	}
	
	public void addLigneCommande(LigneCommande lc) {
		this.ligneCommande.add(lc);
		lc.setAvoir(this);
	}

	public Date getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public Set<LigneCommande> getLigneCommande() {
		return ligneCommande;
	}

	public int getQuantite() {
		return quantite;
	}

	public void removeLigneCommande(LigneCommande lc) {
		this.ligneCommande.remove(lc);
		lc.setAvoir(null);
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setLigneCommande(Set<LigneCommande> ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}