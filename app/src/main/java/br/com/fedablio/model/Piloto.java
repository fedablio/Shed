package br.com.fedablio.model;

public class Piloto {

    private int id_piloto;
    private String senha_piloto;
    private String nome_piloto;
    private String canal_piloto;
    private String banda_piloto;

    public int getId_piloto() {
        return id_piloto;
    }

    public void setId_piloto(int id_piloto) {
        this.id_piloto = id_piloto;
    }

    public String getSenha_piloto() {
        return senha_piloto;
    }

    public void setSenha_piloto(String senha_piloto) {
        this.senha_piloto = senha_piloto;
    }

    public String getNome_piloto() {
        return nome_piloto;
    }

    public void setNome_piloto(String nome_piloto) {
        this.nome_piloto = nome_piloto;
    }

    public String getCanal_piloto() {
        return canal_piloto;
    }

    public void setCanal_piloto(String canal_piloto) {
        this.canal_piloto = canal_piloto;
    }

    public String getBanda_piloto() {
        return banda_piloto;
    }

    public void setBanda_piloto(String banda_piloto) {
        this.banda_piloto = banda_piloto;
    }
}
