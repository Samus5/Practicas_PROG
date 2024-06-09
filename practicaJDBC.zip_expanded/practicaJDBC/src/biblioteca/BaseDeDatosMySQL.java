package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDeDatosMySQL {
	private static final String URL_TEMPLATE = "jdbc:sqlite:%s";
	private static String ruta;
	private static final String SQL_ADD_EJEMPLAR = "INSERT INTO EJEMPLARES (Signatura,Ejemplar,Disponible,Editorial,AnioEdicion,ContPrest) VALUES (?,?,?,?,?,?)";
	private static final String SQL_ADD_PRESTAMO = "INSERT INTO EJEMPLARES (Signatura,Ejemplar,Disponible,Editorial,AnioEdicion,ContPrest) VALUES (?,?,?,?,?,?)";
	private static final String SQL_QUERY_NUMERO_EJEMPLAR = "SELECT MAX(Ejemplar) AS numEjemplares FROM Ejemplares WHERE Signatura = ?";

	public BaseDeDatosMySQL(String ruta) {
		this.ruta = ruta;
	}

	public static void main(String[] args) {

		BaseDeDatosMySQL app = new BaseDeDatosMySQL("db/Libreria.db");
		Ejemplar ejemplo = new Ejemplar("D-001SISBD", 2, 1, "Lukas Grijander", 2024, 10);
		app.addEjemplar(ejemplo);
	}

	private void addEjemplar(Ejemplar ejemplar) {
		try (Connection conexion = getConnection()) {
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(SQL_ADD_EJEMPLAR);
			int numeroEjemplar = getUltimoNumeroEjemplarUsado(ejemplar.getSignatura()) + 1;

			sentenciaPreparada.setString(1, ejemplar.getSignatura());
			sentenciaPreparada.setInt(2, numeroEjemplar);
			sentenciaPreparada.setInt(3, ejemplar.getDisponible());
			sentenciaPreparada.setString(4, ejemplar.getSignatura());
			sentenciaPreparada.setInt(5, ejemplar.getAnioEdicion());
			sentenciaPreparada.setInt(6, 0);

			sentenciaPreparada.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int getUltimoNumeroEjemplarUsado(String signatura) {
		try (Connection conexion = getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(SQL_QUERY_NUMERO_EJEMPLAR)) {

			// Rellena la signatura
			sentencia.setString(1, signatura);
			int[] resultado = new int[1];
			resultado[0] = -1;
			procesaConsultaSimple(sentencia, new ProcesadorConsulta() {

				@Override
				public boolean procesaFila(ResultSet rs) throws SQLException {
					resultado[0] = rs.getInt("numEjemplares");
					return true;
				}

			});
			return resultado[0];
		} catch (SQLException e) {
			throw new BaseDatosException("Error accediendo a la base de datos", e);
		}
	}

	private static void procesaConsultaSimple(PreparedStatement sentencia, ProcesadorConsulta procesadorConsulta)
			throws SQLException {
		// Ejecuta la consulta
		ResultSet rs = sentencia.executeQuery();
		// Si hay resultado
		if (rs.next()) {
			// Procesa la fila
			procesadorConsulta.procesaFila(rs);
			// Cierra la consulta
			try {
				rs.close();
			} catch (Exception e) {
			}
		} else {
			// Error
			throw new BaseDatosException("procesaConsultaSimple: La consulta no devolvió resultados", null);
		}

	}

	private void addPrestamo(String signatura, int numEjemplar, int codSocio) {
		try (Connection conexion = getConnection()) {
			PreparedStatement sentenciaPreparada = conexion.prepareStatement(SQL_ADD_PRESTAMO);

			
		
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Connection getConnection() throws SQLException {
		String url = String.format(URL_TEMPLATE, ruta);
		return DriverManager.getConnection(url);
	}
}
