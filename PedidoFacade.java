package ModelacaoEstrutural;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoFacade implements IPedido {

    private static PedidoFacade instance;
    private List<Pedido2> pedidos;
    private List<Proposta> propostas;
    private List<Alergenio> alergenios;

    private PedidoFacade() {
        this.pedidos = new ArrayList<>();
        this.propostas = new ArrayList<>();
        this.alergenios = new ArrayList<>();
    }

    public static PedidoFacade getInstance() {
        if (instance == null) {
            instance = new PedidoFacade();
        }
        return instance;
    }

    @Override
    public void criarPedido(int numero, List<Proposta> artigos, boolean takeaway) {
        Pedido2 pedido = new Pedido2(numero);
        pedido.setTakeaway(takeaway);
        for (Proposta artigo : artigos) {
            pedido.adicionarArtigo(artigo);
        }
        pedidos.add(pedido);
        System.out.println("Pedido criado: #" + numero);
    }

    @Override
    public Talao emitirTalaoBalcao(Pedido pedido) {
        System.out.println("Emitindo talão de balcão para pedido: " + pedido);
        return new Talao(pedido);
    }

    @Override
    public Fatura emitirFatura(Pedido pedido) {
        System.out.println("Emitindo fatura para pedido: " + pedido);
        return new Fatura(pedido);
    }

    @Override
    public String emitirQRCode(Pedido pedido) {
        String qrCode = "QR-" + pedido.hashCode();
        System.out.println("QR Code gerado: " + qrCode);
        return qrCode;
    }

    @Override
    public int atribuirBalcao(Pedido pedido) {
        int balcao = (int) (Math.random() * 10) + 1;
        System.out.println("Balcão " + balcao + " atribuído ao pedido");
        return balcao;
    }

    @Override
    public List<Proposta> filtrarAlergenio(String nome) {
        System.out.println("Filtrando propostas sem o alergénio: " + nome);
        return propostas.stream()
                .filter(p -> !p.contemAlergenio(nome))
                .collect(Collectors.toList());
    }

    @Override
    public List<Proposta> mostrarPropostas() {
        return new ArrayList<>(propostas);
    }

    @Override
    public List<Alergenio> mostrarAlergenios() {
        return new ArrayList<>(alergenios);
    }
    
    public void adicionarProposta(Proposta proposta) {
        propostas.add(proposta);
    }
    
    public void adicionarAlergenio(Alergenio alergenio) {
        alergenios.add(alergenio);
    }
}