package br.com.tech4me.animaisms.view.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AnimalModeloInclusao {
    @NotBlank(message = "O dono nao pode ser em branco")
    @NotEmpty(message = "O dono nao pode ser vazio")
    private String dono;
    private String nome;
    private Integer idade;
    private String raca;

    //#region Get / Set
    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
    //#endregion
}
