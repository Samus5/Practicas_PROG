package prog.ud07.practica;

public abstract class Vehiculo implements Seguridad,Mantenimiento {

	protected String matricula;
	protected String modelo;
	protected int anio;
	protected String marca;
	protected int km;

	protected Vehiculo(String matricula, String modelo, int anio, String marca, int km) {

		if (matricula == null || modelo == null || anio <= 0 || marca == null || km < 0) {
			throw new IllegalArgumentException("Matriculas o modelo nulo. O año es igual o menor que cero");
		} else if (matricula.isBlank() || modelo.isBlank() || marca.isBlank()) {
			throw new IllegalArgumentException("Matricula o modelo o marca  en blanco");
		}
		this.matricula = matricula;
		this.anio = anio;
		this.modelo = modelo;
		this.marca = marca;
		this.km = km;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	protected abstract double calcularImpuesto();

	protected void mostrarDetalles() {
		System.out.println("Matricula: " + getMatricula() + ", Marca: " + getMarca() + ", Modelo: " + getModelo()
				+ ", Año: " + getAnio());
	};

	protected abstract int obtenerValor();

	public int obtenerKilometraje() {
		return km;
	}
	
	public void actualizarKilometraje(int kilom){
		this.km += kilom;
	}
		
	}	


