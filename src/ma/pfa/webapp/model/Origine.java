package ma.pfa.webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Origine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_origine")
   private int id;
   private String nom;
   private String description;
   
   @JsonIgnore
   @OneToMany(mappedBy="origine")
  private Set<MatierePremiere> matierePremieres=new HashSet<>();
   
 //Constructors


public Origine() {
	super();
}

/**
 * @param nom
 * @param description
 * @param matierePremieres
 */
public Origine(String nom, String description, Set<MatierePremiere> matierePremieres) {
	super();
	this.nom = nom;
	this.description = description;
	this.matierePremieres = matierePremieres;
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

public Set<MatierePremiere> getMatierePremieres() {
	return matierePremieres;
}

public void setMatierePremieres(Set<MatierePremiere> matierePremieres) {
	this.matierePremieres = matierePremieres;
}
   
}