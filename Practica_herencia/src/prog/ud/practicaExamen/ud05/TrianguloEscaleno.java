package prog.ud.practicaExamen.ud05;

public class TrianguloEscaleno extends Triangulo {

	public TrianguloEscaleno( double lado1, double lado2, double lado3) {
		super("Triángulo Escaleno", lado1, lado2, lado3);
		// TODO Auto-generated constructor stub
	}

	@Override
	double calcularArea() {
		// Con sp nos referimos al semiPerimetro, es decir su mitad
		double sp = (lado1 + lado2 + lado3) / 2;

		return Math.sqrt(sp * (sp - lado1) * (sp - lado2) * (sp - lado3));
	}



}
