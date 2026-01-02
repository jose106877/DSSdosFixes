package ModelacaoEstrutural;

public class DisplayFuncionarioView extends View {

    public DisplayFuncionarioView(int numEcras, Ecra[] ecras) {
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
    
    public void exibirTarefas() {
        System.out.println("=== Display do Funcionário ===");
        System.out.println("Tarefas e pedidos em preparação");
        if (ecraAtual >= 0 && ecraAtual < ecras.length && ecras[ecraAtual] != null) {
            ecras[ecraAtual].draw();
        }
    }
}