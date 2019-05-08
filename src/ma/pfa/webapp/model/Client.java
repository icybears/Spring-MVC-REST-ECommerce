package ma.pfa.webapp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
	
	@OneToMany(mappedBy="client")
	@JsonIgnore
	Set<CommandeClient> commandeClient =new HashSet<>();

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
	
	//Constructors

	public String getAdresse() {
		return adresse;
	}

	public Set<CommandeClient> getCommandeClient() {
		return commandeClient;
	}
	
	
	public String getEmail() {
		return email;
	}

	//getters and setters
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getTel() {
		return tel;
	}

	public User getUser() {
		return user;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setCommandeClient(Set<CommandeClient> commandeClient) {
		this.commandeClient = commandeClient;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}