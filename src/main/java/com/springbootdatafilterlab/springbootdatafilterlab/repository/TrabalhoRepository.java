package com.springbootdatafilterlab.springbootdatafilterlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springbootdatafilterlab.springbootdatafilterlab.domain.Trabalho;

@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long>, JpaSpecificationExecutor<Trabalho> {

}
