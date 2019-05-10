package ma.pfa.webapp.message.request;

import java.util.Set;


public class SignUpForm {
  
    
    private String username;

    private String email;
    
    private String nom;
    
    private String prenom;
    
    private String adresse;
    
    private String telephone;
    
    private Set<String> role;

    private String password;

    
    public String getAdresse() {
		return adresse;
	}

	public String getEmail() {
        return email;
    }

	public String getNom() {
		return nom;
	}

	public String getPassword() {
        return password;
    }

	public String getPrenom() {
		return prenom;
	}

	public Set<String> getRole() {
    	return this.role;
    }

	public String getTelephone() {
		return telephone;
	}

	public String getUsername() {
        return username;
    }

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
		this.nom = nom;
	}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

    public void setRole(Set<String> role) {
    	this.role = role;
    }
    
    public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    
    public void setUsername(String username) {
        this.username = username;
    }
}