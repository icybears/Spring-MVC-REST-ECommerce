package ma.pfa.webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_produit")
   private int id;
   private String nom;
   private String description;
   private double prix;
   
   @ManyToOne
   @JoinColumn(name="id_categorie")
   private Categorie categorie ;
   
   @ManyToMany(mappedBy="produits")
   private Set<MatierePremiere> matieresPremieres = new HashSet<MatierePremiere>();
   
   
   @ManyToOne
   @JoinColumn(name="id_cooperative")
   Cooperative cooperative;


public Produit() {
	super();
}


public Produit(String nom, double prix) {
	super();
	this.nom = nom;
	this.prix = prix;
}


public Categorie getCategorie() {
	return categorie;
}


public Cooperative getCooperative() {
	return cooperative;
}


public String getDescription() {
	return description;
}


public int getId() {
	return id;
}


public Set<MatierePremiere> getMatieresPremieres() {
	return matieresPremieres;
}


public String getNom() {
	return nom;
}


public double getPrix() {
	return prix;
}


public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}


public void setCooperative(Cooperative cooperative) {
	this.cooperative = cooperative;
}


public void setDescription(String description) {
	this.description = description;
}


public void setId(int id) {
	this.id = id;
}


public void setMatieresPremieres(Set<MatierePremiere> matieresPremieres) {
	this.matieresPremieres = matieresPremieres;
}


public void setNom(String nom) {
	this.nom = nom;
}


public void setPrix(double prix) {
	this.prix = prix;
}
   
   

}