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

import com.springbootdatafilterlab.springbootdatafilterlab.domain.Pessoa;
import com.springbootdatafilterlab.springbootdatafilterlab.repository.PessoaRepository;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaRest {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public Page<Pessoa> findCustomers(
			@Join(path = "trabalho", alias = "t") @And({ @Spec(path = "nome", spec = Equal.class),
					@Spec(path = "login", spec = Equal.class), @Spec(path = "salario", spec = Equal.class),
					@Spec(path = "t.cargo", params = "tcargo", spec = Equal.class) }) Specification<Pessoa> pessoaSpec,
			Pageable pageable) {
		return pessoaRepository.findAll(pessoaSpec, pageable);
	}

	@PostMapping
	public Pessoa save(@RequestBody Pessoa pessoa) {
		System.out.println(pessoa);
		return this.pessoaRepository.save(pessoa);
	}

}
