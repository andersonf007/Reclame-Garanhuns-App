package com.hackaton.projetofuncional.entities;

/**
 * Created by matt on 04/06/17.
 */

public class Ocorrencia {
    public String criador = "", data = "", descricao = "";
    public int positivos = 0, negativos = 0, tipoVisualizacao = 0, tipoPeloUsuario = 0;
    public double lat = 0, longe = 0;
    //0 denuncia
    //1 projeto  futuro
    //2 projeto em andamento

    public Ocorrencia(String criador, String data, String descricao, int positivos, int negativos, double lat, double longe, int tipo, int tipo1) {
        this.criador = criador;
        this.data = data;
        this.descricao = descricao;
        this.positivos = positivos;
        this.negativos = negativos;
        this.lat = lat;
        this.longe = longe;
        this.tipoVisualizacao = tipo;
        this.tipoPeloUsuario = tipo1;
    }

    public String getTextoTipo() {//se alterar isso, aalterar array em AddOcorrenciaActivity
        if (tipoPeloUsuario == 0) {
            return "Iluminação";
        } else if (tipoPeloUsuario == 1) {
            return "Eletrico";
        } else if (tipoPeloUsuario == 2) {
            return "Encanação";
        } else if (tipoPeloUsuario == 3) {
            return "Deteriorização de vias";
        } else if (tipoPeloUsuario == 10) {
            return "Projeto Futuro";
        } else if (tipoPeloUsuario == 11) {
            return "Projeto Ativo";
        } else {
            return "Desconhecido";
        }
    }
}
