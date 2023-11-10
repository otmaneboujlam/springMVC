package com.diginamic.BestiolesMVC.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diginamic.BestiolesMVC.entity.Species;
import com.diginamic.BestiolesMVC.repository.SpeciesRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/species")
public class SpeciesController {

	@Autowired
	private SpeciesRepository speciesRepository;
	
	@GetMapping("")
	public String listSpecies(Model model) {
		List<Species> speciess = speciesRepository.findAll();
		model.addAttribute("speciess", speciess);
		return "species_list";
	}
	
	@GetMapping("/{id}")
	public String idSpecies(@PathVariable("id") Integer id, Model model) {
		Optional<Species> species = speciesRepository.findById(id);
		if(species.isPresent()) {
			model.addAttribute("species", species.get());
			return "species_id";
		}
		return "error";
	}
	
	@GetMapping("/create")
	public String createSpecies(Model model) {
		model.addAttribute("species", new Species());
		return "species_create";
	}
	
	@GetMapping("/update/{id}")
	public String updateSpecies(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("species", speciesRepository.findById(id));
		return "species_update";
	}
	
	@PostMapping("")
	public String createOrUpdateSpecies(@Valid Species species, BindingResult result) {
		if(result.hasErrors()) {
			return species.getId() == null ? "species_create" : "species_update";
		}
		speciesRepository.save(species);
		return "redirect:/species";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteSpecies(@PathVariable("id") Integer id, Model model) {
		Optional<Species> speciesToDelete = speciesRepository.findById(id);
		if(speciesToDelete.isPresent()) {
			try {
				speciesRepository.delete(speciesToDelete.get());
			} catch (Exception e) {
				model.addAttribute("error", e);
				return "errorDelete";
			}
			
		}
		return "redirect:/species";
	}
	
}
