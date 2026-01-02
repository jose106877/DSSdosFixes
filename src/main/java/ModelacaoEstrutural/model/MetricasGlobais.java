package ModelacaoEstrutural.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricasGlobais {
    
    private float faturacaoTotal;
    private int numeroPedidosTotal;
    private LocalTime tempoMedioEsperaGlobal;
    private Map<String, Integer> pratosMaisVendidosGlobal;
    private List<Metricas> metricasPorRestaurante;
    private float satisfacaoMediaGlobal;
    
    public MetricasGlobais() {
        this.faturacaoTotal = 0.0f;
        this.numeroPedidosTotal = 0;
        this.tempoMedioEsperaGlobal = LocalTime.of(0, 0);
        this.pratosMaisVendidosGlobal = new HashMap<>();
        this.metricasPorRestaurante = new ArrayList<>();
        this.satisfacaoMediaGlobal = 0.0f;
    }
    
    public float getFaturacaoTotal() {
        return faturacaoTotal;
    }
    
    public void setFaturacaoTotal(float faturacaoTotal) {
        this.faturacaoTotal = faturacaoTotal;
    }
    
    public void adicionarFaturacao(float valor) {
        this.faturacaoTotal += valor;
    }
    
    public int getNumeroPedidosTotal() {
        return numeroPedidosTotal;
    }
    
    public void setNumeroPedidosTotal(int numeroPedidosTotal) {
        this.numeroPedidosTotal = numeroPedidosTotal;
    }
    
    public void incrementarPedidosTotal() {
        this.numeroPedidosTotal++;
    }
    
    public LocalTime getTempoMedioEsperaGlobal() {
        return tempoMedioEsperaGlobal;
    }
    
    public void setTempoMedioEsperaGlobal(LocalTime tempoMedioEsperaGlobal) {
        this.tempoMedioEsperaGlobal = tempoMedioEsperaGlobal;
    }
    
    public Map<String, Integer> getPratosMaisVendidosGlobal() {
        return new HashMap<>(pratosMaisVendidosGlobal);
    }
    
    public void registarVendaPratoGlobal(String nomePrato) {
        pratosMaisVendidosGlobal.put(nomePrato, 
            pratosMaisVendidosGlobal.getOrDefault(nomePrato, 0) + 1);
    }
    
    public List<Metricas> getMetricasPorRestaurante() {
        return new ArrayList<>(metricasPorRestaurante);
    }
    
    public void adicionarMetricasRestaurante(Metricas metricas) {
        metricasPorRestaurante.add(metricas);
    }
    
    public float getSatisfacaoMediaGlobal() {
        return satisfacaoMediaGlobal;
    }
    
    public void setSatisfacaoMediaGlobal(float satisfacaoMediaGlobal) {
        this.satisfacaoMediaGlobal = satisfacaoMediaGlobal;
    }
    
    public void calcularMetricasGlobais() {
        faturacaoTotal = 0.0f;
        numeroPedidosTotal = 0;
        
        for (Metricas metricas : metricasPorRestaurante) {
            faturacaoTotal += metricas.getFaturacao();
            numeroPedidosTotal += metricas.getNumeroPedidos();
        }
    }
    
    @Override
    public String toString() {
        return "Métricas Globais - Faturação: " + faturacaoTotal + 
               "€, Pedidos: " + numeroPedidosTotal + 
               ", Restaurantes: " + metricasPorRestaurante.size();
    }
}