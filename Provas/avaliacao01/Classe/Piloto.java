package Classe;

public class Piloto extends Pessoa {
    private String breve;
    private String matricula;
    private Aeronave aviao;

    public Piloto (){}

    public Piloto (String nome, int cpf, String breve, String matricula) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.breve = breve;
        this.matricula = matricula;
    }

    // criar novo piloto e passar um aviao pra ele usar
    public Piloto (String nome, int cpf, String breve, String matricula, Aeronave aviao) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.breve = breve;
        this.matricula = matricula;
        this.setAviao(aviao);
    }

    //criar um novo piloto criando um aviao pra ele junto
    public Piloto (String nome, int cpf, String breve, String matricula, String m, String c) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.breve = breve;
        this.matricula = matricula;
        this.aviao = new Aeronave(m,c,this);
    }
    public String getBreve() {
        return breve;
    }

    public void setBreve(String breve) {
        this.breve = breve;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Aeronave getAviao() {
        return aviao;
    }

    public void setAviao(Aeronave aviao) {
        this.aviao = aviao;
    } 
}
