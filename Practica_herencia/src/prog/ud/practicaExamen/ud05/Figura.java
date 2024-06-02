package prog.ud.practicaExamen.ud05;

public abstract class Figura {
	String nombre;

	public Figura(String nombre) {
		if (nombre == null) {
			throw new NullPointerException();
		}
		if (nombre.isBlank()) {
			throw new IllegalArgumentException("Nombre vacio");
		}

		this.nombre = nombre;
	}

	abstract double calcularArea();

	abstract double calcularPerimetro();

	public abstract void escalar(double escalado);
		
	
	
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

}
