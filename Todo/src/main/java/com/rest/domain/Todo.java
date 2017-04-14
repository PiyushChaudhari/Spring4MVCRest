package com.rest.domain;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.rest.base.presistance.BaseDomain;

@Entity
@Table(name = "todo")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Todo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "{com.rest.domain.Todo.name.NotNull}")
	@NotEmpty(message = "{com.rest.domain.Todo.name.NotEmpty}")
	@NotBlank(message = "{com.rest.domain.Todo.name.NotBlank}")
	@Size(min = 1, max = 10,message = "{com.rest.domain.Todo.name.size.notMatch}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
