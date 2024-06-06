package prog.ud07.practica;

public class Motocicleta extends Vehiculo implements Seguridad,Mantenimiento{
	protected final int ANYO_VALOR = 50;
	protected final double PORCENTAJE_IMPUESTO = 0.01;

	protected int cilindrada;

	protected Motocicleta(String matricula, String modelo, int anio, String marca, int cilindrada,int km) {
		super(matricula, modelo, anio, marca,km);
		if (cilindrada > 0) {
			this.cilindrada = cilindrada;
		}
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	@Override
	protected double calcularImpuesto() {
		return obtenerValor() * PORCENTAJE_IMPUESTO;
	}

	@Override
	protected void mostrarDetalles() {

		super.mostrarDetalles();
		System.out.println(", Cilindrada: " + getCilindrada());
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
