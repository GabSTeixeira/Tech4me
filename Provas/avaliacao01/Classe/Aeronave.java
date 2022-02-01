package Classe;

public class Aeronave {
    private String Modelo;
    private String Categoria;
    private Piloto[] oPiloto = new Piloto[50];

    public Aeronave (){}

    public Aeronave (String m, String c) {
        this.Modelo = m;
        this.Categoria = c;
    }
    // criando Aeronave com um piloto
    public Aeronave (String m, String c, Piloto oPiloto, int param) {
        this.oPiloto[param] = oPiloto;
        this.Modelo = m;
        this.Categoria = c;
    }
    // cria uma aeronave, define o piloto dessa aeronave, e dps guarda essa aeronave em 
    //algum espaço dentro do array de aeronaves em Piloto.java
    public Aeronave (Piloto oPiloto, int param) {
        this.oPiloto[param] = oPiloto;
        oPiloto.setAviao(this,param);
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public Piloto getoPiloto(int param) {
        return oPiloto[param];
    }
    // metodo para definir o piloto
    public void setoPiloto(Piloto oPiloto, int param) {
        this.oPiloto[param] = oPiloto;
    }
    
}
