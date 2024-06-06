package prog.ud07.practica;

public class Automovil extends Vehiculo implements Seguridad,Mantenimiento {
	protected final int ANYO_VALOR = 100;
	protected final double PORCENTAJE_IMPUESTO = 0.015;

	protected int numPuertas;

	protected Automovil(String  marca, String modelo, int anio, int numPuertas, String matricula, int km) {
		super(matricula, modelo, anio, marca,km);
		if (numPuertas > 0) {
			this.numPuertas = numPuertas;
		} else {
			throw new IllegalArgumentException("Las puertas deben ser mayores que 0");
		}
		// TODO Auto-generated constructor stub
	}

	public int getNumPuertas() {
		return numPuertas;
	}

	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}

	@Override
	protected double calcularImpuesto() {

		return obtenerValor() * PORCENTAJE_IMPUESTO;
	}

	@Override
	protected void mostrarDetalles() {

		super.mostrarDetalles();
		System.out.println(", Puertas: " + getNumPuertas());
		System.out.println();

	}

	@Override
	public int obtenerValor() {
		return anio * ANYO_VALOR;
	}

	@Override
	public void realizarMantenimiento() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void realizarInspeccionSeguridad() {
		// TODO Auto-generated method stub
		
	}

}
