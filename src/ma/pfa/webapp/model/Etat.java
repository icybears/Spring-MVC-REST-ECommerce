package ma.pfa.webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Etat {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_etat")

	private int id;

	private String nom;

	private String description;
	
	@OneToMany(mappedBy="etat")
	Set<CommandeClient> commandeClient =new HashSet<>();
	
	@OneToMany(mappedBy="etat")
	Set<CommandeCooperative> commandeCooperative =new HashSet<>();
	
	//Constructor

	/**
	 * 
	 */
	public Etat() {
		super();
	}

	/**
	 * @param nom
	 * @param description
	 * @param commandeClient
	 * @param commandeCooperative
	 */
	public Etat(String nom, String description, Set<CommandeClient> commandeClient,
			Set<CommandeCooperative> commandeCooperative) {
		super();
		this.nom = nom;
		this.description = description;
		this.commandeClient = commandeClient;
		this.commandeCooperative = commandeCooperative;
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

	public Set<CommandeClient> getCommandeClient() {
		return commandeClient;
	}

	public void setCommandeClient(Set<CommandeClient> commandeClient) {
		this.commandeClient = commandeClient;
	}

	public Set<CommandeCooperative> getCommandeCooperative() {
		return commandeCooperative;
	}

	public void setCommandeCooperative(Set<CommandeCooperative> commandeCooperative) {
		this.commandeCooperative = commandeCooperative;
	}
	
	

}