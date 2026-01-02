package ModelacaoEstrutural;

public class GestorView extends View {

    public GestorView(int numEcras, Ecra[] ecras) {
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
    
    public void exibirIndicadores() {
        System.out.println("=== Painel do Gestor ===");
        System.out.println("Indicadores e estatísticas");
        if (ecraAtual >= 0 && ecraAtual < ecras.length && ecras[ecraAtual] != null) {
            ecras[ecraAtual].draw();
        }
    }
    
    public void exibirRelatorios() {
        System.out.println("=== Relatórios ===");
        System.out.println("Relatórios de desempenho");
    }
}