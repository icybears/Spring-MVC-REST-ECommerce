package ma.pfa.webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;








@Entity
public class Cooperative {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cooperative")
	private int id;

	private String nom;
	
	private String responsable;

	private String adresse;

	private String tel;

	private String email;

	private String description;

	
//	@OneToMany(mappedBy = "cooperative",cascade = javax.persistence.CascadeType.REMOVE,
//	        orphanRemoval = true)
	
	@OneToMany(mappedBy = "cooperative",cascade = CascadeType.ALL,
    orphanRemoval = true)
	private Set<Produit> produits = new HashSet<Produit>();

	public Cooperative() {
		super();
	}

	public Cooperative(String nom) {
		super();
		this.setNom(nom);
	}
	
	public Cooperative(String nom,String responsable, String description) {
		super();
		this.responsable = responsable;
		this.description = description;
	}
	
	public void addProduit(Produit prod) {
        produits.add(prod);
        prod.setCooperative(this);
    }
	
	public void removeProduit(Produit prod) {
        produits.remove(prod);
        prod.setCooperative(null);
    }

	public String getAdresse() {
		return adresse;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public Set<Produit> getProduits() {
		return produits;
	}

	public String getResponsable() {
		return responsable;
	}

	public String getTel() {
		return tel;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}