package ModelacaoEstrutural.facade;
import ModelacaoEstrutural.model.*;
import java.util.HashMap;
import java.util.Map;

public class CozinhaFacade implements ICozinha {

    private Map<Integer, Cozinha> cozinhas;
    private static CozinhaFacade instance;

    private CozinhaFacade() {
        this.cozinhas = new HashMap<>();
    }

    public static CozinhaFacade getInstance() {
        if (instance == null) {
            instance = new CozinhaFacade();
        }
        return instance;
    }

    public void operation() {
        System.out.println("Operação da cozinha executada");
    }

    @Override
    public void realizarPedido(Pedido pedido) {
        System.out.println("Realizando pedido: " + pedido);
    }

    @Override
    public void atrasarPedido(Pedido pedido, int novaPosicao, int novoTempo) {
        System.out.println("Atrasando pedido para posição " + novaPosicao + " com novo tempo: " + novoTempo);
    }

    @Override
    public void pedirIngrediente(Ingrediente ingrediente) {
        System.out.println("Pedindo ingrediente: " + ingrediente.getNome());
    }

    @Override
    public void registarEtapaCompleta(int numeroEtapa) {
        System.out.println("Etapa " + numeroEtapa + " completa");
    }

    @Override
    public void fazerEntrega(Pedido pedido) {
        System.out.println("Fazendo entrega do pedido: " + pedido);
    }

    @Override
    public void registarPedidoCompleto(Pedido pedido) {
        System.out.println("Pedido completo registado: " + pedido);
    }

    public void adicionarCozinha(int id, Cozinha cozinha) {
        cozinhas.put(id, cozinha);
    }

    public Cozinha getCozinha(int id) {
        return cozinhas.get(id);
    }
}