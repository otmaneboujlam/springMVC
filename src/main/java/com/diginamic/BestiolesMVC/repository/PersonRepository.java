package com.diginamic.BestiolesMVC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diginamic.BestiolesMVC.entity.Animal;
import com.diginamic.BestiolesMVC.entity.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom {

	List<Person> findByFirstnameOrLastname(String firstname, String lastname);
	
	List<Person> findByAgeGreaterThan(Integer age);
	
	@Query("from Person where age > :ageMin and age < :ageMax")
	List<Person> findAllByAgeIn(@Param("ageMin") Integer ageMin, @Param("ageMax") Integer ageMax);
	
	@Query("from Person p where :animal MEMBER OF p.animals")
	List<Person> findAllByAnimal(@Param("animal") Animal animal);
}
