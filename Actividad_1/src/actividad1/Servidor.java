package actividad1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Servidor {
	
	private List<Pelicula> peliculas;
	
    public Servidor() {
    	peliculas = new ArrayList<>();
        peliculas.add(new Pelicula(1, "Titanic ", "James Cameron", 14.0));
        peliculas.add(new Pelicula(2, "The Terminator", "James Cameron", 12.0));
        peliculas.add(new Pelicula(3, "Forrest Gump", "Robert Zemeckis", 18.0));
        peliculas.add(new Pelicula(4, "October Sky", "Joe Johnston", 13.50));
        peliculas.add(new Pelicula(5, "Snatch", "Guy Ritchie", 22.15));
    }
	
    public List<Pelicula> consultarPeliculaPorID(int id) {
        List<Pelicula> result = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getIdPel() == id) {
                result.add(pelicula);
            }
        }
        return result;
    }   
    
    public List<Pelicula> consultarPeliculaPorTitulo(String titulo) {
        List<Pelicula> result = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getTituloPel().equals(titulo)) {
                result.add(pelicula);
            }
        }
        return result;
    }    
    
    public List<Pelicula> consultarPeliculaPorDirector(String director) {
        List<Pelicula> result = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getDirectorPel().equals(director)) {
                result.add(pelicula);
            }
        }
        return result;
    }    
    
    public synchronized void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }    
    
    
	public static final int PUERTO = 2018;

	public static void main(String[] args) {
		
        Servidor servidor = new Servidor();
        
		System.out.println("      APLICACIÓN DE SERVIDOR CON HILOS     ");
		System.out.println("-------------------------------------------");		
		
		int peticion = 0;
		
		try (ServerSocket serverSocket = new ServerSocket()){			
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			serverSocket.bind(direccion);

			System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
			
			while (true) {
				Thread hilo = null;
				//Por cada peticion de cliente aceptada se me crea un objeto socket diferente
				Socket socketAlCliente = serverSocket.accept();
				System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");
				//Abrimos un hilo nuevo y liberamos el hilo principal para que pueda
				//recibir peticiones de otros clientes
				Thread clienteHilo = new Thread();
				new ClienteHilo(hilo, socketAlCliente, servidor);
				clienteHilo.start();
			}			
		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error");
			e.printStackTrace();
		}
		
		
		
	}
	

}
