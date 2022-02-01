package Classe;

public class Piloto extends Pessoa {
    private String breve;
    private String matricula;
    private Aeronave[] aviao = new Aeronave[50]; // eu botei com 50, mas o certo seria infinito, 
                                                //porem é mt trabalho
    private int qtdDeAvioesEspecifica = 0;

    public Piloto() {
    }

    public Piloto(String nome, String cpf, String breve, String matricula) {
        super(nome, cpf);
        this.breve = breve;
        this.matricula = matricula;
    }

    // criar novo piloto e passar um aviao pra ele usar
    public Piloto(String nome, String cpf, String breve, String matricula, Aeronave aviao, int param) {
        super(nome, cpf);
        this.breve = breve;
        this.matricula = matricula;
        this.setAviao(aviao, param);
    }

    // criar um novo piloto criando um aviao pra ele junto, e também adiciona em um
    // espaço desejado no array
    public Piloto(String nome, String cpf, String breve, String matricula, String m, String c, int param) {
        super(nome, cpf);
        this.breve = breve;
        this.matricula = matricula;
        this.aviao[param] = new Aeronave(m, c, this, param);
    }

    public Piloto getPiloto() {
        return this;
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

    // consegue acessar um avião específico do array
    public Aeronave getAviao(int param) {
        return aviao[param];
    }

    // faz uma checagem se esse piloto possui aviões ou não
    public boolean checarSeTemAviao() {
        if (aviao[0] != null) {
            return true;
        } else {
            return false;
        }
    }

    // adiciona aviões em posições expecificas no array, deve-se ser usado em loops
    public void setAviao(Aeronave aviao, int param) {
        this.aviao[param] = aviao;
    }

    public int getQtdDeAvioesEspecifica() {
        return qtdDeAvioesEspecifica;
    }

    // aumenta a qtdDeAvioesEspecifica, bem parecida com QtdCadastrados em
    // AppPilotos.java
    public void setAumentarQtdAviao() {
        this.qtdDeAvioesEspecifica++;
    }
}
