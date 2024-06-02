package prog.ud.practicaExamen.ud05;

public class Cuadrado extends Figura {

	double lado;
	
	public Cuadrado(double lado) {
		super("Cuadrado");
		if(lado <= 0) {
			throw new IllegalArgumentException("El lado no puede medir 0 o menor.");
		}
		this.lado = lado;
	}

	@Override
	double calcularArea() {
		return lado * lado;
	}

	@Override
	double calcularPerimetro() {
		return 4 * lado;
	}

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}

	@Override
	public void escalar(double escalado) {
	
		this.lado *= escalado;
	}

}
