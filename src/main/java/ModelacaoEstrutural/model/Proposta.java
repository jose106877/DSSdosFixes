package ModelacaoEstrutural.model;

import java.util.ArrayList;
import java.util.List;

public class Proposta {

    private String nome;
    private float preco;
    private float custo;
    private List<Alergenio> alergenios;

    public Proposta() {
        this.nome = "";
        this.preco = 0.0f;
        this.custo = 0.0f;
        this.alergenios = new ArrayList<>();
    }
    
    public Proposta(String nome, float preco, float custo) {
        this.nome = nome;
        this.preco = preco;
        this.custo = custo;
        this.alergenios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }
    
    public List<Alergenio> getAlergenios() {
        return new ArrayList<>(alergenios);
    }
    
    public void adicionarAlergenio(Alergenio alergenio) {
        if (!alergenios.contains(alergenio)) {
            alergenios.add(alergenio);
        }
    }
    
    public void removerAlergenio(Alergenio alergenio) {
        alergenios.remove(alergenio);
    }
    
    public boolean contemAlergenio(String nomeAlergenio) {
        return alergenios.stream()
                .anyMatch(a -> a.getNome().equalsIgnoreCase(nomeAlergenio));
    }
    
    public float calcularMargem() {
        return preco - custo;
    }
    
    @Override
    public String toString() {
        return "Proposta: " + nome + " - Preço: " + preco + "€";
    }
}