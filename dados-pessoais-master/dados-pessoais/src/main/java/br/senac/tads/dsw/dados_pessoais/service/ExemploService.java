// hash-identificacao: 86a46359b1d20393d6905f1c15cb353eb2c3c8c8f03799f4bfd1df812be0bf4d
/*
 * Arquivo gerado automaticamente pelo TADS Spring Initializr customizado (versão didática).
 * ATENÇÃO: Este arquivo NÃO é gerado pelo Spring Initializr oficial.
 */
package br.senac.tads.dsw.dados_pessoais.service;

import br.senac.tads.dsw.dados_pessoais.dto.ExemploDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExemploService {

	// tipo é chave única no formato String: cada objeto no map (valor) é identificado por seu tipo único
	private final Map<String, ExemploDto> dados = new ConcurrentHashMap<>();
	private final AtomicInteger counter = new AtomicInteger(0);

	public ExemploService() {
		dados.put("hello", new ExemploDto(counter.incrementAndGet(), "hello", "Hello Spring Boot"));
		dados.put("exemplo", new ExemploDto(counter.incrementAndGet(), "exemplo", "Exemplo de dados"));
		dados.put("teste", new ExemploDto(counter.incrementAndGet(), "teste", "Teste"));
		dados.put("demo", new ExemploDto(counter.incrementAndGet(), "demo", "Dados da aplicação de demonstração"));
	}

	public List<ExemploDto> findAll() {
		return List.copyOf(dados.values());
	}

	public Optional<ExemploDto> findByTipo(String tipo) {
		return Optional.ofNullable(dados.get(tipo));
	}

	public ExemploDto addNew(ExemploDto dto) {
		ExemploDto novo = new ExemploDto(counter.incrementAndGet(), dto.getTipo(), dto.getMensagem());
		dados.put(novo.getTipo(), novo);
		return novo;
	}

}
