package ModelacaoEstrutural.model;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Estatisticas {

    private float faturacao;
    private Map<String, Integer> stock;
    private LocalTime tempoMedioEspera;

    public Estatisticas() {
        this.faturacao = 0.0f;
        this.stock = new HashMap<>();
        this.tempoMedioEspera = LocalTime.of(0, 0);
    }

    public void addStockIngrediente(String nomeIngrediente, int quantidade) {
        stock.put(nomeIngrediente, stock.getOrDefault(nomeIngrediente, 0) + quantidade);
    }

    public void setStock(Map<String, Integer> stock) {
        this.stock = new HashMap<>(stock);
    }
    
    public Map<String, Integer> getStock() {
        return new HashMap<>(stock);
    }

    public void removerStockIngrediente(String nomeIngrediente, int quantidade) {
        int stockAtual = stock.getOrDefault(nomeIngrediente, 0);
        if (stockAtual >= quantidade) {
            stock.put(nomeIngrediente, stockAtual - quantidade);
        } else {
            throw new IllegalArgumentException("Stock insuficiente para " + nomeIngrediente);
        }
    }

    public LocalTime getTempoMedioEspera() {
        return this.tempoMedioEspera;
    }

    public void setTempoMedioEspera(LocalTime tempoMedioEspera) {
        this.tempoMedioEspera = tempoMedioEspera;
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
}