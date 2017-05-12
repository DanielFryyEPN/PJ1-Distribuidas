package proyecto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Formatear {
	private static HashMap<String, String> myMap;
	@SuppressWarnings("unused")
	private TratamientoDatos p;
	
	FormatearThread t1 = new FormatearThread(leerArchivo, map)
	
	/**
	 * Crea un HashMap a partir de un archivo.
	 * @param path Ruta del archivo.
	 */
	public void leerArchivo(String path) {
		ArrayList<String> leerArchivo = new ArrayList<String>();
		try {
			leerArchivo = (ArrayList<String>) Files.readAllLines(Paths.get(System.getProperty("user.dir") + path));
		} catch (IOException e) {
			System.err.println("Error al leer el archivo");
		}
		
		myMap = new HashMap<String, String>();
		
		llenarMapa(leerArchivo);
		//mostrarMapa(myMap);
		limpiarCadena(myMap);
		//mostrarMapa(myMap);
		p = new TratamientoDatos(myMap);
	}
	
	private static synchronized void llenarMapa(ArrayList<String> leerArchivo) {
		for(int i = 1; i < leerArchivo.size(); i++) {
			String[] tokens = leerArchivo.get(i).split("\t");
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