package Classes;

public class Venda Throws Exception {
    private String data;
    private Produto produto;
    private int QtdVendida;

    public Venda (Produto produto, int QtdVendida) {
        this.produto = produto;
        this.QtdVendida = QtdVendida;
        //this.data = new Date();
    }

    public Venda (Produto produto, int QtdVendida, String data) {
        this.produto = produto;
        this.QtdVendida = QtdVendida;
        this.data = data;

    }
}
