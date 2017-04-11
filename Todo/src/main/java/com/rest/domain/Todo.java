package com.rest.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.rest.base.presistance.BaseDomain;

@Entity
@Table(name = "todo")
public class Todo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
