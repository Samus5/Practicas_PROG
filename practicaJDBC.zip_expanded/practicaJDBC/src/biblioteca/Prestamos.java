package biblioteca;

public class Prestamos {

	private int idPrestamo;
	private String signatura;
	private int ejemplar;
	private int codSocio;
	private String fechaSalida;
	private int diasLimite;
	private String fechaEntrada;

	public Prestamos(int idPrestamo, String Signatura, int Ejemplar, int CodSocio, String fechaSalida, int diasLimite,
			String fechaEntrada) {
		codSocio = idPrestamo;
		signatura = Signatura;
		ejemplar = Ejemplar;
		codSocio = CodSocio;
		this.fechaSalida = fechaSalida;
		this.diasLimite = diasLimite;
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getDiasLimite() {
		return diasLimite;
	}

	public void setDiasLimite(int diasLimite) {
		this.diasLimite = diasLimite;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int IdPrestamo) {
		idPrestamo = IdPrestamo;
	}

	public String getSignatura() {
		return signatura;
	}

	public void setSignatura(String Signatura) {
		signatura = Signatura;
	}

	public int getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(int Ejemplar) {
		ejemplar = Ejemplar;
	}

	public int getCodSocio() {
		return codSocio;
	}

	public void setCodSocio(int CodSocio) {
		codSocio = CodSocio;
	}

}
