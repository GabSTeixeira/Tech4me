package Classes;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Venda implements Comparable<Venda>{
  private DecimalFormat df = new DecimalFormat("#.##");
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

  public Calendar getDataComparavel () {
    return this.data;
  }

  public double getTotalVendido () {
    return (double) this.QtdVendida * this.produto.getValor();
  }

  public String getTotalVendidoDecimal () {
    return df.format(this.QtdVendida * this.produto.getValor());
  }
  
  @Override
  public int compareTo(Venda o) {
    if(this.data.compareTo(o.getDataComparavel())> 0) {
      return -1;
    }

    return 1;
  }
  
  @Override
  public String toString () {
    return String.format("|Data: %s |<>| Nome: %s |<>| Valor: %s |<>| Qtd Vendida: %s |<>| Código: %s |<>| Total: %s",
    this.getData(),
    this.produto.getNome(),
    df.format(this.produto.getValor()),
    this.QtdVendida,
    this.produto.getCodigo(),
    this.getTotalVendidoDecimal());
  }
  
}
