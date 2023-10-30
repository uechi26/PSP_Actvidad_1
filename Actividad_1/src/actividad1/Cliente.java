package actividad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class Cliente {
	// IP y Puerto del servidor a la que nos vamos a conectar
	public static final int PUERTO = 2018;
	public static final String IP_SERVER = "localhost";
	
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("        APLICACIÓN CLIENTE         ");
		System.out.println("-----------------------------------");
		
		//Implementamos una dirección IP del Socket con la clase InetSocketAdress
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		try (Scanner sc = new Scanner(System.in)){
			//declaramos la variable opcion
			int opcion;
			
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
			
			//creamos el puente entre el cliente y el servidor con la clase socket
			Socket socketAlServidor = new Socket();
			
			// con el metodo .connect conectamos el socket (socketAlServidor) al servidor
			socketAlServidor.connect(direccionServidor);
			
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + 
					" por el puerto " + PUERTO);
			
			// creamos un objeto llamado "entrada" con la clase InputStreamentrada
			// Un InputStreamReader es un puente entre flujos de bytes y flujos de caracteres
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			
			// Es aconsejable incluir un Bufferedentrada en cualquier lector cuyas operaciones
			// de lectura() puedan ser costosas, como Fileentradas y InputStreamentrada
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			//creamos el objeto que nos permite mandar informacion al servidor
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
			
			do {	
				// sacamos por pantalla el menú
	            System.out.println("Menú:");
	            System.out.println("1. Consultar película por ID");
	            System.out.println("2. Consultar película por título");
	            System.out.println("3. Consultar películas por director");
	            System.out.println("4. Añadir película");
	            System.out.println("5. Salir de la aplicación");
	            System.out.println("Seleccione una opción: ");
				
	            opcion = sc.nextInt();
	            sc.nextLine();
				
				switch(opcion) {
					case 1:
	                    System.out.print("\nIngrese el ID de la película: ");
	                    int idPel = sc.nextInt();
	                    salida.println(opcion);
	                    salida.println(idPel);
	                    List<Pelicula> peliculasPorID = ClienteHilo.leerPeliculas(entrada);
	                    ClienteHilo.mostrarPeliculas(peliculasPorID);
						break;
					case 2:
						System.out.print("\nIngrese el título de la película: ");
	                    String tituloPel = sc.nextLine();
	                    salida.println(opcion);
	                    salida.println(tituloPel);
	                    List<Pelicula> peliculasPorTitulo = ClienteHilo.leerPeliculas(entrada);
	                    ClienteHilo.mostrarPeliculas(peliculasPorTitulo);
						break;
					case 3:
						System.out.print("\nIngrese el director de la película: ");
	                    String directorPel = sc.nextLine();
	                    salida.println(opcion);
	                    salida.println(directorPel);
	                    List<Pelicula> peliculasPorDirector = ClienteHilo.leerPeliculas(entrada);
	                    ClienteHilo.mostrarPeliculas(peliculasPorDirector);
						break;
					case 4:
						System.out.print("\nIngrese el ID de la película: ");
	                    int idAdd = sc.nextInt();
	                    sc.nextLine();
	                    System.out.print("Ingrese el t�tulo de la película: ");
	                    String tituloAdd = sc.nextLine();
	                    System.out.print("Ingrese el director de la película: ");
	                    String directorAdd = sc.nextLine();
	                    System.out.print("Ingrese el precio de la película: ");
	                    double precioAdd = sc.nextDouble();
	                    salida.println(opcion);
	                    salida.println(idAdd);
	                    salida.println(tituloAdd);
	                    salida.println(directorAdd);
	                    salida.println(precioAdd);
	                    System.out.println("Película añadida exitosamente.");
						break;
					case 5:
						System.out.println("Saliendo de la aplicación.");
						break;
					default:
	                    System.out.println("Opción no válida. Inténtelo de nuevo.");
						break;
				}
			
			} while (opcion !=5);
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la direcci�n" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
		
		System.out.println("CLIENTE: Fin del programa");
		
		
		
		
	}

}
