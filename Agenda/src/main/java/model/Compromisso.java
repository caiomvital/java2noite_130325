package model;

public class Compromisso {

	private int id;
	private String nome;
	private String descricao;
	private Contato contato;
	
	public Compromisso() {}
	
	public Compromisso(int id, String nome, String descricao, Contato contato) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.contato = contato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	@Override
	public String toString() {
		String dados = "ID do compromisso: " + this.id;
		dados += "\nNome do Compromisso: " + this.nome;
		dados += "\nContato associado: " + this.contato.getNome();
		dados += "\nDescrição do compromisso: " + this.descricao;
		dados += "\n~~~~~~~~";
				
		return dados;
	}
	
	
}
