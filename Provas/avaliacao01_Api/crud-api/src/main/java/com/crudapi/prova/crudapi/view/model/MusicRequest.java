package com.crudapi.prova.crudapi.view.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MusicRequest {
    
    @NotBlank(message = "deve conter um valor")
    @NotEmpty(message = "não pode ser nulo ou vazio")
    private String titulo;
    @NotBlank(message = "deve conter um valor")
    @NotEmpty(message = "não pode ser nulo ou vazio")
    private String artista;
    @NotBlank(message = "deve conter um valor")
    @NotEmpty(message = "não pode ser nulo ou vazio")
    private String album;
    @NotBlank(message = "deve conter um valor")
    @NotEmpty(message = "não pode ser nulo ou vazio")
    private String genero;
    @Min(value = 1600, message = "não pode ser menor que 1600") 
    @Max(value = 2040, message = "não pode ser maior que 2040")
    @NotNull(message = "Tem q conter uma valor")
    private int anoLancamento;
    @NotBlank(message = "deve conter um valor")
    @NotEmpty(message = "não pode ser nulo ou vazio")
    private String compositor;
       
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public String getCompositor() {
        return compositor;
    }
    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }
}
