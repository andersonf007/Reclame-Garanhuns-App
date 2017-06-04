package com.hackaton.projetofuncional.entities;

/**
 * Created by matt on 04/06/17.
 */

public class Ocorrencia {
    public String criador = "", data = "", descricao = "";
    public int positivos = 0, negativos = 0, tipo=0;
    public double lat = 0, longe = 0;

    public Ocorrencia(String criador, String data, String descricao, int positivos, int negativos, double lat, double longe, int tipo) {
        this.criador = criador;
        this.data = data;
        this.descricao = descricao;
        this.positivos = positivos;
        this.negativos = negativos;
        this.lat = lat;
        this.longe = longe;
        this.tipo = tipo;
    }
}
