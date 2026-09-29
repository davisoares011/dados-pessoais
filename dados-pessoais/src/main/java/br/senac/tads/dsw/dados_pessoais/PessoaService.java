package br.senac.tads.dsw.dados_pessoais;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;


@Service
public class PessoaService {

	private AtomicInteger contador = new AtomicInteger(0);

	private Map<String, PessoaDto> mapPessoas = new ConcurrentHashMap<>();

	@PostConstruct
	public void init() {
		mapPessoas.put("davi", new PessoaDto(contador.incrementAndGet(),
			"davi", "Davi Soares Araújo",
			"davisoares0708@gmail.com", "(11) 9 6898-8967", LocalDate.parse("2003-03-10")));

		mapPessoas.put("gabriel", new PessoaDto(contador.incrementAndGet(),
			"gabriel", "Gabriel Silva Santos",
			"gabrielsilva123@gmail.com", "(11) 9 1234-5678", LocalDate.parse("2002-05-15")));

		mapPessoas.put("joao", new PessoaDto(contador.incrementAndGet(),
			"joao", "João Oliveira Silva",
			"joao.oliveira123@gmail.com", "(11) 9 5678-1234", LocalDate.parse("2001-09-20")));
	}

	public List<PessoaDto> listar() {
		return new ArrayList<>(mapPessoas.values());
	}

	public Optional<PessoaDto> buscarPorUsername(String username) {
		return Optional.ofNullable(mapPessoas.get(username));
	}

	public List<PessoaDto> obterPessoas() {
		return listar();
	}

	public Optional<PessoaDto> obterPessoa(String username) {
		return buscarPorUsername(username);
	}

	public PessoaDto incluirNovaPessoa(PessoaDto pessoa) {
		pessoa.setId(contador.incrementAndGet());
		mapPessoas.put(pessoa.getUsername(), pessoa);
		return pessoa;
	}

	public PessoaDto alterarPessoa(String username, PessoaAlteracaoDto pessoaAlteracao) {
		if (!mapPessoas.containsKey(username)) {
			throw new NaoEncontradoException("Pessoa " + username + " não encontrada.");
		}
		PessoaDto pessoaOriginal = mapPessoas.get(username);
		pessoaOriginal.setNome(pessoaAlteracao.getNome());
		pessoaOriginal.setEmail(pessoaAlteracao.getEmail());
		pessoaOriginal.setTelefone(pessoaAlteracao.getTelefone());
		pessoaOriginal.setDataNascimento(pessoaAlteracao.getDataNascimento());
		pessoaOriginal.setConhecimentos(pessoaAlteracao.getConhecimentos());
		return pessoaOriginal;
	}

	public void removerPessoa(String username) {
	if (!mapPessoas.containsKey(username)){
	throw new NaoEncontradoException("Pessoa " + username + " não encontrada.");
	}
	mapPessoas.remove(username);
	}
}


