package de.liu.mylibrary.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "user_name")
	@NotEmpty
	private String userName;

	@Column(name = "password")
	@NotEmpty
	private String password;

	@Column(name = "first_name")
	@NotEmpty
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "role")
	@Enumerated(value = EnumType.STRING)
	private Role role;

	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private Status status;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<BorrowHistory> borrowHistories;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<BorrowHistory> getBorrowHistories() {
		return borrowHistories;
	}

	public void setBorrowHistories(Set<BorrowHistory> borrowHistories) {
		this.borrowHistories = borrowHistories;
	}

}
