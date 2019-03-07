package ma.pfa.webapp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categorie")
  
   private int id;
  
   private String nom;
 
   private String description;
   
   @OneToMany(mappedBy="categorie")
   private   Set<Produit> produits=new HashSet<>();

   //Constructors
/**
 * @param nom
 * @param description
 * @param produits
 */
public Categorie(String nom, String description, Set<Produit> produits) {
	super();
	this.nom = nom;
	this.description = description;
	this.produits = produits;
}

/**
 * 
 */
public Categorie() {
	super();
}

//getters and setters

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
	return produits;
}

public void setProduits(Set<Produit> produits) {
	this.produits = produits;
}

}