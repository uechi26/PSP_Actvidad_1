package actividad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClienteHilo implements Runnable {
	
	private Thread hilo;	// se crea un hilo por cada conexion
	private static int numCliente = 0;
	private Socket socketAlCliente;
	private Servidor servidor;
	
	
	public ClienteHilo(Thread hilo, Socket socketAlCliente, Servidor servidor) {
	super();
	numCliente++;
	hilo = new Thread(this, "Cliente_"+ numCliente);
	this.hilo = hilo;
	this.socketAlCliente = socketAlCliente;
	this.servidor = servidor;
	hilo.start();
	}
	
	public ClienteHilo() {
	super();
	}

		@Override
	public void run() {
			
		System.out.println("Estableciendo comunicacion con " + hilo.getName());
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;
		
		try {
            int opcion = Integer.parseInt(entradaBuffer.readLine());

			//Salida del servidor al cliente
			salida = new PrintStream(socketAlCliente.getOutputStream());
			//Entrada del servidor al cliente
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			entradaBuffer = new BufferedReader(entrada);
            
            
          switch (opcion) {
              case 1:
                  int id = Integer.parseInt(entradaBuffer.readLine());
                  List<Pelicula> peliculasPorID = servidor.consultarPeliculaPorID(id);
                  enviarPeliculas(salida, peliculasPorID);
                  break;
              case 2:
                  String titulo = entradaBuffer.readLine();
                  List<Pelicula> peliculasPorTitulo = servidor.consultarPeliculaPorTitulo(titulo);
                  enviarPeliculas(salida, peliculasPorTitulo);
                  break;
              case 3:
                  String director = entradaBuffer.readLine();
                  List<Pelicula> peliculasPorDirector = servidor.consultarPeliculaPorDirector(director);
                  enviarPeliculas(salida, peliculasPorDirector);
                  break;
              case 4:
                  int idNueva = Integer.parseInt(entradaBuffer.readLine());
                  String tituloNueva = entradaBuffer.readLine();
                  String directorNueva = entradaBuffer.readLine();
                  double precioNueva = Double.parseDouble(entradaBuffer.readLine());
                  Pelicula nuevaPelicula = new Pelicula(idNueva, tituloNueva, directorNueva, precioNueva);
                  servidor.agregarPelicula(nuevaPelicula);
                  break;
              default:
                  break;
          }
		
		} catch (IOException e) {
            e.printStackTrace();
        }                 	
	}
		
	private void enviarPeliculas(PrintStream salida, List<Pelicula> peliculas) {
		salida.println(peliculas.size());
	    for (Pelicula pelicula : peliculas) {
	    	salida.println(pelicula.getIdPel());
	    	salida.println(pelicula.getTituloPel());
	    	salida.println(pelicula.getDirectorPel());
	    	salida.println(pelicula.getPrecioPel());
	    }
	}
	
    public static List<Pelicula> leerPeliculas(BufferedReader entradaBuffer) throws IOException {
        int numPeliculas = Integer.parseInt(entradaBuffer.readLine());
    	//String numPeliculas = Integer.parseInt(entradaBuffer.readLine())
        List<Pelicula> peliculas = new ArrayList<>();

        for (int i = 0; i < numPeliculas; i++) {
            int id = Integer.parseInt(entradaBuffer.readLine());
           String titulo = entradaBuffer.readLine();
            String director = entradaBuffer.readLine();
           double precio = Double.parseDouble(entradaBuffer.readLine());
          peliculas.add(new Pelicula(id, titulo, director, precio));
            
        }
        return peliculas;
    }	
		
    public static void mostrarPeliculas(List<Pelicula> peliculas) {
        if (peliculas.isEmpty()) {
            System.out.println("No se encontraron películas.");
        } else {
            System.out.println("Peliculas encontradas:");
            for (Pelicula pelicula : peliculas) {
                System.out.println("ID: " + pelicula.getIdPel());
                System.out.println("Título: " + pelicula.getTituloPel());
                System.out.println("Director: " + pelicula.getDirectorPel());
                System.out.println("Precio: " + pelicula.getPrecioPel());
                System.out.println("---------------------------");
            }
        }
    }		
}
