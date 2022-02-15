package Classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Venda {

  private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
  private Calendar data = Calendar.getInstance();
  private Produto produto;
  private int QtdVendida;

  public Venda(Produto produto, int QtdVendida) {
    this.produto = produto;
    this.QtdVendida = QtdVendida;
  }

  public Venda(Produto produto, int QtdVendida, int year, int month, int date) {
    this.produto = produto;
    this.QtdVendida = QtdVendida;
    this.setData(year, month - 1, date);
  }

  public int getDataComparavel() {
    return Integer.parseInt(data.get(Calendar.YEAR)+""+data.get(Calendar.MONTH)+""+data.get(Calendar.DAY_OF_MONTH));
  }
  public String getData() {
    return sdf.format(data.getTime());

  }

  public void setData(int y, int m, int d) {
    data.set(y, m - 1, d);
  }

  public double getTotalVendido () {
    return (double) this.QtdVendida * this.produto.getValor();
  }

  @Override
  public String toString () {
    return String.format("|Codigo: %s |<>| Nome: %s |<>| Valor: %s |<>| Qtd Vendida: %s |<>| Total: %s",
    this.produto.getCodigo(),
    this.produto.getNome(),
    this.produto.getValor(),
    this.QtdVendida,
    this.getTotalVendido());
  }
}
