package co.edu.icesi.arqui.implementation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneradorDatos {

	//Por parametro se pasa el n�mero de datos
	public void generarDatos(int numeroDatos) throws IOException {

		long t1 = System.currentTimeMillis();

		// Incluye todas las may�sculas, min�sculas, y los car�cteres [ \ ] _ ` ^
		int leftLimit = 65; // ASCII letra A
		int rightLimit = 122; // ASCII lera z
		int targetStringLength = 60; // La longitud de las palabras va a ser 60

		int[] numeros = { 1, 2, 3, 4, 5, 7, 8, 9, 0 };

		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		// Si el archivo no existe se crea, si no se sobre escribe
		
		File archivo = new File("Datos/datos.txt");
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
		//Mirando cu�ndo se demora en generar, para genera 1000000 de datos se genera 2 segundos
		System.out.println("Ha tardado " + (t2 - t1) + "milisegundos");
	}

}
