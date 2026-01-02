package ModelacaoEstrutural;

import java.util.HashMap;
import java.util.Map;

public class Armazem {

    private int id;
    private String localizacao;
    private Map<String, Integer> stock;

    public Armazem(int id, String localizacao) {
        this.id = id;
        this.localizacao = localizacao;
        this.stock = new HashMap<>();
    }

    public int getStockIngrediente(String nomeIngrediente) {
        return stock.getOrDefault(nomeIngrediente, 0);
    }

    public void addStockIngrediente(String nomeIngrediente, int quantidade) {
        stock.put(nomeIngrediente, getStockIngrediente(nomeIngrediente) + quantidade);
    }

    public String getLocalizacao() {
        return this.localizacao;
    }
    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void removerStockIngrediente(String nomeIngrediente, int quantidade) {
        int stockAtual = getStockIngrediente(nomeIngrediente);
        if (stockAtual >= quantidade) {
            stock.put(nomeIngrediente, stockAtual - quantidade);
        } else {
            throw new IllegalArgumentException("Stock insuficiente para o ingrediente: " + nomeIngrediente);
        }
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Map<String, Integer> getStock() {
        return new HashMap<>(stock);
    }
    
    public void setStock(Map<String, Integer> stock) {
        this.stock = new HashMap<>(stock);
    }
}