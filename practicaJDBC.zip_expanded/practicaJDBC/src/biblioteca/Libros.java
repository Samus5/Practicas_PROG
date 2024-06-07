package biblioteca;

public class Libros {
	private String Signatura;
	private String Titulo;
	private String Autor;

	public String getSignatura() {
		return Signatura;
	}

	public void setSignatura(String signatura) {
		Signatura = signatura;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getAutor() {
		return Autor;
	}

	public void setAutor(String autor) {
		Autor = autor;
	}


	public Libros(String signatura, String titulo, String autor) {
		Signatura = signatura;
		Titulo = titulo;
		Autor = autor;
	}

}
