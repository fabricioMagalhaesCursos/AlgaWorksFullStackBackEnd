package com.fabriciomagalhaes.fabriciomagalhaesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabriciomagalhaes.fabriciomagalhaesapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
