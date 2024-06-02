package prog.ud.practicaExamen.ud05;

public class Prueba {

	public static void main(String[] args) {
        Figura[] figuras = new Figura[4];
        figuras[0] = new Cuadrado(5);
        figuras[1] = new TrianguloEquilatero(6);
        figuras[2] = new TrianguloIsosceles(5, 8);
        figuras[3] = new TrianguloEscaleno(3, 4, 5);

        for (Figura figura : figuras) {
            System.out.println("Nombre: " + figura.getNombre());
            System.out.println("Área: " + figura.calcularArea());
            System.out.println("Perímetro: " + figura.calcularPerimetro());
            System.out.println();
        }
        for (Figura figura : figuras) {
            figura.escalar(2);
        }

        System.out.println("Después de escalar:");
        for (Figura figura : figuras) {
            System.out.println("Nombre: " + figura.getNombre());
            System.out.println("Área: " + figura.calcularArea());
            System.out.println("Perímetro: " + figura.calcularPerimetro());
            System.out.println();
        }
	
	}


}
