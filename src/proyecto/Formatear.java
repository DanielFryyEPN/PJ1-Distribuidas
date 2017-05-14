package proyecto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Formatear {
	public static HashMap<String, String> myMap = new HashMap<String, String>();
	@SuppressWarnings("unused")
	private TratamientoDatos p;
	
	/**
	 * Crea un HashMap a partir de un archivo.
	 * @param path Ruta del archivo.
	 */
	public void leerArchivo(String path) throws InterruptedException{
		ArrayList<String> leerArchivo = new ArrayList<String>();
		try {
			leerArchivo = (ArrayList<String>) Files.readAllLines(Paths.get(System.getProperty("user.dir") + path));
		} catch (IOException e) {
			System.err.println("Error al leer el archivo");
		}
		
		/*Thread t1Archivo = new Thread(new FormatearThread(1, leerArchivo));
		Thread t2Archivo = new Thread(new FormatearThread(2, leerArchivo));
		
		t1Archivo.start();
		t2Archivo.start();
		t1Archivo.join();
		t2Archivo.join();
		
		/*Thread t1Limpiar = new Thread(new LimpiarCadena());
		Thread t2Limpiar = new Thread(new LimpiarCadena());
		
		t1Limpiar.start();
		t2Limpiar.start();
		
		t1Limpiar.join();
		t2Limpiar.join();*/
		
		llenarMapa(leerArchivo);
		//mostrarMapa(myMap);
		limpiarCadena(myMap);
		mostrarMapa(myMap);
		p = new TratamientoDatos(myMap);
	}
	
	private void llenarMapa(ArrayList<String> leerArchivo) {
		String[] tokens;
		for(int i = 1; i < leerArchivo.size(); i++) {
			tokens = leerArchivo.get(i).split("\t");
			myMap.put(tokens[0], tokens[1]);
		}
	}

	/**
	 * Elimina los caracteres especiales de las formulas.
	 * @param map HashMap de donde se obtienen las formulas.
	 */
	private void limpiarCadena(HashMap<String, String> map) {
		Set<String> keys = map.keySet();
		TreeSet<String> sortedKeys = new TreeSet<String>(keys);
		String formula, formulaLimpia;
		char[] elementos;
		for(String key : sortedKeys) {
			formulaLimpia = "";
			formula = map.get(key);
			elementos = formula.toCharArray();
			for(char l : elementos)
				if(String.valueOf(l).matches("[a-zA-Z@]"))
					formulaLimpia += l;
			myMap.put(key, formulaLimpia);
		}
	}

	/**
	 * Muestra en consola los compuestos y sus formulas.
	 * @param map HashMap de donde se obtienen los datos.
	 */
	private void mostrarMapa(HashMap<String, String> map) {
		Set<String> keys = map.keySet();
		TreeSet<String> sortedKeys = new TreeSet<String>(keys);
		for(String key : sortedKeys)
			System.out.printf( "%-16s%10s\n", key, map.get(key));
	}
}