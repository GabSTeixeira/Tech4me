package Classe;

// usei o conceito novo de abstract
public abstract class Pessoa {
    private int cpf;
    private String nome;

    
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
