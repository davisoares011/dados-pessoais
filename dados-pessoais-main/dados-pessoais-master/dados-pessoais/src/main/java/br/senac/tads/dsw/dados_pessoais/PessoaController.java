package br.senac.tads.dsw.dados_pessoais;

import java.util.List;
import java.util.Optional;
import java.net.URI;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	private final PessoaService pessoaServiceMapImpl;

	public PessoaController(PessoaService pessoaServiceMapImpl) {
		this.pessoaServiceMapImpl = pessoaServiceMapImpl;
	}

	@GetMapping
	public List<PessoaDto> obterPessoas() {
		return pessoaServiceMapImpl.obterPessoas();
	}

	@GetMapping("/busca")
	public ResponseEntity<List<PessoaDto>> buscarPessoas(@RequestParam("q") String termo) {
		return ResponseEntity.ok(pessoaServiceMapImpl.buscarPessoas(termo));
	}

	@GetMapping("/{username}")
	public PessoaDto obterPessoa(@PathVariable("username") String username) {
		Optional<PessoaDto> optPessoa = pessoaServiceMapImpl.obterPessoa(username);
		if (optPessoa.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return optPessoa.get();
	}

	@PostMapping("/sem-validacao")
	public ResponseEntity<?> incluirNovo(@RequestBody PessoaDto pessoa) {
		pessoaServiceMapImpl.incluirNovaPessoa(pessoa);
		URI location = ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path("/pessoas/{username}")
			.buildAndExpand(pessoa.getUsername())
			.toUri();
		return ResponseEntity.created(location).build();
	}

	@PostMapping
	public ResponseEntity<?> incluirNovoComValidacao(@RequestBody @Valid PessoaDto pessoa) {
		pessoaServiceMapImpl.incluirNovaPessoa(pessoa);
		URI location = ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path("/pessoas/{username}")
			.buildAndExpand(pessoa.getUsername())
			.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{username}")
	public ResponseEntity<?> atualizar(@PathVariable("username") String username, @RequestBody @Valid PessoaAlteracaoDto pessoa) {
		PessoaDto pessoaAlterada = pessoaServiceMapImpl.alterarPessoa(username, pessoa);
		return ResponseEntity.ok().body(pessoaAlterada);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<?> remover(@PathVariable("username") String username) {
		pessoaServiceMapImpl.removerPessoa(username);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<ProblemDetail> tratarExcecao(NaoEncontradoException ex) {
		ProblemDetail pd = ProblemDetail.forStatusAndDetail(
			HttpStatusCode.valueOf(404), ex.getMessage());
		return ResponseEntity.of(pd).build();
	}
}
