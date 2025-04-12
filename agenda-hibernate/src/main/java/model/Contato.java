package model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "contato") 
    private List<Compromisso> compromissos = new ArrayList<>();

    public Contato() {

    }

    public Contato(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

        String dados = String.format("ID: %d%nNome: %s%nTelefone: %s%nLista de Compromissos:%n", id, nome, telefone);

        if (compromissos != null && !compromissos.isEmpty()) {
            for (Compromisso compromisso : compromissos) {

            	String dataFormatada = compromisso.getDataHora().format(formatter);
                dados += String.format("  - %s - Horário: %s%n", compromisso.getNome(), dataFormatada);
            }
        } else {
            dados += "  Nenhum compromisso encontrado.\n";
        }

        return dados;
    }
}
