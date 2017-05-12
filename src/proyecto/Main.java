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
		p.leerArchivo("\\src\\archivos\\chemicals.tsv");
		double end = new Date().getTime();
		double time = (end - start)/1000;
		File file = new File(System.getProperty("user.dir") + "\\src\\archivos\\time.txt");
		try {
			FileWriter writer = new FileWriter(file);
			PrintWriter write = new PrintWriter(writer);
			write.print(time);
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(time);
	}
}
