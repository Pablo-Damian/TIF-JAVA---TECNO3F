// Sistema de Tickets - Trabajo Integrador JAVA - TECNO3F
// Main.java
import java.io.File;
import java.util.Scanner;

public class Main {
  static final String DIR_REGISTRO = "registro";
  static final String ARCH_TICKETS = DIR_REGISTRO + "/tickets.csv";

  public static void main(String[] args) {
    File registroDir = new File(DIR_REGISTRO);
    if (!registroDir.exists()) {
      registroDir.mkdirs();
    }

    TicketManager ticketManager = new TicketManager();
    ticketManager.cargarTickets();

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Hola, bienvenido al sistema de Tickets en JAVA:");
      System.out.println("==============================================");
      System.out.println("1  -  Generar un Nuevo Ticket");
      System.out.println("2  -  Leer un Ticket");
      System.out.println("3  -  Salir");
      System.out.println("==============================================");
      System.out.print("Seleccione una Opción: ");

      String opcion = scanner.nextLine();

      switch (opcion) {
        case "1":
          ticketManager.altaTicket(scanner);
          break;
        case "2":
          ticketManager.leerTicket(scanner);
          break;
        case "3":
          if (ticketManager.confirmarSalida(scanner)) {
            ticketManager.guardarTickets();
            System.out.println("Gracias por utilizar este programa...");
            System.exit(0);
          }
          break;
        default:
          System.out.println("La Opción no es válida. Por favor, seleccione 1, 2 o 3.\n");
      }
    }
  }

  public static void limpiarPantalla() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
// FIN DEL CÓDIGO ;)