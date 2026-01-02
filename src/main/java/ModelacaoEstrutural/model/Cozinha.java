package ModelacaoEstrutural.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cozinha {

    private int id;
    private List<Posto> postos;
    private Queue<Pedido2> filaPedidos;
    private List<Ingrediente> ingredientesDisponiveis;

    public Cozinha(int id) {
        this.id = id;
        this.postos = new ArrayList<>();
        this.filaPedidos = new LinkedList<>();
        this.ingredientesDisponiveis = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void adicionarPosto(Posto posto) {
        postos.add(posto);
    }

    public void adicionarPedido(Pedido2 pedido) {
        filaPedidos.offer(pedido);
    }

    public Pedido2 proximoPedido() {
        return filaPedidos.poll();
    }

    public List<Posto> getPostos() {
        return new ArrayList<>(postos);
    }

    public Queue<Pedido2> getFilaPedidos() {
        return new LinkedList<>(filaPedidos);
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        ingredientesDisponiveis.add(ingrediente);
    }

    public List<Ingrediente> getIngredientesDisponiveis() {
        return new ArrayList<>(ingredientesDisponiveis);
    }
}