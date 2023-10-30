package actividad1;

public class Pelicula {
	//variables pedidas en el enunciado
	private int idPel;
	private String tituloPel;
	private String directorPel;
	private double precioPel;
	
	//se crea el constructor vacio
	public Pelicula() {
		super();
	}
	
	
	//se crea el constructor con todos las variables
	public Pelicula(int idPel, String tituloPel, String directorPel, double precioPel) {
		super();
		this.idPel = idPel;
		this.tituloPel = tituloPel;
		this.directorPel = directorPel;
		this.precioPel = precioPel;
	}
	

	// se crea los getter an setter
	public int getIdPel() {
		return idPel;
	}

	public void setIdPel(int idPel) {
		this.idPel = idPel;
	}

	public String getTituloPel() {
		return tituloPel;
	}

	public void setTituloPel(String tituloPel) {
		this.tituloPel = tituloPel;
	}

	public String getDirectorPel() {
		return directorPel;
	}

	public void setDirectorPel(String directorPel) {
		this.directorPel = directorPel;
	}

	public double getPrecioPel() {
		return precioPel;
	}

	public void setPrecioPel(double precioPel) {
		this.precioPel = precioPel;
	}
	
	
	

}
