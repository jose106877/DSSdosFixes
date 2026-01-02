package ModelacaoEstrutural;

import java.util.ArrayList;
import java.util.List;

public class Balcao {

    private int id;
    private int numeroPedidosEntregar;
    private List<Pedido2> pedidosPendentes;

    public Balcao(int id) {
        this.id = id;
        this.numeroPedidosEntregar = 0;
        this.pedidosPendentes = new ArrayList<>();
    }

    public int getNumeroPedidosEntregar() {
        return this.numeroPedidosEntregar;
    }

    public void setNumeroPedidosEntregar(int numeroPedidosEntregar) {
        this.numeroPedidosEntregar = numeroPedidosEntregar;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void adicionarPedido(Pedido2 pedido) {
        pedidosPendentes.add(pedido);
        numeroPedidosEntregar++;
    }
    
    public void removerPedido(Pedido2 pedido) {
        if (pedidosPendentes.remove(pedido)) {
            numeroPedidosEntregar--;
        }
    }
    
    public List<Pedido2> getPedidosPendentes() {
        return new ArrayList<>(pedidosPendentes);
    }
}