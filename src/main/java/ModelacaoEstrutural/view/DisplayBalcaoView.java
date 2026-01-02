package ModelacaoEstrutural.view;
import ModelacaoEstrutural.model.*;
public class DisplayBalcaoView extends View {

    public DisplayBalcaoView(int numEcras, Ecra[] ecras) {
        super(numEcras, ecras);
    }
    
    @Override
    public void updateEcra(Pair<Integer, String> options) {
        super.updateEcra(options);
    }
    
    @Override
    public void proximoEcra() {
        super.proximoEcra();
    }
    
    @Override
    public void close() {
        super.close();
    }
    
    public void exibirPedidosPendentes() {
        System.out.println("=== Display do Balcão ===");
        System.out.println("Pedidos pendentes para entrega");
        if (ecraAtual >= 0 && ecraAtual < ecras.length && ecras[ecraAtual] != null) {
            ecras[ecraAtual].draw();
        }
    }
}