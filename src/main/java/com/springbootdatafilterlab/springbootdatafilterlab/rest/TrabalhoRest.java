package com.springbootdatafilterlab.springbootdatafilterlab.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootdatafilterlab.springbootdatafilterlab.domain.Trabalho;
import com.springbootdatafilterlab.springbootdatafilterlab.repository.TrabalhoRepository;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/trabalhos")
public class TrabalhoRest {

	@Autowired
	private TrabalhoRepository trabalhoRepository;

	@GetMapping
	public Page<Trabalho> findCustomers(
			@Join(path = "pessoas", alias = "p") @And({ @Spec(path = "cargo", spec = Equal.class),
					@Spec(path = "salario", spec = Equal.class), @Spec(path = "funcao", spec = Equal.class),
					@Spec(path = "p.nome", params = "pnome", spec = Equal.class) }) Specification<Trabalho> trabalhoSpec,
			Pageable pageable) {
		return this.trabalhoRepository.findAll(trabalhoSpec, pageable);
	}

	@PostMapping
	public Trabalho save(@RequestBody Trabalho trabalho) {
		return this.trabalhoRepository.save(trabalho);
	}

}
