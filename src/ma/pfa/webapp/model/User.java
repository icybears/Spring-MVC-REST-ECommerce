package ma.pfa.webapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserRole userRole;

	private String userName;

	private String password;
	
	public User() {
		super();
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
}