package ModelacaoEstrutural.facade;
import ModelacaoEstrutural.model.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// isto vai de pica, tens um facade por use case:
// - pedido
// - gestao
// - cozinha
// todos acedem ao restauranteDAO e retiram e atualizam a info que requerem
public class CadeiaFacade {

    private static CadeiaFacade instance;

    private AutenticacaoFacade autenticacaoFacade;
    private Map<Integer, PedidoFacade> pedidoFacades;

    private Map<Integer, Restaurante> restaurantes; // isto sera transmitido para o resto dos facades

    private CadeiaFacade() {
        this.autenticacaoFacade = AutenticacaoFacade.getInstance();
        this.pedidoFacades = new HashMap<>();
        this.restaurantes = new HashMap<>(); // TODO: ir buscar ao DAO
    }

    public static CadeiaFacade getInstance() {
        if (instance == null) {
            instance = new CadeiaFacade();
        }
        return instance;
    }

    public boolean login(String userName, String pass) {
        return autenticacaoFacade.login(userName, pass);
    }

    public boolean registarGestor(String nome, String password, String restaurante, boolean coo) {
        return autenticacaoFacade.registarGestor(nome, password, restaurante, coo);
    }

    public void criarPedido(int numero, List<Proposta> artigos, boolean takeaway, int restauranteID) {
        PedidoFacade pedidoFacade = pedidoFacades.get(restauranteID);
        if (pedidoFacade == null) {
            pedidoFacade = PedidoFacade.getInstance();
            pedidoFacades.put(restauranteID, pedidoFacade);
        }
        pedidoFacade.criarPedido(numero, artigos, takeaway);
    }
    
    public PedidoFacade getPedidoFacade(int restauranteID) {
        return pedidoFacades.get(restauranteID);
    }
}