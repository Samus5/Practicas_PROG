package prog.ud07.practica;

public class Camion extends Vehiculo implements Seguridad, Mantenimiento {
	protected final int ANYO_VALOR = 200;
	protected final double PORCENTAJE_IMPUESTO = 0.02;

	protected int capacidadCarga;

	protected Camion(String marca, String modelo, int anio, String matricula, int km, int capacidadCarga) {
		super(marca, modelo, anio, matricula,km);
		if (capacidadCarga > 0) {
			this.capacidadCarga = capacidadCarga;
		}
	}

	public int getCapacidadCarga() {
		return capacidadCarga;
	}

	public void setCapacidadCarga(int capacidadCarga) {
		this.capacidadCarga = capacidadCarga;
	}

	@Override
	protected double calcularImpuesto() {
		return obtenerValor() * PORCENTAJE_IMPUESTO;
	}

	@Override
	protected void mostrarDetalles() {

		super.mostrarDetalles();
		System.out.println(", Capacidad de Carga: " + getCapacidadCarga());
		System.out.println();

	}

	@Override
	protected int obtenerValor() {
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
