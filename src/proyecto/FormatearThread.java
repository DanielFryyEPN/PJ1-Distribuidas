package proyecto;

import java.util.ArrayList;
import static proyecto.Formatear.myMap;

public class FormatearThread implements Runnable{
	private ArrayList<String> leerArchivo;
	private int indice;
	
	public FormatearThread(int indice, ArrayList<String> leerArchivo) {
		this.indice = indice;
		this.leerArchivo = leerArchivo;
	}
	
	@Override
	public void run() {
		llenarMapa(leerArchivo);
	}

	private synchronized void llenarMapa(ArrayList<String> leerArchivo) {
		for(int i = indice; i < leerArchivo.size(); i+=2) {
			String[] tokens = leerArchivo.get(i).split("\t");
			myMap.put(tokens[1], tokens[3]);
		}
	}
}
