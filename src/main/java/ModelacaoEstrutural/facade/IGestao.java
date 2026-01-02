package ModelacaoEstrutural.facade;
import ModelacaoEstrutural.model.*;
public interface IGestao {

	void getIndicadoresGlobal();

	/**
	 * 
	 * @param id
	 */
	void getIndicadoresRestaurante(int id);

	MetricasGlobais calcularIndicadoresGlobal();

	/**
	 * 
	 * @param id
	 */
	Metricas calcularIndicadoresRestaurante(int id);

	/**
	 * 
	 * @param idRestaurante
	 * @param nome
	 * @param nif
	 */
	boolean registarFuncionario(int idRestaurante, String nome, int nif);

	/**
	 * 
	 * @param idRestaurante
	 */
	void emitirNotificacao(int idRestaurante);

	/**
	 * 
	 * @param nome
	 * @param idRestaurante
	 */
	void encomendarIngrediente(String nome, int idRestaurante);

}