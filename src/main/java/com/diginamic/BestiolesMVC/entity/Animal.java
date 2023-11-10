package com.diginamic.BestiolesMVC.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private Integer id;
	
	@Column(length = 50, columnDefinition = "varchar(50) default NULL")
	@Size(max = 50)
	private String color;
	
	@Column(length = 50, nullable = false)
	@NotBlank
	@Size(max = 50)
	private String name;
	
	@Column(length = 255, nullable = false)
	@NotBlank
	@Size(max = 255)
	private String sex;
	
	@ManyToOne
	@NotNull
	private Species species;
	
	@ManyToMany(mappedBy = "animals")
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
