package prog.ud.practicaExamen.ud05;

public class TrianguloIsosceles extends Triangulo {

	public TrianguloIsosceles(double ladoIgual, double base) {
		super("Triángulo Isósceles", ladoIgual, ladoIgual, base);
	}

	@Override
	public double calcularArea() {
		double altura = Math.sqrt(lado1 * lado1 - (lado3 / 2) * (lado3 / 2));
		return (lado3 * altura) / 2;
	}


}
