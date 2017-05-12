package proyecto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		double start = new Date().getTime();
		Formatear p = new Formatear();
		try {
			p.leerArchivo("\\src\\archivos\\ZINC_chemicals.tsv");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		double end = new Date().getTime();
		double time = (end - start)/1000;
		System.out.println(time);
	}
}
