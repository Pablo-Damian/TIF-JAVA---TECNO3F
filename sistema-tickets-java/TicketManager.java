// Sistema de Tickets - Trabajo Integrador JAVA - TECNO3F
// TicketManager.java
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TicketManager {
  static final String ARCH_TICKETS = Main.DIR_REGISTRO + "/tickets.csv";
  static final TimeZone ZONA_HORARIA = TimeZone.getTimeZone("America/Argentina/Buenos_Aires");

  private List<Ticket> tickets = new ArrayList<>();

  public void cargarTickets() {
    try (BufferedReader br = new BufferedReader(new FileReader(ARCH_TICKETS))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        Ticket ticket = new Ticket(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5]);
        tickets.add(ticket);
      }
    } catch (IOException | NumberFormatException e) {
      // Ignorar si hay un error al cargar los tickets
    }
  }

  public void guardarTickets() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCH_TICKETS))) {
      for (Ticket ticket : tickets) {
        String line = ticket.getNumeroTicket() + "," + ticket.getNombre() + "," + ticket.getSector() + ","
            + ticket.getAsunto() + "," + ticket.getProblema() + "," + ticket.getFechaHoraCreacion();
        bw.write(line);
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void altaTicket(Scanner scanner) {
    // Código de altaTicket
    System.out.println("Ingrese los datos para generar un Nuevo Ticket");
    System.out.println("----------------------------------------------");

    System.out.print("Ingrese su Nombre: ");
    String nombre = scanner.nextLine();

    System.out.print("Ingrese su Sector: ");
    String sector = scanner.nextLine();

    System.out.print("Ingrese el Asunto: ");
    String asunto = scanner.nextLine();

    System.out.print("Ingrese un Mensaje: ");
    String problema = scanner.nextLine();

    int numeroTicket = ThreadLocalRandom.current().nextInt(1000, 10000);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm");
    dateFormat.setTimeZone(ZONA_HORARIA);
    String fechaHoraCreacion = dateFormat.format(new Date());

    Ticket ticket = new Ticket(numeroTicket, nombre, sector, asunto, problema, fechaHoraCreacion);
    tickets.add(ticket);
    guardarTickets();

    Main.limpiarPantalla();

    System.out.println("=========================================");
    System.out.println("     Se generó el siguiente Ticket");
    System.out.println("=========================================");
    System.out.println(" Su Nombre:  " + nombre + "   Nº de Ticket: " + numeroTicket);
    System.out.println(" Sector:     " + sector);
    System.out.println(" Asunto:     " + asunto);
    System.out.println(" Mensaje:    " + problema + "\n");
    System.out.println(" Fecha y Hora de Creación: " + fechaHoraCreacion + "\n");
    System.out.println("     IMPORTANTE: Recuerde su número de Ticket\n");

    System.out
        .print("¿Desea generar un nuevo ticket? (S/s para confirmar o cualquier otro caracter para volver al menú): ");
    String respuesta = scanner.nextLine();
    Main.limpiarPantalla();
    if (respuesta.toLowerCase().equals("s")) {
      Main.limpiarPantalla();
      altaTicket(scanner);
    }
  }

  public void leerTicket(Scanner scanner) {
    // Código de leerTicket
    mostrarTickets();

    while (true) {
      System.out.print("Ingrese el número de ticket (ENTER sin escribir nada para cancelar y volver al menú): ");
      String numeroTicketInput = scanner.nextLine();

      if (numeroTicketInput.isEmpty()) {
        Main.limpiarPantalla();
        return;
      }

      try {
        int numeroTicket = Integer.parseInt(numeroTicketInput);
        Ticket ticketEncontrado = buscarTicket(numeroTicket);

        if (ticketEncontrado != null) {
          Main.limpiarPantalla();
          System.out.println("Ticket encontrado (creado el " + ticketEncontrado.getFechaHoraCreacion() + "):");
          System.out.println("__________________\n");
          System.out.println("Número de Ticket: " + ticketEncontrado.getNumeroTicket());
          System.out.println("Su Nombre: " + ticketEncontrado.getNombre());
          System.out.println("Sector: " + ticketEncontrado.getSector());
          System.out.println("Asunto: " + ticketEncontrado.getAsunto());
          System.out.println("Mensaje: " + ticketEncontrado.getProblema());
          System.out.println("__________________\n");
        } else {
          Main.limpiarPantalla();
          System.out.println("No se ha encontrado ningún Ticket con ese número.\n" +
              "------------------------------------------------\n");
        }

        System.out.print(
            "¿Desea leer otro ticket? (ingresar S/s para confirmar o cualquier otro caracter para volver al menú): ");
        String respuesta = scanner.nextLine();

        if (!respuesta.toLowerCase().equals("s")) {
          Main.limpiarPantalla();
          break;
        }
      } catch (NumberFormatException e) {
        Main.limpiarPantalla();
        System.out.println("Entrada no válida. Por favor, ingrese un número válido.\n");
        mostrarTickets();
      }
    }
  }

  private Ticket buscarTicket(int numeroTicket) {
    for (Ticket ticket : tickets) {
      if (ticket.getNumeroTicket() == numeroTicket) {
        return ticket;
      }
    }
    return null;
  }

  private void mostrarTickets() {
    System.out.println("Leer Ticket, listado de números disponibles:");
    System.out.println("-------------------------------------------");
    for (Ticket ticket : tickets) {
      System.out.println(ticket.getNumeroTicket() + "\n----");
    }
  }

  public boolean confirmarSalida(Scanner scanner) {
    System.out.print("¿Está seguro de que desea salir? (S/s para confirmar, cualquier otro caracter para cancelar): ");
    String respuesta = scanner.nextLine();
    Main.limpiarPantalla();
    return respuesta.trim().equalsIgnoreCase("s");
  }
}
// FIN DEL CÓDIGO ;)