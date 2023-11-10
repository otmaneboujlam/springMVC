package com.diginamic.BestiolesMVC.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diginamic.BestiolesMVC.entity.Animal;
import com.diginamic.BestiolesMVC.repository.AnimalRepository;
import com.diginamic.BestiolesMVC.repository.SpeciesRepository;

import jakarta.validation.Valid;

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
		model.addAttribute("animal", new Animal());
		model.addAttribute("species", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
		return "animal_create";
	}
	
	@GetMapping("/update/{id}")
	public String updateAnimal(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("animal", animalRepository.findById(id));
		model.addAttribute("species", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
		return "animal_update";
	}
	
	@PostMapping("")
	public String createOrUpdateAnimal(@Valid Animal animal, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("species", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
			return animal.getId() == null ? "animal_create" : "animal_update";
		}
		animalRepository.save(animal);
		return "redirect:/animal";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAnimal(@PathVariable("id") Integer id) {
		Optional<Animal> animalToDelete = animalRepository.findById(id);
		animalToDelete.ifPresent(animal -> animalRepository.delete(animal));
		return "redirect:/animal";
	}
	
}
