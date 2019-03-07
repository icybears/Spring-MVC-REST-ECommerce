package ma.pfa.webapp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_client")

	private int id;

	private String nom;

	private String prenom;

	private String adresse;

	private String tel;

	private String email;
	
	@OneToMany(mappedBy="client")
	Set<CommandeClient> commandeClient =new HashSet<>();
	
	//Constructors

	/**
	 * 
	 */
	public Client() {
		super();
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param tel
	 * @param email
	 * @param commandeClient
	 */
	public Client(String nom, String prenom, String adresse, String tel, String email,
			Set<CommandeClient> commandeClient) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.commandeClient = commandeClient;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<CommandeClient> getCommandeClient() {
		return commandeClient;
	}

	public void setCommandeClient(Set<CommandeClient> commandeClient) {
		this.commandeClient = commandeClient;
	}
	
}