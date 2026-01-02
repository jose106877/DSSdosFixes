package ModelacaoEstrutural.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    
    private int numero;
    private List<Proposta> artigos;
    private boolean takeaway;
    private LocalDateTime dataHora;
    private EstadoPedido estado;
    private int balcaoAtribuido;
    
    public enum EstadoPedido {
        PENDENTE,
        EM_PREPARACAO,
        PRONTO,
        ENTREGUE,
        CANCELADO
    }
    
    public Pedido(int numero, List<Proposta> artigos, boolean takeaway) {
        this.numero = numero;
        this.artigos = new ArrayList<>(artigos);
        this.takeaway = takeaway;
        this.dataHora = LocalDateTime.now();
        this.estado = EstadoPedido.PENDENTE;
        this.balcaoAtribuido = -1;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public List<Proposta> getArtigos() {
        return new ArrayList<>(artigos);
    }
    
    public void adicionarArtigo(Proposta artigo) {
        artigos.add(artigo);
    }
    
    public boolean isTakeaway() {
        return takeaway;
    }
    
    public void setTakeaway(boolean takeaway) {
        this.takeaway = takeaway;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public EstadoPedido getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
    
    public int getBalcaoAtribuido() {
        return balcaoAtribuido;
    }
    
    public void setBalcaoAtribuido(int balcaoAtribuido) {
        this.balcaoAtribuido = balcaoAtribuido;
    }
    
    public float calcularTotal() {
        return (float) artigos.stream()
                .mapToDouble(Proposta::getPreco)
                .sum();
    }
    
    @Override
    public String toString() {
        String tipo = takeaway ? "Takeaway" : "Local";
        return "Pedido #" + numero + " (" + tipo + ") - Total: " + calcularTotal() + "€";
    }
}
