package biblioteca;

@FunctionalInterface
public interface ProcesadorConsulta {
	/**
	 * Interfaz que implementan los procesadores de consulta
	 */

	  boolean procesaFila(ResultSet rs) throws SQLException;
	

}
