package ma.pfa.webapp.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produit")
	private int id;
	private String nom;
	private String description;
	private double prix;

	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;

//	@ManyToMany(mappedBy = "produits")
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(name="produit_matiere", 
	joinColumns = @JoinColumn(name="id_produit"),
	inverseJoinColumns = @JoinColumn(name="id_matierePremiere"))
	@JsonIgnore
	private Set<MatierePremiere> matieresPremieres = new HashSet<MatierePremiere>();

//	 @OneToMany(mappedBy="pk.produit")
//	private Set<LigneCommande> ligneCommandes = new HashSet<LigneCommande>();

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cooperative")
	private Cooperative cooperative;

	public Produit() {
		super();
	}

	public Produit(String nom, double prix) {
		super();
		this.nom = nom;
		this.prix = prix;
	}

	public void addMatierePremiere(MatierePremiere mp) {
		matieresPremieres.add(mp);
		mp.getProduits().add(this);
	}
	
	public void removeMatierePremiere(MatierePremiere mp) {
		matieresPremieres.remove(mp);
		mp.getProduits().remove(this);
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

//	@Override
//	public boolean equals(Object o) {
//		Produit prod = (Produit) o;
//		return getId() == prod.getId();
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(getId());
//	}

}