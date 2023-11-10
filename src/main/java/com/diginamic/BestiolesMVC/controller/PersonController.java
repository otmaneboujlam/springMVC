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

import com.diginamic.BestiolesMVC.entity.Person;
import com.diginamic.BestiolesMVC.repository.AnimalRepository;
import com.diginamic.BestiolesMVC.repository.PersonRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@GetMapping("")
	public String listPerson(Model model) {
		List<Person> persons = personRepository.findAll();
		model.addAttribute("persons", persons);
		return "person_list";
	}
	
	@GetMapping("/{id}")
	public String idPerson(@PathVariable("id") Integer id, Model model) {
		Optional<Person> person = personRepository.findById(id);
		if(person.isPresent()) {
			model.addAttribute("person", person.get());
			return "person_id";
		}
		return "error";
	}
	
	@GetMapping("/create")
	public String createPerson(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("animals", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
		return "person_create";
	}
	
	@GetMapping("/update/{id}")
	public String updatePerson(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("person", personRepository.findById(id));
		model.addAttribute("animals", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
		return "person_update";
	}
	
	@PostMapping("")
	public String createOrUpdatePerson(@Valid Person person, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("animals", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
			return person.getId() == null ? "person_create" : "person_update";
		}
		personRepository.save(person);
		return "redirect:/person";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePerson(@PathVariable("id") Integer id) {
		Optional<Person> personToDelete = personRepository.findById(id);
		personToDelete.ifPresent(person -> personRepository.delete(person));
		return "redirect:/person";
	}

}
