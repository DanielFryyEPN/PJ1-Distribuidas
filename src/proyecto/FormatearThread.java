package proyecto;

import java.util.ArrayList;
import java.util.HashMap;

public class FormatearThread implements Runnable{

	private ArrayList<String> leerArchivo;
	private HashMap<String, String> myMap;
	public FormatearThread(ArrayList<String> leerArchivo, HashMap<String, String> map) {
		this.leerArchivo = leerArchivo;
		this.myMap = map;
	}
	
	@Override
	public void run() {
		llenarMapa(leerArchivo);
		limpiarCadena(myMap);
	}

	private static synchronized void llenarMapa(ArrayList<String> leerArchivo) {
		for(int i = 1; i < leerArchivo.size(); i++) {
			String[] tokens = leerArchivo.get(i).split("\t");
			myMap.put(tokens[0], tokens[1]);
		}
	}
}
