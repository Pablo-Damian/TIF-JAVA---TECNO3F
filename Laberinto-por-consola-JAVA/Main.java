import java.util.Scanner;

public class Main {
  private static final char WALL = '▀';
  private static final char PATH = '·';
  private static final char START = '☻';
  private static final char EXIT = 'E';

  private char[][] laberinto = {
    { '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀' },
    { 'S', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀' },
    { '☻', '.', '.', '.', '.', '.', '▀', '▀', '.', '.', '.', '.', '▀', '▀', '.', '.', '.', '▀' },
    { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '▀', '▀', '.', '.', '.', '▀' },
    { '▀', '▀', '▀', '▀', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀' },
    { '▀', '▀', '▀', '▀', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '.', '.', '.', '.', '▀', '▀' },
    { '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀' },
    { '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀' },
    { '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀' },
    { '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.', '▀', '▀' },
    { '▀', '▀', '.', '.', '▀', '▀', '.', '.', '.', '.', '.', '.', '.', '.', '▀', '▀', '.', '.' },
    { '▀', '▀', '.', '.', '▀', '▀', '.', '.', '.', '.', '▀', '▀', '.', '.', '▀', '▀', '.', '.' },
    { '▀', '▀', '.', '.', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '.', '.', '▀', '▀', '.', '.' },
    { '▀', '▀', '.', '.', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '.', '.', '▀', '▀', '.', '.' },
    { '▀', '▀', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '▀', '▀', '.', '.' },
    { '▀', '▀', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '▀', '▀', '.', '.' },
    { '▀', '▀', '.', '.', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '.', '.', '.', 'E' },
    { '▀', '▀', '.', '.', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '.', '.', '▀', '▀' },
    { '▀', '▀', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '▀', '▀' },
    { '▀', '▀', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '▀', '▀' },
    { '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀' },
    { '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀', '▀' }

  };

  private int playerRow;
  private int playerCol;
  private long startTime;
  private Scanner scanner;

  public Main() {
    findPlayerStart();
    startTime = System.currentTimeMillis();
    scanner = new Scanner(System.in);
  }

  private void findPlayerStart() {
    for (int i = 0; i < laberinto.length; i++) {
      for (int j = 0; j < laberinto[0].length; j++) {
        if (laberinto[i][j] == START) {
          playerRow = i;
          playerCol = j;
          return;
        }
      }
    }
  }

  private void limpiarPantalla() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private void mostrarInfo() {
    System.out.println("LABERINTO JAVA\n");
    System.out.println("Jugador:\t\t☻");
    System.out.println("INICIO/START:\tS");
    System.out.println("SALIDA/EXIT:\tE\n");
  }

  private void printLaberinto() {
    limpiarPantalla();
    mostrarInfo();

    for (char[] row : laberinto) {
      for (char cell : row) {
        System.out.print(cell + " ");
      }
      System.out.println();
    }

    printElapsedTime();
  }

  private void printElapsedTime() {
    long elapsedTime = System.currentTimeMillis() - startTime;
    long minutes = (elapsedTime / 1000) / 60;
    long seconds = (elapsedTime / 1000) % 60;
    System.out.println("Tiempo del recorrido: " + minutes + " minutos " + seconds + " segundos");
  }

  private boolean isExit() {
    return laberinto[playerRow][playerCol] == EXIT;
  }

  private boolean isValidMove(int newRow, int newCol) {
    return newRow >= 0 && newRow < laberinto.length && newCol >= 0 && newCol < laberinto[0].length
        && laberinto[newRow][newCol] != WALL;
  }

  private void movePlayer(int newRow, int newCol) {
    laberinto[playerRow][playerCol] = PATH;
    playerRow = newRow;
    playerCol = newCol;
    laberinto[playerRow][playerCol] = START;
  }

  private boolean quieroVolverAJugar() {
    System.out.println("\n¿Quieres volver a jugar? (S/n)");
    String respuesta = scanner.nextLine().toLowerCase();
    return respuesta.equals("s");
  }

  public void play() {
    while (true) {
      printLaberinto();
      System.out.println("\nMovimientos");
      System.out.println("Arriba:\t\tA/a");
      System.out.println("Abajo:\t\tB/b");
      System.out.println("Izquierda:\tI/i");
      System.out.println("Derecha:\tD/d\n");

      String movimiento = scanner.nextLine().toLowerCase();
      int newRow = playerRow;
      int newCol = playerCol;

      switch (movimiento) {
        case "arriba":
        case "a":
          newRow--;
          break;
        case "abajo":
        case "b":
          newRow++;
          break;
        case "izquierda":
        case "i":
          newCol--;
          break;
        case "derecha":
        case "d":
          newCol++;
          break;
        default:
          System.out.println("Movimiento no válido. Intente de nuevo.");
          continue;
      }

      if (isValidMove(newRow, newCol)) {
        // Verificar si la nueva posición es la salida antes de mover al jugador
        if (laberinto[newRow][newCol] == EXIT) {
          System.out.println("¡Felicidades! Has llegado a la salida.");
          break; // Salir del bucle principal si el jugador llega a la salida
        }

        movePlayer(newRow, newCol);
      } else {
        System.out.println("Movimiento no válido. Intente de nuevo.");
      }
    }

    scanner.close(); // Cerrar el Scanner al finalizar
  }

  public static void main(String[] args) {
    Main juegoLaberinto = new Main();
    juegoLaberinto.play();
  }
}