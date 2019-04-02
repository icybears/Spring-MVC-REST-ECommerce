package ma.pfa.webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class MatierePremiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_matierePremiere")
	private int id;
	private String nom;
	private String description;

	@ManyToMany
	@JoinTable(name="produit_matiere", joinColumns=@JoinColumn(name="id_matierePremiere"),
	inverseJoinColumns=@JoinColumn(name="id_produit"))
	private Set<Produit> produits = new HashSet<Produit>();
	
	@ManyToOne
	@JoinColumn(name="id_origine")
	private Origine origine;

	
	public MatierePremiere() {
		super();
	}

	public MatierePremiere(String nom) {
		super();
		this.nom = nom;

	}
	
	public MatierePremiere(String nom, String description, Origine origine) {
		super();
		this.nom = nom;
		this.description = description;
		this.origine = origine;
		
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public Origine getOrigine() {
		return origine;
	}

	

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setOrigine(Origine origine) {
		this.origine = origine;
	}

}