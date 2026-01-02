package ModelacaoEstrutural;

public class ClientView extends View {

    public ClientView(int numEcras, Ecra[] ecras) {
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
    
    public void exibirMenu() {
        System.out.println("=== Menu do Cliente ===");
        if (ecraAtual >= 0 && ecraAtual < ecras.length && ecras[ecraAtual] != null) {
            ecras[ecraAtual].draw();
        }
    }
}