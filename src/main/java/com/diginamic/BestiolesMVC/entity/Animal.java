package com.diginamic.BestiolesMVC.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private Integer id;
	
	@Column(length = 50, columnDefinition = "varchar(50) default NULL")
	private String color;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 255, nullable = false)
	private String sex;
	
	@ManyToOne
	private Species species;
	
	@ManyToMany(mappedBy = "animals", cascade = CascadeType.ALL)
	private List<Person> persons;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", color=" + color + ", name=" + name + ", sex=" + sex + "]";
	}
	
}
