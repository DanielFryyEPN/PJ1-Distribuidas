package proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class TratamientoDatos {
	private ArrayList<HashMap<Character, Integer>> valores;
	
	public TratamientoDatos(HashMap<String, String> map) {
		valores = new ArrayList<HashMap<Character, Integer>>();
		crearMapa(map);
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
	
	private void mostrarMapa(HashMap<Character, Integer> map) {
		Set<Character> keys = map.keySet();
		TreeSet<Character> sortedKeys = new TreeSet<Character>(keys);
		for(Character key : sortedKeys)
			System.out.printf( "%-4s%4s\n", key, map.get(key));
	}
}
