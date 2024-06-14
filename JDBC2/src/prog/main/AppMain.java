package prog.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AppMain {

	private static final String URL_BASE = "jdbc:sqlite:";
	private static final String SQL_ADD_PRODUCTO = "INSERT INTO productos (producto,proveedor_id,categoria_id,cantidad_por_unidad,precio_unidad,unidades_existencia,unidades_pedido,nivel_nuevo_pedido,suspendido) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE = "jdbc:sqlite:";
	private static final String SQL_COMPROBACION = "SELECT * FROM productos where producto = ?";
	private static final String SQL_GET_MAX_ID = "SELECT MAX(id) FROM productos";
	private static final String SQL_ADD_PRODUCTO2 = "UPDATE productos SET id = ? WHERE producto = ?";
	private static final String SQL_LISTADO = "SELECT * from pedidos";

	private String ruta;

	public AppMain(String rutadb) {

		this.ruta = rutadb;
	}

	public static void main(String[] args) {

		AppMain app = new AppMain("db/bd_neptuno.sqlite");

		app.listadoPedidos();
	}

	private void addProducto() {
		try (Connection conexion = getConexion()) {
			PreparedStatement sentencia = conexion.prepareStatement(SQL_ADD_PRODUCTO, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement sentenciaComprobacion = conexion.prepareStatement(SQL_COMPROBACION);
			PreparedStatement sentenciaId = conexion.prepareStatement(SQL_ADD_PRODUCTO2);
			Scanner sc = new Scanner(System.in);
			System.out.println("AÑADIR NUEVO PRODUCTO");
			System.out.println();

			System.out.println("Introduzca el nombre del producto:");
			String producto = sc.nextLine();

			if (producto == null || producto.isBlank() || producto.isEmpty()) {
				throw new IllegalArgumentException("Nombre del producto vacio");
			}

			sentenciaComprobacion.setString(1, producto);
			ResultSet rs = sentenciaComprobacion.executeQuery();
			if (rs.next()) {
				throw new IllegalArgumentException("Producto ya existente");
			}

			System.out.println("Introduzca el id_proveedor del producto:");
			int proveedor_id = sc.nextInt();
			sc.nextLine();
			if (proveedor_id < 0) {
				throw new IllegalArgumentException("id del proveedor vacio");
			}

			System.out.println("Introduzca el id_categoria del producto:");
			int categoria_id = sc.nextInt();
			sc.nextLine();

			if (categoria_id < 0) {
				throw new IllegalArgumentException("id_categorias producto vacio");
			}

			System.out.println("Introduzca la cantidad por unidad del producto:");
			String cantidad_por_unidad = sc.nextLine();

			if (cantidad_por_unidad == null || cantidad_por_unidad.isBlank() || cantidad_por_unidad.isEmpty()) {
				throw new IllegalArgumentException("Nombre del producto vacio");
			}

			System.out.println("Introduzca el precio_unidad del producto:");
			double precio_unidad = sc.nextDouble();

			if (precio_unidad < 0) {
				throw new IllegalArgumentException("Nombre del producto vacio");
			}

			System.out.println("Introduzca las unidades en existencia del producto:");
			int unidades_existencia = sc.nextInt();
			sc.nextLine();

			if (unidades_existencia < 0) {
				throw new IllegalArgumentException("Nombre del producto vacio");
			}

			System.out.println("Introduzca las unidades pedidas del producto: (Generalmente a 0 si se deja vacio)");
			int unidades_pedido = sc.nextInt();
			sc.nextLine();

			if (unidades_pedido < 0) {
				throw new IllegalArgumentException("No puede tener unidades de pedido menores a 0");
			}

			System.out.println("Introduzca el nivel_nuevo_pedido del producto:");
			int nivel_nuevo_pedido = sc.nextInt();
			sc.nextLine();

			if (nivel_nuevo_pedido < 0) {
				throw new IllegalArgumentException("Nombre del producto vacio");
			}

			System.out.println(
					"Introduzca el estado del producto: (s para suspendido, n o cualquiera para no. Si se deja vacio no esta suspendido)");
			int suspendido = sc.nextInt();

			sentencia.setString(1, producto); // Establecer el ID
			sentencia.setInt(2, proveedor_id);
			sentencia.setInt(3, categoria_id);
			sentencia.setString(4, cantidad_por_unidad);
			sentencia.setDouble(5, precio_unidad);
			sentencia.setInt(6, unidades_existencia);
			sentencia.setInt(7, unidades_pedido);
			sentencia.setInt(8, nivel_nuevo_pedido);
			sentencia.setInt(9, suspendido);

			int al = sentencia.executeUpdate();
			if (al != 1) {
				throw new IllegalArgumentException("No se realizó la inserción del producto");
			} else {
				ResultSet rs2 = sentencia.getGeneratedKeys();
				int id_Principal = rs2.getInt(1);
				System.out.println("Producto : " + producto + "De id: " + id_Principal + ", Añadido con éxito");
				sentenciaId.setInt(1, id_Principal);
				sentenciaId.setString(2, producto);
				int ideador = sentenciaId.executeUpdate();
				if (ideador != 1) {
					throw new IllegalArgumentException("El id no se aplicó bien arregrarlo con urgencia");
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void listadoPedidos() {
	    try (Connection conexion = getConexion()) {
	        PreparedStatement sentencia = conexion.prepareStatement(SQL_LISTADO);
	        ResultSet rs = sentencia.executeQuery();

	        System.out.println("PEDIDOS");
	        System.out.println("-------");
	        System.out.printf("%-6s%-11s%-12s%-15s%-15s%-15s%-10s%-10s%-30s%-20s%-15s%-10s%-10s\n",
	                "ID", "CLIENTE_ID", "EMPLEADO_ID", "FECHA_PEDIDO", "FECHA_ENTREGA", "FECHA_ENVIO", "ENVIO_ID", "CARGO", "DESTINATARIO", "DIRECCION", "CIUDAD", "REGION", "CP", "PAIS");

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int cliente_id = rs.getInt("cliente_id");
	            int empleado_id = rs.getInt("empleado_id");
	            String fecha_pedido = rs.getString("fecha_pedido");
	            String fecha_entrega = rs.getString("fecha_entrega");
	            String fecha_envio = rs.getString("fecha_envio");
	            int envio_id = rs.getInt("envio_id");
	            double cargo = rs.getDouble("cargo");
	            String destinatario = rs.getString("destinatario");
	            String direccion = rs.getString("direccion_destinatario");
	            String ciudad = rs.getString("ciudad_destinatario");
	            String region = rs.getString("region_destinatario");
	            String cp = rs.getString("cp_destinatario");
	            String pais = rs.getString("pais_destinatario");

	            System.out.printf("%-5d %-10d %-12d %-15s %-15s %-15s %-8d %-10.2f %-15s %-20s %-15s %-10s %-6s %-10s%n", 
	                    id, cliente_id, empleado_id, fecha_pedido, fecha_entrega, fecha_envio, envio_id, cargo, destinatario, direccion, ciudad, region, cp, pais);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	private Connection getConexion() throws SQLException {
		return DriverManager.getConnection(URL_BASE + ruta);
	}

}
