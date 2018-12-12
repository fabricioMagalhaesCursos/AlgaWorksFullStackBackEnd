package com.fabriciomagalhaes.fabriciomagalhaesapi.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fabriciomagalhaes.fabriciomagalhaesapi.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		HttpServletResponse response = event.getResponse();
		
		adicionarHeaderLocation(event, response);
	}

	private void adicionarHeaderLocation(RecursoCriadoEvent event, HttpServletResponse response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(event.getCodigo()).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
	}

}
