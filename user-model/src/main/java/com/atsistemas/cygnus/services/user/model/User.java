package com.atsistemas.cygnus.services.user.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2118159221476629339L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	public User() {
	}

	public User(String name, Long id) {
		this.id = id;
		this.name = name;
	}

	public User(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
