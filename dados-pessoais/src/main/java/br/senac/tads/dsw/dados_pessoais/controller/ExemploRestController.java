// hash-identificacao: 86a46359b1d20393d6905f1c15cb353eb2c3c8c8f03799f4bfd1df812be0bf4d
/*
 * Arquivo gerado automaticamente pelo TADS Spring Initializr customizado (versão didática).
 * ATENÇÃO: Este arquivo NÃO é gerado pelo Spring Initializr oficial.
 */
package br.senac.tads.dsw.dados_pessoais.controller;

import br.senac.tads.dsw.dados_pessoais.dto.ExemploDto;
import br.senac.tads.dsw.dados_pessoais.service.ExemploService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exemplos")
public class ExemploRestController {

	private final ExemploService service;

	// Construtor usado para injeção de dependência do bean ExemploService
	// Substitui a necessidade de criar manualmente uma instância do service, o Spring faz isso automaticamente
	// Evitar usar o @Autowired (injeção por campo) em projetos modernos
	public ExemploRestController(ExemploService service) {
		this.service = service;
	}

	@GetMapping
	public List<ExemploDto> listarTodos() {
		return service.findAll();
	}

	@GetMapping("/{tipo}")
	public ResponseEntity<ExemploDto> buscarPorTipo(@PathVariable String tipo) {
		Optional<ExemploDto> optDado = service.findByTipo(tipo);
		if (optDado.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		ExemploDto dados = optDado.get();  
		return ResponseEntity.ok(dados);

		// *** Alternativa funcional:
		// return service.findByTipo(tipo)
		//		.map(ResponseEntity::ok)
		//		.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ExemploDto> incluirNovo(@RequestBody ExemploDto dto) {
		return ResponseEntity.ok(service.addNew(dto));
	}

}
