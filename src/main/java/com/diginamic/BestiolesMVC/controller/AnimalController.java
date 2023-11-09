package com.diginamic.BestiolesMVC.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diginamic.BestiolesMVC.entity.Animal;
import com.diginamic.BestiolesMVC.repository.AnimalRepository;
import com.diginamic.BestiolesMVC.repository.SpeciesRepository;

@Controller
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private SpeciesRepository speciesRepository;
	
	@GetMapping("")
	public String listAnimal(Model model) {
		List<Animal> animals = animalRepository.findAll();
		model.addAttribute("animals", animals);
		return "animal_list";
	}
	
	@GetMapping("/{id}")
	public String idAnimal(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animal = animalRepository.findById(id);
		if(animal.isPresent()) {
			model.addAttribute("animal", animal.get());
			return "animal_id";
		}
		return "error";
	}
	
	@GetMapping("/create")
	public String createAnimal(Model model) {
		model.addAttribute("newAnimal", new Animal());
		model.addAttribute("species", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
		return "animal_create";
	}
	
}
