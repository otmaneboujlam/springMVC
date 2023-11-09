package com.diginamic.BestiolesMVC.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diginamic.BestiolesMVC.entity.Species;


@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer>{

	Optional<Species> findFirstByCommonName(String commonName);
	
	List<Species> findByLatinNameContainsIgnoreCase(String latinName);
	
	@Query("from Species")
	List<Species> findAllAsc(Sort sort);
	
	@Query("from Species where commonName LIKE :commonName")
	List<Species> findAllLike(@Param("commonName") String commonName);
	
}
