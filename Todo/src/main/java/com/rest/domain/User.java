package com.rest.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rest.base.presistance.BaseDomain;

@Entity
@Table(name = "User")
public class User extends BaseDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "{com.rest.domain.User.firstName.NotNull}")
	@NotEmpty(message = "{com.rest.domain.User.firstName.NotEmpty}")
	@NotBlank(message = "{com.rest.domain.User.firstName.NotBlank}")
	@Size(min = 1, max = 15, message = "com.rest.domain.User.firstName.size.notMatch}")
	private String firstName;

	@NotNull(message = "{com.rest.domain.User.lastName.NotNull}")
	@NotEmpty(message = "{com.rest.domain.User.lastName.NotEmpty}")
	@NotBlank(message = "{com.rest.domain.User.lastName.NotBlank}")
	@Size(min = 1, max = 15, message = "com.rest.domain.User.lastName.size.notMatch}")
	private String lastName;

	@NotNull(message = "{com.rest.domain.User.email.NotNull}")
	@NotEmpty(message = "{com.rest.domain.User.email.NotEmpty}")
	@NotBlank(message = "{com.rest.domain.User.email.NotBlank}")
	@Email(message = "{com.rest.domain.User.email.invalid}")
	private String email;

	@NotNull(message = "{com.rest.domain.User.password.NotNull}")
	@NotEmpty(message = "{com.rest.domain.User.password.NotEmpty}")
	@NotBlank(message = "{com.rest.domain.User.password.NotBlank}")
	private String password;

	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@Column(nullable = true)
	private Set<Todo> todoList;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(Set<Todo> todoList) {
		this.todoList = todoList;
	}

}
