package com.fabriciomagalhaes.fabriciomagalhaesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabriciomagalhaes.fabriciomagalhaesapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
