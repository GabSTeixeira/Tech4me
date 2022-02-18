package Classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Venda {

  private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
  private Calendar data = Calendar.getInstance();
  private Produto produto;
  private int QtdVendida;


  public Venda(Produto produto, int QtdVendida, Date datainfo) {
    this.produto = produto;
    this.QtdVendida = QtdVendida;
    data.setTime(datainfo);
  }

  public boolean comparaDataEntre(Date inicio, Date termino) {
   if(data.getTime().compareTo(inicio)>=0&&data.getTime().compareTo(termino)<=0) {
     return true;
   } else {
     return false;
   }

  }
  public String getData() {
    return sdf.format(data.getTime());

  }

  public double getTotalVendido () {
    return (double) this.QtdVendida * this.produto.getValor();
  }

  @Override
  public String toString () {
    return String.format("|Codigo: %s |<>| Nome: %s |<>| Valor: %s |<>| Qtd Vendida: %s |<>| Data: %s |<>| Total: %s",
    this.produto.getCodigo(),
    this.produto.getNome(),
    this.produto.getValor(),
    this.QtdVendida,
    this.getData(),
    this.getTotalVendido());
  }
}
