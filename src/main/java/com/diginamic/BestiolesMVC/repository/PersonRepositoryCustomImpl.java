package com.diginamic.BestiolesMVC.repository;

import com.diginamic.BestiolesMVC.entity.Person;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void deletePersonHasNoAnimal() {

		em.createQuery("delete from Person p where p.animals is empty").executeUpdate();
	
	}

	@Override
	public void createPersons(Integer nombrePerson) {
		Faker faker = new Faker();
		for(int i=0; i<nombrePerson; i++) {
			Person p = new Person();
			p.setFirstname(faker.name().firstName());
			p.setLastname(faker.name().lastName());
			p.setAge(faker.number().numberBetween(1, 100));
			em.persist(p);
		}
		
	}

}
