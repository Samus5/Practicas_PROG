package biblioteca;

public class Ejemplar {

	private String Signatura;
	private int Ejemplar;
	private int Disponible;
	private String Editorial;
	private int AnioEdicion;
	private int ContPrest;
	public Ejemplar(String signatura, int ejemplar, int disponible, String editorial, int anioEdicion, int contPrest) {
		Signatura = signatura;
		Ejemplar = ejemplar;
		Disponible = disponible;
		Editorial = editorial;
		AnioEdicion = anioEdicion;
		ContPrest = contPrest;
	}
	public String getSignatura() {
		return Signatura;
	}
	public void setSignatura(String signatura) {
		Signatura = signatura;
	}
	public int getEjemplar() {
		return Ejemplar;
	}
	public void setEjemplar(int ejemplar) {
		Ejemplar = ejemplar;
	}
	public int getDisponible() {
		return Disponible;
	}
	public void setDisponible(int disponible) {
		Disponible = disponible;
	}
	public String getEditorial() {
		return Editorial;
	}
	public void setEditorial(String editorial) {
		Editorial = editorial;
	}
	public int getAnioEdicion() {
		return AnioEdicion;
	}
	public void setAnioEdicion(int anioEdicion) {
		AnioEdicion = anioEdicion;
	}
	public int getContPrest() {
		return ContPrest;
	}
	public void setContPrest(int contPrest) {
		ContPrest = contPrest;
	}

}
