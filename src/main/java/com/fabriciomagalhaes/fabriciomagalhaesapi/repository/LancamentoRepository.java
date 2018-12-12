package com.fabriciomagalhaes.fabriciomagalhaesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabriciomagalhaes.fabriciomagalhaesapi.model.Lancamento;
import com.fabriciomagalhaes.fabriciomagalhaesapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
