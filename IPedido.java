package ModelacaoEstrutural;

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
	Talao emitirTalaoBalcao(ModelacaoEstrutural.Pedido pedido);

	/**
	 * 
	 * @param pedido
	 */
	ModelacaoEstrutural.Fatura emitirFatura(ModelacaoEstrutural.Pedido pedido);

	/**
	 * 
	 * @param pedido
	 */
	String emitirQRCode(ModelacaoEstrutural.Pedido pedido);

	/**
	 * 
	 * @param pedido
	 */
	int atribuirBalcao(ModelacaoEstrutural.Pedido pedido);

	/**
	 * 
	 * @param nome
	 */
	List<Proposta> filtrarAlergenio(String nome);

	List<Proposta> mostrarPropostas();

	List<Alergenio> mostrarAlergenios();

}