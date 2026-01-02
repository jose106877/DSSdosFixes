package ModelacaoEstrutural;

import java.util.ArrayList;
import java.util.List;

public class Prato extends Proposta {
    
    private List<Ingrediente> ingredientes;

    public Prato() {
        super();
        this.ingredientes = new ArrayList<>();
    }
    
    public Prato(String nome, float preco, float custo) {
        super(nome, preco, custo);
        this.ingredientes = new ArrayList<>();
    }
    
    public List<Ingrediente> getIngredientes() {
        return new ArrayList<>(ingredientes);
    }
    
    public void adicionarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }
    
    public void removerIngrediente(Ingrediente ingrediente) {
        ingredientes.remove(ingrediente);
    }
    
    @Override
    public String toString() {
        return "Prato: " + getNome() + " - Preço: " + getPreco() + "€";
    }
}