// Sistema de Tickets - Trabajo Integrador JAVA - TECNO3F
// Ticket.java
import java.io.Serializable;

public class Ticket implements Serializable {
  private int numeroTicket;
  private String nombre;
  private String sector;
  private String asunto;
  private String problema;
  private String fechaHoraCreacion;

  public Ticket(int numeroTicket, String nombre, String sector, String asunto, String problema,
      String fechaHoraCreacion) {
    this.numeroTicket = numeroTicket;
    this.nombre = nombre;
    this.sector = sector;
    this.asunto = asunto;
    this.problema = problema;
    this.fechaHoraCreacion = fechaHoraCreacion;
  }

  public int getNumeroTicket() {
    return numeroTicket;
  }

  public String getNombre() {
    return nombre;
  }

  public String getSector() {
    return sector;
  }

  public String getAsunto() {
    return asunto;
  }

  public String getProblema() {
    return problema;
  }

  public String getFechaHoraCreacion() {
    return fechaHoraCreacion;
  }
}
// FIN DEL CÓDIGO ;)