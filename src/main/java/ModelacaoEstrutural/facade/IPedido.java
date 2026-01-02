package ModelacaoEstrutural.facade;
import ModelacaoEstrutural.model.*;
import java.util.List;

public interface IPedido {

	/**
	 * 
	 * @param numero
	 * @param artigos
	 * @param takeaway
	 */
	void criarPedido(int numero, List<Proposta> artigos, boolean takeaway);

	/**
	 * 
	 * @param pedido
	 */
	Talao emitirTalaoBalcao(Pedido pedido);

	/**
	 * 
	 * @param pedido
	 */
	Fatura emitirFatura(Pedido pedido);

	/**
	 * 
	 * @param pedido
	 */
	String emitirQRCode(Pedido pedido);

	/**
	 * 
	 * @param pedido
	 */
	int atribuirBalcao(Pedido pedido);

	/**
	 * 
	 * @param nome
	 */
	List<Proposta> filtrarAlergenio(String nome);

	List<Proposta> mostrarPropostas();

	List<Alergenio> mostrarAlergenios();

}