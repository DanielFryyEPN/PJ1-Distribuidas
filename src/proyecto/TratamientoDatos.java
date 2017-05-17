package proyecto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

public class TratamientoDatos {
	private ArrayList<HashMap<Character, Integer>> valores;
	private HashMap<Character, Integer> mapaA, mapaB;
	private double Na, Nb, Nc, T;
	
	public TratamientoDatos(HashMap<String, String> map) {
		valores = new ArrayList<HashMap<Character, Integer>>();
		test(map);
	}
	
	/**
	 * Obtiene los valores necesarios para enviar a la funcion T a partir del HashMap de los compuestos.
	 * @param map HashMap con las formulas limpias.
	 */
	private void test(HashMap<String, String> map) {
		Set<String> keys = map.keySet();
		TreeSet<String> sortedKeys = new TreeSet<String>(keys);
		ArrayList<String> keysList = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		for(String lines : sortedKeys) {
			keysList.add(lines);
			values.add(map.get(lines));
		}
		int index = 1;
		String path1 = System.getProperty("user.home") + "\\Downloads\\Documents\\mySolutions.tsv";
		String path0 = System.getProperty("user.dir") + "\\src\\archivos\\mySolutions.tsv";
		File mySolutions = new File(path1);
		try {
			FileWriter writer = new FileWriter(mySolutions);
			PrintWriter write = new PrintWriter(writer);
			write.printf("%s\t%s\t\t%s\t\t%s\n", "index", "compound_a", "compound_b", "value");
			for(int i = 0; i < values.size(); i++)
				for(int j = i + 1; j < values.size(); j++) {
					T = T(values.get(i), values.get(j));
					if (T >= 0.5 && index != 22)
						write.printf("%s\t%s\t%s\t%.2f\n", index++, keysList.get(i), keysList.get(j), T);
				}
			write.close();
		} catch (IOException e) {
			System.err.println("Error al escribir el archivo");;
		}
	}
	
	/**
	 * Calcula el coeficiente Jaccard/Tanimoto.
	 * @param a Formula quimica del compuesto a.
	 * @param b Formula quimica del compuesto b.
	 */
	private double T(String a, String b) {
		char[] compuestoA = a.toCharArray();
		char[] compuestoB = b.toCharArray();
		mapaA = sacarCaracteres(compuestoA);
		mapaB = sacarCaracteres(compuestoB);
		Na = numeroElementos(mapaA);
		Nb = numeroElementos(mapaB);
		Nc = numeroElementosComunes(mapaA, mapaB);
		T = Nc/(Na + Nb - Nc);
		//System.out.printf("Na = %-6sNb = %-6sNc = %-6sT = %.2f\n", Na, Nb, Nc, T);
		//mostrarMapa(mapaA);
		return T;
	}

	/**
	 * Crea un HashMap con los elementos encontrados en la formula quimica como clave y su frecuencia como valor.
	 * @param compuesto Lista con todos los elementos de la formula quimica.
	 * @return HashMap de los elementos y su frecuencia.
	 */
	private HashMap<Character, Integer> sacarCaracteres(char[] compuesto) {
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
		for(char l : compuesto) {
			if(charMap.containsKey(l)) {
				int contador = charMap.get(l);
				charMap.put(l, contador + 1);
			} else
				charMap.put(l, 1);
			if(charMap.containsKey('@'))
				charMap.put('@', 1);
		}
		return charMap;
	}
	
	/**
	 * Calcula el numero de elementos en el compuesto.
	 * @param map HashMap con los elementos de la formula quimica del compuesto.
	 * @return Numero de elementos del compuesto.
	 */
	private int numeroElementos(HashMap<Character, Integer> map) {
		Set<Character> keys = map.keySet();
		TreeSet<Character> sortedKeys = new TreeSet<Character>(keys);
		ArrayList<Integer> values = new ArrayList<Integer>();
		int N = 0;
		for(char lines : sortedKeys)
			values.add(map.get(lines));
		for(int lines : values)
			N += lines;
		return N;
	}
	
	/**
	 * Calcula el numero de elementos comunes entre los compuesto a y b.
	 * @param mapA HashMap con los elementos de la formula quimica del compuesto a.
	 * @param mapB HashMap con los elementos de la formula quimica del compuesto b.
	 * @return Numero de elementos comunes entre los compuesto a y b.
	 */
	private int numeroElementosComunes(HashMap<Character, Integer> mapA, HashMap<Character, Integer> mapB) {
		Set<Character> keysA = mapA.keySet();
		TreeSet<Character> sortedKeysA = new TreeSet<Character>(keysA);
		Set<Character> keysB = mapB.keySet();
		TreeSet<Character> sortedKeysB = new TreeSet<Character>(keysB);
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
		int menor;
		for(char linesA : keysA)
			for(char linesB : keysB)
				if(linesA == linesB) {
					menor = obtenerMenor(mapA.get(linesA), mapB.get(linesB));
					charMap.put(linesA, menor);
				}
		return numeroElementos(charMap);
	}

	/**
	 * Encuentra el menor entre dos numeros, si son iguales devuelve el primer valor.
	 * @param x Primer valor.
	 * @param y Segundo valor.
	 * @return Menor valor.
	 */
	private int obtenerMenor(int x, int y) {
		int z = 0; if(x == y) z = x; else if(x < y)	z = x; else	z = y; return z;
	}

	private void mostrarMapa(HashMap<Character, Integer> map) {
		Set<Character> keys = map.keySet();
		TreeSet<Character> sortedKeys = new TreeSet<Character>(keys);
		for(Character key : sortedKeys)
			System.out.printf( "%-4s%4s\n", key, map.get(key));
	}
}