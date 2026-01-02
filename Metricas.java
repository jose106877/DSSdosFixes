package ModelacaoEstrutural;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Metricas {
    
    private int idRestaurante;
    private float faturacao;
    private int numeroPedidos;
    private LocalTime tempoMedioEspera;
    private Map<String, Integer> pratosMaisVendidos;
    private float satisfacaoMedia;
    
    public Metricas(int idRestaurante) {
        this.idRestaurante = idRestaurante;
        this.faturacao = 0.0f;
        this.numeroPedidos = 0;
        this.tempoMedioEspera = LocalTime.of(0, 0);
        this.pratosMaisVendidos = new HashMap<>();
        this.satisfacaoMedia = 0.0f;
    }
    
    public int getIdRestaurante() {
        return idRestaurante;
    }
    
    public float getFaturacao() {
        return faturacao;
    }
    
    public void setFaturacao(float faturacao) {
        this.faturacao = faturacao;
    }
    
    public void adicionarFaturacao(float valor) {
        this.faturacao += valor;
    }
    
    public int getNumeroPedidos() {
        return numeroPedidos;
    }
    
    public void setNumeroPedidos(int numeroPedidos) {
        this.numeroPedidos = numeroPedidos;
    }
    
    public void incrementarPedidos() {
        this.numeroPedidos++;
    }
    
    public LocalTime getTempoMedioEspera() {
        return tempoMedioEspera;
    }
    
    public void setTempoMedioEspera(LocalTime tempoMedioEspera) {
        this.tempoMedioEspera = tempoMedioEspera;
    }
    
    public Map<String, Integer> getPratosMaisVendidos() {
        return new HashMap<>(pratosMaisVendidos);
    }
    
    public void registarVendaPrato(String nomePrato) {
        pratosMaisVendidos.put(nomePrato, pratosMaisVendidos.getOrDefault(nomePrato, 0) + 1);
    }
    
    public float getSatisfacaoMedia() {
        return satisfacaoMedia;
    }
    
    public void setSatisfacaoMedia(float satisfacaoMedia) {
        this.satisfacaoMedia = satisfacaoMedia;
    }
    
    @Override
    public String toString() {
        return "Métricas Restaurante #" + idRestaurante + 
               " - Faturação: " + faturacao + 
               "€, Pedidos: " + numeroPedidos;
    }
}