package prog.ud07.practica;

public class Main2 {
	public static void main(String[] args) {
	    // Crea un automovil, un camion y una motocicleta, calcula su valor e impuestos y muestra los detalles
	    Automovil automovil = new Automovil("Renault", "Clio", 2024, 5,"CHS4920",0);
	    Motocicleta motocicleta = new Motocicleta("Yamaha", "Choto",2020,"ZR500",  1500,10000);
	    Camion camion = new Camion("Pegaso","Master",2014,"HJ876", 5000, 12000);
	    
	    // Imprime los detalles y los impuestos
	    System.out.println("Detalles del automovil");
	    automovil.mostrarDetalles();
	    System.out.printf("El automovil paga en impuestos %.2f euros%n", automovil.calcularImpuesto());

	    System.out.println("Detalles de la motocicleta");
	    motocicleta.mostrarDetalles();
	    System.out.printf("La motocicleta paga en impuestos %.2f euros%n", motocicleta.calcularImpuesto());
	    
	    System.out.println("Detalles del camión");
	    camion.mostrarDetalles();
	    System.out.printf("El camion paga en impuestos %.2f euros%n", camion.calcularImpuesto());
	    
	   /*
	    // Realiza servicio en los tres vehiculos
	    realizarServicio(automovil);
	    realizarServicio(motocicleta);
	    realizarServicio(camion);
	  */
	  }
}
