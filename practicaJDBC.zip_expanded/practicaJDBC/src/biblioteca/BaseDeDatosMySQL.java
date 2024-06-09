package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BaseDeDatosMySQL {
	private static final String URL_TEMPLATE = "jdbc:sqlite:%s";
	private static String ruta;
	private static final String SQL_ADD_EJEMPLAR = "INSERT INTO EJEMPLARES (Signatura,Ejemplar,Disponible,Editorial,AnioEdicion,ContPrest) VALUES (?,?,?,?,?,?)";
	private static final String SQL_ADD_PRESTAMO = "INSERT INTO EJEMPLARES (Signatura,Ejemplar,Disponible,Editorial,AnioEdicion,ContPrest) VALUES (?,?,?,?,?,?)";
	private static final String SQL_QUERY_NUMERO_EJEMPLAR = "SELECT MAX(Ejemplar) AS numEjemplares FROM Ejemplares WHERE Signatura = ?";
	private static final String SQL_COMPROBACION_PRESTAMO = "Select * from prestamos p inner join socios s on s.CodSocio = p.CodSocio where Signatura = ? and p.CodSocio = ? and p.Ejemplar = ? ";
	private static final String SQL_CONSULTA_DISPONIBILIDAD = "SELECT Disponible FROM ejemplares WHERE Signatura = ? AND Ejemplar = ?";
	private static final String SQL_INSERT_PRESTAMO = "INSERT INTO prestamos (Signatura, Ejemplar, CodSocio, FechaSalida, DiasLimite, FechaEntrada) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_COMPROBACION_PRESTAMO_CODPRESTAMO = "SELECT * FROM prestamos WHERE idPrestamo = ? ";
	private static final String SQL_UPDATE_DEVUELTA = "UPDATE prestamos SET FechaEntrada = ? WHERE idPrestamo = ?";
	private static final String SQL_DELETE_EJEMPLAR = "DELETE FROM EJEMPLARES WHERE Signatura = ? and Ejemplar = ? ";
	private static final String SQL_DELETE_PRESTAMOS = "DELETE FROM PRESTAMOS WHERE Signatura = ? and Ejemplar = ? ";
	private static final String SQL_LISTADO = "SELECT p.IdPrestamo, l.Titulo, l.Autor, e.Ejemplar, e.AnioEdicion, p.FechaSalida, p.FechaEntrada "
			+ "FROM prestamos p " + "INNER JOIN ejemplares e ON e.Signatura = p.Signatura "
			+ "INNER JOIN Libros l ON l.Signatura = p.Signatura";

	public BaseDeDatosMySQL(String ruta) {
		this.ruta = ruta;
	}

	public static void main(String[] args) {

		BaseDeDatosMySQL app = new BaseDeDatosMySQL("db/Libreria.db");
		// Ejemplar ejemplo = new Ejemplar("D-001SISBD", 2, 1, "Lukas Grijander", 2024,
		// 10);
		app.listadoPrestamos();
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

			System.out.println(numeroEjemplar);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getFechaActual() {
		LocalDateTime hoy = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return hoy.format(formatter);

	}

	private void addPrestamo(String signatura, int numEjemplar, int codSocio) {
		try (Connection conexion = getConnection()) {
			// Preparar la sentencia de comprobación
			try (PreparedStatement sentenciaComprobacion = conexion.prepareStatement(SQL_COMPROBACION_PRESTAMO)) {
				sentenciaComprobacion.setString(1, signatura);
				sentenciaComprobacion.setInt(2, codSocio);
				sentenciaComprobacion.setInt(3, numEjemplar);

				// Ejecutar la consulta para comprobar el préstamo
				try (ResultSet rs = sentenciaComprobacion.executeQuery()) {
					// Comprobar si el préstamo existe
					if (!rs.next()) {
						// Preparar la consulta para obtener la disponibilidad del ejemplar
						try (PreparedStatement consultaDisponibilidad = conexion
								.prepareStatement(SQL_CONSULTA_DISPONIBILIDAD)) {
							consultaDisponibilidad.setString(1, signatura);
							consultaDisponibilidad.setInt(2, numEjemplar);

							// Ejecutar la consulta de disponibilidad
							try (ResultSet rsDisponibilidad = consultaDisponibilidad.executeQuery()) {
								// Verificar si hay resultados
								if (rsDisponibilidad.next()) {
									int disponible = rsDisponibilidad.getInt("Disponible");
									if (disponible == 1) {
										// El ejemplar está disponible, preparar la inserción del préstamo
										try (PreparedStatement sentenciaPrestamo = conexion.prepareStatement(
												SQL_INSERT_PRESTAMO, Statement.RETURN_GENERATED_KEYS)) {
											sentenciaPrestamo.setString(1, signatura);
											sentenciaPrestamo.setInt(2, numEjemplar);
											sentenciaPrestamo.setInt(3, codSocio);
											sentenciaPrestamo.setString(4, getFechaActual());
											sentenciaPrestamo.setInt(5, 15); // Número de días para la devolución
											sentenciaPrestamo.setNull(6, Types.DATE);

											// Ejecutar la inserción del préstamo
											int filasAfectadas = sentenciaPrestamo.executeUpdate();

											// Verificar si se añadió el préstamo correctamente
											if (filasAfectadas > 0) {
												// Obtener el idPrestamo generado
												try (ResultSet generatedKeys = sentenciaPrestamo.getGeneratedKeys()) {
													if (generatedKeys.next()) {
														int idPrestamo = generatedKeys.getInt(1);
														System.out.println("ID del préstamo generado: " + idPrestamo);
													}
												}
											} else {
												System.out.println("No se pudo añadir el préstamo.");
											}
										}
									} else {
										System.out.println("Lo sentimos, pero este libro no está disponible.");
									}
								}
							}
						}
					} else {
						System.out.println("El préstamo ya existe.");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Considera registrar la excepción o manejarla más apropiadamente según las
			// necesidades de tu aplicación
		}
	}

	private void addDevolucion(int codPrestamo) {
		try (Connection conexion = getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(SQL_COMPROBACION_PRESTAMO_CODPRESTAMO)) {

			sentencia.setInt(1, codPrestamo);
			sentencia.executeQuery();

			ResultSet rs = sentencia.executeQuery();

			// Comprobar si el préstamo existe
			boolean prestamoExiste = rs.next();

			// Si el préstamo no existe, verificar la disponibilidad del ejemplar
			if (prestamoExiste) {
				PreparedStatement sentenciaUpdate = conexion.prepareStatement(SQL_UPDATE_DEVUELTA);
				sentenciaUpdate.setString(1, getFechaActual());
				sentenciaUpdate.setInt(2, codPrestamo);
				int alt = sentenciaUpdate.executeUpdate();
				if (alt != 1) {
					throw new SQLException("No hubo ninguna actualizacion");
				}

			} else {
				System.out.println("El prestamo con el codigo " + codPrestamo + " no existe");
			}

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
		ResultSet rs = sentencia.executeQuery();
		if (rs.next()) {
			procesadorConsulta.procesaFila(rs);
			try {
				rs.close();
			} catch (Exception e) {
			}
		} else {
			// Error
			throw new BaseDatosException("procesaConsultaSimple: La consulta no devolvió resultados", null);
		}

	}

	private void deleteEjemplar(String signatura, int numEjemplar) {
		try (Connection conexion = getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(SQL_DELETE_EJEMPLAR)) {

			sentencia.setString(1, signatura);
			sentencia.setInt(2, numEjemplar);

			int ls = sentencia.executeUpdate();

			if (ls == 0) {
				throw new SQLException("No se eliminó nada");
			} else {

				PreparedStatement sentenciaPrestamos = conexion.prepareStatement(SQL_DELETE_PRESTAMOS);
				sentenciaPrestamos.setString(1, signatura);
				sentenciaPrestamos.setInt(2, numEjemplar);
				sentenciaPrestamos.executeUpdate();
				System.out.println("Ejemplar de signatura " + signatura + "con nº de ejemplar " + numEjemplar
						+ "eliminado y sus prestamos");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws SQLException {
		String url = String.format(URL_TEMPLATE, ruta);
		return DriverManager.getConnection(url);
	}

	private void listadoPrestamos() {
		System.out.println("LISTADO DE PRESTAMOS");
		System.out.println();

		try (Connection conexion = getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(SQL_LISTADO)) {
			ResultSet rs = sentencia.executeQuery();

			while (rs.next()) {
				while (rs.next()) {
					int idPrestamo = rs.getInt("IdPrestamo");
					String titulo = rs.getString("Titulo");
					String autor = rs.getString("Autor");
					int ejemplar = rs.getInt("Ejemplar");
					int anioEdicion = rs.getInt("AnioEdicion");
					String fechaSalida = rs.getString("FechaSalida");
					String fechaEntrada = rs.getString("FechaEntrada");
					if (rs.wasNull()) {
						fechaEntrada = "N/A";
					}
					// Imprimir la información de cada préstamo
					System.out.println("IdPrestamo: " + idPrestamo);
					System.out.println("Titulo: " + titulo);
					System.out.println("Autor: " + autor);
					System.out.println("Ejemplar: " + ejemplar);
					System.out.println("Año de Edición: " + anioEdicion);
					System.out.println("Fecha de Salida: " + fechaSalida);
					System.out.println("Fecha de Entrada: " + fechaEntrada);
					System.out.println("-----------");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
