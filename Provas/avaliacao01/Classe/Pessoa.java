package Classe;

public class Pessoa {
    private int cpf;
    private String nome;

    public Pessoa () {}

    public Pessoa (String nome, int cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCpf() {
        return cpf;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    
}
