package com.fabriciomagalhaes.fabriciomagalhaesapi.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabriciomagalhaes.fabriciomagalhaesapi.model.Lancamento;
import com.fabriciomagalhaes.fabriciomagalhaesapi.model.Pessoa;
import com.fabriciomagalhaes.fabriciomagalhaesapi.repository.LancamentoRepository;
import com.fabriciomagalhaes.fabriciomagalhaesapi.repository.PessoaRepository;
import com.fabriciomagalhaes.fabriciomagalhaesapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(@Valid Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
			
		}
		return lancamentoRepository.save(lancamento);
	}

}
