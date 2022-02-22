package Classes;

import java.text.DecimalFormat;

public class Produto implements Comparable<Produto> {
  private DecimalFormat df = new DecimalFormat("#.##");
  private int codigo;
  private String nome;
  private double valor;
  private int QtdEstoque;

  public Produto(int c, String n, double v, int qtd) {
    this.codigo = c;
    this.nome = n;
    this.valor = v;
    this.QtdEstoque = qtd;
  }

  public int getEstoque() {
    return this.QtdEstoque;
  }

  public void retirarEstoque(int qtdPraTirar) {
    this.QtdEstoque -= qtdPraTirar;
  }

  public String getNome() {
    return this.nome;
  }

  public double getValor() {
    return this.valor;
  }

  public int getCodigo() {
    return this.codigo;
  }

  @Override
  public String toString() {
    return String.format(
      "|Código: %s |<>| Nome: %s |<>| Valor: %s |<>| Estoque: %s",
      this.codigo,
      this.nome,
      df.format(this.valor),
      this.QtdEstoque
    );
  }



  @Override
  public int compareTo(Produto p) {
    if (this.getCodigo() < p.getCodigo()) {
      return -1;
    }
    if (this.getCodigo() > p.getCodigo()) {
      return 1;
    }
    return 0;
  }
}
