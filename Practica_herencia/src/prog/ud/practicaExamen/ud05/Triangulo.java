package prog.ud.practicaExamen.ud05;

public abstract class Triangulo extends Figura {

	protected double lado1;
	protected double lado2;
	protected double lado3;

	public Triangulo(String nombre, double lado1, double lado2, double lado3) {
		super(nombre);
		if (lado1 <= 0 || lado2 <= 0 || lado3 <= 0) {
			throw new IllegalArgumentException("Los lados no pueden ser menores o igual a cero");
		}

		this.lado1 = lado1;
		this.lado2 = lado2;
		this.lado3 = lado3;

	}

	@Override
	double calcularPerimetro() {
		return lado1 + lado2 + lado3;
	}

	@Override
	public void escalar(double factor) {
		this.lado1 *= factor;
		this.lado2 *= factor;
		this.lado3 *= factor;
	}

}
