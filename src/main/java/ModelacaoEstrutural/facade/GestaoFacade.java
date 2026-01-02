package ModelacaoEstrutural.facade;
import ModelacaoEstrutural.model.*;
import java.util.HashMap;
import java.util.Map;

public class GestaoFacade implements IGestao {
    
    private static GestaoFacade instance;
    private Map<Integer, Restaurante> restaurantes;
    private MetricasGlobais metricasGlobais;
    
    private GestaoFacade() {
        this.restaurantes = new HashMap<>();
        this.metricasGlobais = new MetricasGlobais();
    }
    
    public static GestaoFacade getInstance() {
        if (instance == null) {
            instance = new GestaoFacade();
        }
        return instance;
    }
    
    @Override
    public void getIndicadoresGlobal() {
        MetricasGlobais metricas = calcularIndicadoresGlobal();
        System.out.println("=== Indicadores Globais ===");
        System.out.println("Faturação Total: " + metricas.getFaturacaoTotal() + "€");
        System.out.println("Número de Pedidos: " + metricas.getNumeroPedidosTotal());
        System.out.println("Tempo Médio de Espera: " + metricas.getTempoMedioEsperaGlobal());
        System.out.println("===========================");
    }
    
    @Override
    public void getIndicadoresRestaurante(int id) {
        Metricas metricas = calcularIndicadoresRestaurante(id);
        if (metricas != null) {
            System.out.println("=== Indicadores Restaurante #" + id + " ===");
            System.out.println("Faturação: " + metricas.getFaturacao() + "€");
            System.out.println("Número de Pedidos: " + metricas.getNumeroPedidos());
            System.out.println("Tempo Médio de Espera: " + metricas.getTempoMedioEspera());
            System.out.println("===================================");
        }
    }
    
    @Override
    public MetricasGlobais calcularIndicadoresGlobal() {
        metricasGlobais.calcularMetricasGlobais();
        return metricasGlobais;
    }
    
    @Override
    public Metricas calcularIndicadoresRestaurante(int id) {
        Restaurante restaurante = restaurantes.get(id);
        if (restaurante != null) {
            Metricas metricas = new Metricas(id);
            Estatisticas stats = restaurante.getEstatisticas();
            metricas.setFaturacao(stats.getFaturacao());
            metricas.setTempoMedioEspera(stats.getTempoMedioEspera());
            return metricas;
        }
        return null;
    }
    
    @Override
    public boolean registarFuncionario(int idRestaurante, String nome, int nif) {
        Restaurante restaurante = restaurantes.get(idRestaurante);
        if (restaurante != null) {
            System.out.println("Funcionário " + nome + " registado no restaurante #" + idRestaurante);
            return true;
        }
        return false;
    }
    
    @Override
    public void emitirNotificacao(int idRestaurante) {
        Restaurante restaurante = restaurantes.get(idRestaurante);
        if (restaurante != null) {
            System.out.println("Notificação emitida para o restaurante #" + idRestaurante);
        }
    }
    
    @Override
    public void encomendarIngrediente(String nome, int idRestaurante) {
        Restaurante restaurante = restaurantes.get(idRestaurante);
        if (restaurante != null) {
            System.out.println("Ingrediente '" + nome + "' encomendado para o restaurante #" + idRestaurante);
        }
    }
    
    public void adicionarRestaurante(Restaurante restaurante) {
        restaurantes.put(restaurante.getId(), restaurante);
    }
    
    public Restaurante getRestaurante(int id) {
        return restaurantes.get(id);
    }
    
    public MetricasGlobais getMetricasGlobais() {
        return metricasGlobais;
    }
}