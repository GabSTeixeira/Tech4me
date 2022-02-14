package Classes;

public class Produto implements Comparable<Produto>{
    private int codigo;
    private String nome;
    private double valor;
    private int QtdEstoque;

    public Produto (int c, String n, double v, int qtd) {
        this.codigo = c;    
        this.nome = n;
        this.valor = v;
        this.QtdEstoque = qtd;
    }
    public int getEstoque () {
        return this.QtdEstoque;
    }

    public String getNome () {
        return this.nome;
    }

    public double getValor () {
        return this.valor;
    }

    @Override
    public String toString () {
        return String.format("|Codigo: %s |-x-| Nome: %s |-x-| Valor: %s |-x-| Estoque: %s",
        this.codigo,this.nome,this.valor,this.QtdEstoque);
    }
    @Override
    public int compareTo(Produto o) {
        if(this.getValor() < o.getValor()) {
            return -1;
        }
        if(this.getValor() > o.getValor()) {
            return 1;
        }
        return 0;
    }
}