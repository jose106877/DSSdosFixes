package ModelacaoEstrutural.model;

public class Menu extends Proposta {

    private TamanhoMenu tamanho;
    
    public enum TamanhoMenu {
        PEQUENO,
        MEDIO,
        GRANDE
    }

    public Menu() {
        super();
        this.tamanho = TamanhoMenu.MEDIO;
    }
    
    public Menu(String nome, float preco, float custo, TamanhoMenu tamanho) {
        super(nome, preco, custo);
        this.tamanho = tamanho;
    }

    public TamanhoMenu getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoMenu tamanho) {
        this.tamanho = tamanho;
    }
    
    @Override
    public String toString() {
        return "Menu: " + getNome() + " (" + tamanho + ") - Preço: " + getPreco() + "€";
    }
}