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

    private Map<String, Pessoa> mapPessoas = new ConcurrentHashMap<>();

    @PostConstruct
public void init() {
    mapPessoas.put("davi", new Pessoa(contador.incrementAndGet(),
        "davi", "Davi Soares Araújo",
        "davisoares0708@gmail.com", "(11) 9 6898-8967", LocalDate.parse("2003-03-10")));

    mapPessoas.put("gabriel", new Pessoa(contador.incrementAndGet(),
        "gabriel", "Gabriel Silva Santos",
        "gabrielsilva123@gmail.com", "(11) 9 1234-5678", LocalDate.parse("2002-05-15")));

    mapPessoas.put("joao", new Pessoa(contador.incrementAndGet(),
        "joao", "João Oliveira Silva",
        "joao.oliveira123@gmail.com", "(11) 9 5678-1234", LocalDate.parse("2001-09-20")));
}

    public List<Pessoa> listar() {
        return new ArrayList<>(mapPessoas.values());
    }

    public Optional<Pessoa> buscarPorUsername(String username) {
        return Optional.ofNullable(mapPessoas.get(username));
    }

    public List<Pessoa> obterPessoas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPessoas'");
    }

    public Optional<Pessoa> obterPessoa(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPessoa'");
    }

	public Pessoa incluirNovaPessoa (Pessoa pessoa) {
		pessoa.setId(contador.incrementAndGet());
		mapPessoas.put(pessoa.getUsername(), pessoa);
		return pessoa;
	}
}
