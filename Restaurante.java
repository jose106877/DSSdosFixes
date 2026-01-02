package ModelacaoEstrutural;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {

    private int id;
    private String localizacao;
    private List<Balcao> balcoes;
    private Cozinha cozinha;
    private Estatisticas estatisticas;

    public Restaurante(int id, String localizacao, int numBalcoes) {
        this.id = id;
        this.localizacao = localizacao;
        this.balcoes = new ArrayList<>();
        for (int i = 0; i < numBalcoes; i++) {
            balcoes.add(new Balcao(i + 1));
        }
        this.cozinha = new Cozinha(id);
        this.estatisticas = new Estatisticas();
    }

    public String getLocalizacao() {
        return this.localizacao;
    }
    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public List<Balcao> getBalcoes() {
        return new ArrayList<>(balcoes);
    }
    
    public Balcao getBalcao(int id) {
        return balcoes.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public Cozinha getCozinha() {
        return cozinha;
    }
    
    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }
    
    public Estatisticas getEstatisticas() {
        return estatisticas;
    }
    
    public void setEstatisticas(Estatisticas estatisticas) {
        this.estatisticas = estatisticas;
    }
    
    @Override
    public String toString() {
        return "Restaurante #" + id + " - " + localizacao;
    }
}