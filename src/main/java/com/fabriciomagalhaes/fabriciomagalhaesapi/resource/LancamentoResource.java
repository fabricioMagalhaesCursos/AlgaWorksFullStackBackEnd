package com.fabriciomagalhaes.fabriciomagalhaesapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabriciomagalhaes.fabriciomagalhaesapi.event.RecursoCriadoEvent;
import com.fabriciomagalhaes.fabriciomagalhaesapi.exceptionhandler.FabricioExceptionHandler.Erro;
import com.fabriciomagalhaes.fabriciomagalhaesapi.model.Lancamento;
import com.fabriciomagalhaes.fabriciomagalhaesapi.repository.LancamentoRepository;
import com.fabriciomagalhaes.fabriciomagalhaesapi.repository.filter.LancamentoFilter;
import com.fabriciomagalhaes.fabriciomagalhaesapi.service.LancamentoService;
import com.fabriciomagalhaes.fabriciomagalhaesapi.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private LancamentoService lancamentoService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
		return lancamentoRepository.filtrar(lancamentoFilter);
	}

	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalva = lancamentoService.salvar(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
		Lancamento cat = lancamentoRepository.findById(codigo).orElse(null);
		return cat != null ? ResponseEntity.ok(cat) : ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}
