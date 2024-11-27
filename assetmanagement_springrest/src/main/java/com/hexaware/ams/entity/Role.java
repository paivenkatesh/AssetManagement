package com.hexaware.ams.entity;

/*
 * @Author: Venkatesh Pai
 * Date: 16-11-24
 * Description: Role Entity with Mappings
 * Role Entity
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;

	@Column(name = "role_name", unique = true, nullable = false)
	@NotNull(message = "Role name cannot be null")
	@Size(min = 2, max = 50, message = "Role name must be between 2 and 50 charachters")
	private String roleName;

	public Role() {
		super();
	}

	public Role(int roleId,
			@NotNull(message = "Role name cannot be null") @Size(min = 2, max = 50, message = "Role name must be between 2 and 50 charachters") String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

}
