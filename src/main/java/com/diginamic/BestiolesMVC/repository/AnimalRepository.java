package com.diginamic.BestiolesMVC.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diginamic.BestiolesMVC.entity.Animal;
import com.diginamic.BestiolesMVC.entity.Species;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{
	
	List<Animal> findBySpecies(Species species);
	
	List<Animal> findByColorIn(List<String> colors);
	
	@Query("select count(*) from Animal a where a.sex = :sex")
	Optional<Integer> countAnimalBySex(@Param("sex") String sex);
	
	@Query("SELECT CASE WHEN SIZE(a.persons) > 0 THEN true ELSE false END FROM Animal a WHERE a = :animal")
	Boolean hasOwner(@Param("animal") Animal animal);
	
}