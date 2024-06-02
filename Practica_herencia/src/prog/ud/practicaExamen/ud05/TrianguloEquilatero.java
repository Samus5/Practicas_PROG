package prog.ud.practicaExamen.ud05;

public class TrianguloEquilatero extends Triangulo {

	public TrianguloEquilatero(double lado) {
		super("Triánqulo Equilátero", lado, lado, lado);
		// TODO Auto-generated constructor stub
	}

	@Override
	double calcularArea() {
		// TODO Auto-generated method stub
		return (Math.sqrt(3) / 4) * lado1 * lado1;
	}



	

}
