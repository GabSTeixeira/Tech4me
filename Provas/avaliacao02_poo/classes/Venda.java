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

  public String getData() {
    return sdf.format(data.getTime());
  }

  public void setData(int y, int m, int d) {
    data.set(y, m - 1, d);
  }
}
