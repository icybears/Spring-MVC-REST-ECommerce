package ma.pfa.webapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {

	@Id
	private Long roleId;
	
	@OneToOne
    @JoinColumn
    @MapsId
    private User user;
	

	private String role;

	public UserRole() {
		super();
	}

	public String getRole() {
		return role;
	}

	public Long getRoleId() {
		return roleId;
	}

	public User getUser() {
		return user;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
