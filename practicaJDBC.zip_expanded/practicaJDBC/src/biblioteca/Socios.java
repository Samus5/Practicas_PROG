package biblioteca;

public class Socios {
	private int codSocio;
	private String nombre;
	private int direccion;
	private int localidad;
	private int cp;
	private int telefono;
	private String fechaNacimiento;
	
	
	
	
	
	public Socios(int codSocio, String nombre, int direccion, int localidad, int cp, int telefono,
			String fechaNacimiento) {
		this.codSocio = codSocio;
		this.nombre = nombre;
		this.direccion = direccion;
		this.localidad = localidad;
		this.cp = cp;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getCodSocio() {
		return codSocio;
	}
	public void setCodSocio(int codSocio) {
		this.codSocio = codSocio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	public int getLocalidad() {
		return localidad;
	}
	public void setLocalidad(int localidad) {
		this.localidad = localidad;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
