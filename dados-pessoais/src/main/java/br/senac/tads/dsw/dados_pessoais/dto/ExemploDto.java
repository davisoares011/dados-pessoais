// hash-identificacao: 86a46359b1d20393d6905f1c15cb353eb2c3c8c8f03799f4bfd1df812be0bf4d
/*
 * Arquivo gerado automaticamente pelo TADS Spring Initializr customizado (versão didática).
 * ATENÇÃO: Este arquivo NÃO é gerado pelo Spring Initializr oficial.
 */
package br.senac.tads.dsw.dados_pessoais.dto;

/*
 * ALTERNATIVA COM RECORD (Java 16+):
 *
 *   public record ExemploDto(Long id, String tipo, String mensagem) {}
 *
 * Com record, o compilador gera automaticamente: construtor canônico, acessores,
 * equals(), hashCode() e toString(). A classe também se torna imutável (campos final).
 * A diferença de uso está no acesso às propriedades:
 *
 *   POJO (esta versão):  objeto.getId()       objeto.getTipo()       objeto.getMensagem()
 *   Record:              objeto.id()          objeto.tipo()          objeto.mensagem()
 */
public class ExemploDto {

	private Integer id;
	private String tipo;
	private String mensagem;

	public ExemploDto() {}

	public ExemploDto(Integer id, String tipo, String mensagem) {
		this.id = id;
		this.tipo = tipo;
		this.mensagem = mensagem;
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getTipo() { return tipo; }
	public void setTipo(String tipo) { this.tipo = tipo; }

	public String getMensagem() { return mensagem; }
	public void setMensagem(String mensagem) { this.mensagem = mensagem; }

}
