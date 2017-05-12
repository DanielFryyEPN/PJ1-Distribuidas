package proyecto;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import static proyecto.Formatear.myMap;

public class LimpiarCadena implements Runnable {
	/*private ArrayList<String> leerArchivo;
	private int indice;
	
	public LimpiarCadena(int indice, ArrayList<String> leerArchivo) {
		this.indice = indice;
		this.leerArchivo = leerArchivo;
	}*/
	
	@Override
	public void run() {
		limpiarCadena(myMap);
	}
	
	private synchronized void limpiarCadena(HashMap<String, String> map) {
		System.out.println(map.size());
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
}
