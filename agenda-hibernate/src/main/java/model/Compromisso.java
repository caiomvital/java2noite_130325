package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "compromissos")
public class Compromisso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome",  nullable = false)
	private String nome;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@ManyToOne // estabelece o relacionamento muitos-para-um
	@JoinColumn(name = "contato_id", referencedColumnName = "id")
	private Contato contato;
	
	@Column(name = "data_hora")
	private LocalDateTime dataHora = LocalDateTime.now();
	
	public Compromisso() {}
	
	public Compromisso(int id, String nome, String descricao, Contato contato, LocalDateTime dataHora) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.contato = contato;
		if(dataHora == null) {
			this.dataHora = LocalDateTime.now();
		} else {
		this.dataHora = dataHora;
		}
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
	
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
	    String dataHoraFormatada = (dataHora != null) ? dataHora.format(formatter) : "Data não disponível";
		
		String dados = "ID do compromisso: " + this.id;
		dados += "\nNome do Compromisso: " + this.nome;
		dados += "\nContato associado: " + this.contato.getNome();
		dados += "\nDescrição do compromisso: " + this.descricao;
		dados += "\nData/Hora: " + dataHoraFormatada;
		dados += "\n~~~~~~~~";
				
		return dados;
	}
	
	
}
