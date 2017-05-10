package proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TratamientoDatos {
	private ArrayList<HashMap<Character, Integer>> valores;
	
	public TratamientoDatos(HashMap<String, String> map) {
		valores = new ArrayList<HashMap<Character, Integer>>();
		crearMapa(map);
		test(map);
	}
	
	private void crearMapa(HashMap<String, String> map) {
		Set<String> keys = map.keySet();
		TreeSet<String> sortedKeys = new TreeSet<String>(keys);
		String formula;
		char[] elementos;
		HashMap<Character, Integer> charMap;
		for(String key : sortedKeys) {
			charMap = new HashMap<Character, Integer>();
			formula = map.get(key);
			elementos = formula.toCharArray();
			for(char l : elementos) {
				if(charMap.containsKey(l)) {
					int contador = charMap.get(l);
					charMap.put(l, contador + 1);
				} else
					charMap.put(l, 1);
				if(charMap.containsKey('@'))
					charMap.put('@', 1);
			}
			valores.add(charMap);
		}
		
		for(HashMap<Character, Integer> lines : valores) {
			mostrarMapa(lines);
			System.out.println();
		}
	}
	
	private void test(HashMap<String, String> map) {
		Set<String> keys = map.keySet();
		TreeSet<String> sortedKeys = new TreeSet<String>(keys);
		ArrayList<String> values = new ArrayList<String>();
		String[] formulas = new String[keys.size()];
		char[] elementos;
		int index = 0;
		for(String lines : sortedKeys) {
			values.add(map.get(lines));
			System.out.println(values.get(index));
			index++;
		}
		index = 1;
		for(int i = 0; i < values.size(); i++)
			for(int j = i + 1; j < values.size(); j++) {
				T(values.get(i), values.get(j));
				System.out.println(index++ + "\t1 => " + values.get(i) + "\t2 => " + values.get(j));
			}
	}
	
	private void T(String a, String b) {
		char[] compuestoA = a.toCharArray();
		char[] compuestoB = b.toCharArray();
		HashMap<Character, Integer> mapaA = sacarCaracteres(compuestoA);
		HashMap<Character, Integer> mapaB = sacarCaracteres(compuestoB);
		int Na = numeroElementos(mapaA);
		int Nb = numeroElementos(mapaB);
		int Nc = numeroElementosComunes(mapaA, mapaB);
		System.out.println("Na = " + Na + "\tNb = " + Nb);
		mostrarMapa(mapaA);
	}

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
	
	private int numeroElementosComunes(HashMap<Character, Integer> mapA, HashMap<Character, Integer> mapB) {
		Set<Character> keysA = mapA.keySet();
		TreeSet<Character> sortedKeysA = new TreeSet<Character>(keysA);
		Set<Character> keysB = mapB.keySet();
		TreeSet<Character> sortedKeysB = new TreeSet<Character>(keysB);
		
		return 0;
	}

	private void mostrarMapa(HashMap<Character, Integer> map) {
		Set<Character> keys = map.keySet();
		TreeSet<Character> sortedKeys = new TreeSet<Character>(keys);
		for(Character key : sortedKeys)
			System.out.printf( "%-4s%4s\n", key, map.get(key));
	}
}
