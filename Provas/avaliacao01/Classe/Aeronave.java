package Classe;

// Não ultiliza no codigo mas deixei do jeito que eu acho que seja o certo
public class Aeronave {
    private String Modelo;
    private String Categoria;
    private Piloto oPiloto;

    public Aeronave (){}

    public Aeronave (String m, String c) {
        this.Modelo = m;
        this.Categoria = c;
    }
    
    public Aeronave (String m, String c, Piloto oPiloto) {
        this(oPiloto);
        this.Modelo = m;
        this.Categoria = c;
    }

    public Aeronave (Piloto oPiloto) {
        this.oPiloto = oPiloto;
        oPiloto.setAviao(this);
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

    public Piloto getoPiloto() {
        return oPiloto;
    }

    public void setoPiloto(Piloto oPiloto) {
        this.oPiloto = oPiloto;
    }
    
}
