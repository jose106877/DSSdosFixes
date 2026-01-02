package ModelacaoEstrutural;

public class Posto {

    private int id;
    private FuncaoPosto funcao;
    private EstadoPosto estado;
    
    public enum FuncaoPosto {
        PREPARACAO,
        COZINHA,
        MONTAGEM,
        EMPRATAMENTO
    }
    
    public enum EstadoPosto {
        LIVRE,
        OCUPADO,
        MANUTENCAO
    }

    public Posto(int id, FuncaoPosto funcao) {
        this.id = id;
        this.funcao = funcao;
        this.estado = EstadoPosto.LIVRE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FuncaoPosto getFuncao() {
        return funcao;
    }

    public void setFuncao(FuncaoPosto funcao) {
        this.funcao = funcao;
    }

    public EstadoPosto getEstado() {
        return estado;
    }

    public void setEstado(EstadoPosto estado) {
        this.estado = estado;
    }
    
    public boolean isDisponivel() {
        return estado == EstadoPosto.LIVRE;
    }
    
    @Override
    public String toString() {
        return "Posto #" + id + " - " + funcao + " (" + estado + ")";
    }
}