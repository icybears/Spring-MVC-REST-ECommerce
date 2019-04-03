package ma.pfa.webapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categorie")
	private int id;

	private String nom;

	private String description;

	@OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER)
	private Set<Produit> produits = new HashSet<Produit>();
	

	public Categorie() {
		super();
	}

	public Categorie(String nom, String description, Set<Produit> produits) {
		super();
		this.nom = nom;
		this.description = description;
		this.produits = produits;
	}

//	public void addProduit(Produit prod) {
//        produits.add(prod);
//        prod.setCategorie(this);
//    }
// 
//	public void removeProduit(Produit prod) {
//        produits.remove(prod);
//        prod.setCategorie(null);
//    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Produit> getProduits() {
		return this.produits;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

}