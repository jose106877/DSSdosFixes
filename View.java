package ModelacaoEstrutural;

public abstract class View {

    public int ecraAtual;
    private int ecraAnterior;
    protected Ecra[] ecras;

    public View(int numEcras, Ecra[] ecras) {
        this.ecraAtual = 0;
        this.ecraAnterior = -1;
        this.ecras = new Ecra[numEcras];
        System.arraycopy(ecras, 0, this.ecras, 0, Math.min(numEcras, ecras.length));
    }

    public void updateEcra(Pair<Integer, String> options) {
        if (options != null && ecras[ecraAtual] != null) {
            int idOpcao = options.getFirst();
            ecras[ecraAtual].updateOpcaoAt(idOpcao);
            System.out.println("Ecrã atualizado com opção: " + options.getSecond());
        }
    }

    public void proximoEcra() {
        if (ecras[ecraAtual] != null) {
            Ecra proximo = ecras[ecraAtual].getProximoEcra();
            if (proximo != null) {
                ecraAnterior = ecraAtual;
                for (int i = 0; i < ecras.length; i++) {
                    if (ecras[i] == proximo) {
                        ecraAtual = i;
                        break;
                    }
                }
            }
        }
    }
    
    public void ecraAnterior() {
        if (ecraAnterior >= 0 && ecraAnterior < ecras.length) {
            int temp = ecraAtual;
            ecraAtual = ecraAnterior;
            ecraAnterior = temp;
        }
    }

    public void close() {
        System.out.println("Fechando view...");
        ecraAtual = 0;
        ecraAnterior = -1;
    }
    
    public Ecra getEcraAtual() {
        if (ecraAtual >= 0 && ecraAtual < ecras.length) {
            return ecras[ecraAtual];
        }
        return null;
    }
    
    public void setEcraAtual(int index) {
        if (index >= 0 && index < ecras.length) {
            ecraAnterior = ecraAtual;
            ecraAtual = index;
        }
    }
}