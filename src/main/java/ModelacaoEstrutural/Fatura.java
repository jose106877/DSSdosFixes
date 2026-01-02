package ModelacaoEstrutural;

import java.time.LocalDateTime;

public class Fatura {
    
    private int numero;
    private Pedido pedido;
    private LocalDateTime dataEmissao;
    private float total;
    private float iva;
    private static final float TAXA_IVA = 0.23f;
    
    public Fatura(Pedido pedido) {
        this.numero = pedido.getNumero();
        this.pedido = pedido;
        this.dataEmissao = LocalDateTime.now();
        this.total = pedido.calcularTotal();
        this.iva = total * TAXA_IVA;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    
    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }
    
    public float getTotal() {
        return total;
    }
    
    public float getIva() {
        return iva;
    }
    
    public float getTotalComIva() {
        return total + iva;
    }
    
    public void imprimir() {
        System.out.println("========== FATURA ==========");
        System.out.println("Número: " + numero);
        System.out.println("Data: " + dataEmissao);
        System.out.println("Pedido: " + pedido);
        System.out.println("----------------------------");
        for (Proposta artigo : pedido.getArtigos()) {
            System.out.println(artigo.getNome() + " - " + artigo.getPreco() + "€");
        }
        System.out.println("----------------------------");
        System.out.println("Subtotal: " + total + "€");
        System.out.println("IVA (23%): " + iva + "€");
        System.out.println("TOTAL: " + getTotalComIva() + "€");
        System.out.println("============================");
    }
    
    @Override
    public String toString() {
        return "Fatura #" + numero + " - Total: " + getTotalComIva() + "€";
    }
}