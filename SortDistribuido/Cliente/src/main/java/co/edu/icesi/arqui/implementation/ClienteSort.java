package co.edu.icesi.arqui.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

import co.edu.icesi.arqui.interfaces.DistSort;

public class ClienteSort implements Runnable {
	
	@Reference(name="distSort")
	private DistSort s;

	public ClienteSort() {
		System.out.println("Cliente inicializado!");
	}

	@Init
	public final void init() {
		System.out.println("CLIENT initialized");
	}

	private String[] readFile(String path) {
		String[] arr = null;
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(path));
			String ln = inFile.readLine();
			arr = ln.split(";");

		} catch (Exception e) {
			System.out.println("Ocurrio un error en la lectura del archivo");
			System.out.println(e.getMessage());
		}
		return arr;
	}
	
	//Por parametro se pasa el número de datos
		public void generarDatos(int numeroDatos) throws IOException {

			long t1 = System.currentTimeMillis();

			// Incluye todas las mayúsculas, minúsculas, y los carácteres [ \ ] _ ` ^
			int leftLimit = 65; // ASCII letra A
			int rightLimit = 122; // ASCII lera z
			int targetStringLength = 60; // La longitud de las palabras va a ser 60

			int[] numeros = { 1, 2, 3, 4, 5, 7, 8, 9, 0 };

			Random random = new Random();
			StringBuilder buffer = new StringBuilder(targetStringLength);
			// Si el archivo no existe se crea, si no se sobre escribe
			
			File archivo = new File("Datos/datos_10.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
			for (int j = 0; j < numeroDatos; j++) {

				for (int i = 0; i < targetStringLength; i++) {
					// Random para escribir una letra o un numero
					int x = random.nextInt(2);
					if (x == 0) {
						int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
						buffer.append((char) randomLimitedInt);
					} else {
						int pos = random.nextInt(9);
						buffer.append(numeros[pos] + "");
					}

				}
				String generatedString = buffer.toString();

				// Reseteo el string builder para que no concatene resultados anteriores
				buffer = new StringBuilder(targetStringLength);

				// Escribo en el archivo

				if (j == (numeroDatos-1))
					bw.write(generatedString);
				else
					bw.write(generatedString + ";");

			}
			bw.flush();
			bw.close();

			long t2 = System.currentTimeMillis();
			//Mirando cuándo se demora en generar, para genera 1000000 de datos se genera 2 segundos
			System.out.println("Ha tardado " + (t2 - t1) + "milisegundos");
		}

	public final void run() {
		String resultFile = "Datos/resultados.txt";		
				
		try {
			
			for (long tam = 200000; tam <= 1000000; tam += 200000) {
				BufferedWriter outFile = new BufferedWriter(new FileWriter(resultFile, true));			
				String[] arr = readFile("Datos/datos_"+tam+".txt");
				System.out.println("Procesando arreglo: "+arr.length);
				
				long t1 = System.currentTimeMillis();
				
				String[] orderArr = s.sort(arr);
				
				long t2 = System.currentTimeMillis();
				
				//Guarda el tiempo de ordenamiento en el archivo.
				System.out.println("Ha tardado " + (t2 - t1) + "milisegundos");
				outFile.append(tam+","+(t2 - t1)+"\n");				
				outFile.close();		
			}			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
