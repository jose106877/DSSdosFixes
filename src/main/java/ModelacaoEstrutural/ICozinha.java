package ModelacaoEstrutural;

public interface ICozinha {

	/**
	 * 
	 * @param pedido
	 */
	void realizarPedido(Pedido pedido);

	/**
	 * 
	 * @param pedido
	 * @param novaPosicao
	 * @param novoTempo
	 */
	void atrasarPedido(Pedido pedido, int novaPosicao, int novoTempo);

	/**
	 * 
	 * @param ingrediente
	 */
	void pedirIngrediente(Ingrediente ingrediente);

	/**
	 * 
	 * @param numeroEtapa
	 */
	void registarEtapaCompleta(int numeroEtapa);

	/**
	 * 
	 * @param pedido
	 */
	void fazerEntrega(Pedido pedido);

	/**
	 * 
	 * @param pedido
	 */
	void registarPedidoCompleto(Pedido pedido);

}