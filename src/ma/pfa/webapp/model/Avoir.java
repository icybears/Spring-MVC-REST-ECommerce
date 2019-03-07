package ma.pfa.webapp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Avoir {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_avoir")

	private int id;

	private int quantite;

	private Date date;

	@OneToMany(mappedBy = "avoir")
	public Set<LigneCommande> ligneDeCommande = new HashSet<>();
}