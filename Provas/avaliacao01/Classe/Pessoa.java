package Classe;

// metodo abstract ja que nunca usamos uma Pessoa "pura" no codigo.
public abstract class Pessoa {
    private String cpf;
    private String nome;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // metodo para pegar a filha Piloto de Pessoa
    public abstract Piloto getPiloto();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
