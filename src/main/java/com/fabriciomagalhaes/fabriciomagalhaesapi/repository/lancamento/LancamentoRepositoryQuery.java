package com.fabriciomagalhaes.fabriciomagalhaesapi.repository.lancamento;

import java.util.List;

import com.fabriciomagalhaes.fabriciomagalhaesapi.model.Lancamento;
import com.fabriciomagalhaes.fabriciomagalhaesapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
