package ma.pfa.webapp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Etat {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_etat")

	private int id;

	private String nom;

	private String description;
	

	public Etat() {
		super();
	}


	public Etat(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;

	}

    
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

	

}