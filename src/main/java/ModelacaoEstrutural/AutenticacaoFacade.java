package ModelacaoEstrutural;

import java.util.HashMap;
import java.util.Map;

public class AutenticacaoFacade implements IAutenticacao {

    private Map<String, Utilizador> utilizadores;
    private static AutenticacaoFacade instance;

    private AutenticacaoFacade() {
        this.utilizadores = new HashMap<>();
    }

    public static AutenticacaoFacade getInstance() {
        if (instance == null) {
            instance = new AutenticacaoFacade();
        }
        return instance;
    }

    @Override
    public boolean registarGestor(String nome, String password, String restaurante, boolean coo) {
        if (utilizadores.containsKey(nome)) {
            return false;
        }
        Utilizador gestor = new Utilizador(nome, password, restaurante, coo);
        utilizadores.put(nome, gestor);
        return true;
    }

    @Override
    public boolean login(String userName, String pass) {
        Utilizador user = utilizadores.get(userName);
        if (user != null && user.getPassword().equals(pass)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCOO(Utilizador utilizador) {
        return utilizador != null && utilizador.isCoo();
    }
    
    public Utilizador getUtilizador(String nome) {
        return utilizadores.get(nome);
    }
}