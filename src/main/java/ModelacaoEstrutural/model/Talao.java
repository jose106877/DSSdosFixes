package ModelacaoEstrutural.model;

import java.time.LocalDateTime;

public class Talao {
    
    private int id;
    private Pedido pedido;
    private LocalDateTime dataEmissao;
    private int balcao;
    
    public Talao(Pedido pedido) {
        this.id = pedido.getNumero();
        this.pedido = pedido;
        this.dataEmissao = LocalDateTime.now();
        this.balcao = pedido.getBalcaoAtribuido();
    }
    
    public int getId() {
        return id;
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    
    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }
    
    public int getBalcao() {
        return balcao;
    }
    
    public void setBalcao(int balcao) {
        this.balcao = balcao;
    }
    
    public void imprimir() {
        System.out.println("========== TALÃO ==========");
        System.out.println("Número: " + id);
        System.out.println("Balcão: " + balcao);
        System.out.println("Data: " + dataEmissao);
        System.out.println("Pedido: " + pedido);
        System.out.println("===========================");
    }
    
    @Override
    public String toString() {
        return "Talão #" + id + " - Balcão " + balcao;
    }
}