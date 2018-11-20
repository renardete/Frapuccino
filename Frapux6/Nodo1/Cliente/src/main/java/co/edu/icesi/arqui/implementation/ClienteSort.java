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

import co.edu.icesi.arqui.interfaces.Merge1;

public class ClienteSort implements Runnable {
	
	@Reference(name="distSort")
	private Merge1 s;

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
	
	public final void run() {
		String resultFile = "Datos/resultados.txt";		
				
		try {
			
			
				//BufferedWriter outFile = new BufferedWriter(new FileWriter(resultFile, true));			
				String[] arr = readFile("Datos/datos_"+26000000+".txt");
				
				System.out.println("Procesando arreglo: "+arr.length);
				
				long t1 = System.currentTimeMillis();
				
				String[] orderArr = s.sort(arr);
				
				long t2 = System.currentTimeMillis();
				
				//for(int u = 0; u < orderArr.length;u++){System.out.print(orderArr[u]);}
				
				//Guarda el tiempo de ordenamiento en el archivo.
				System.out.println("Ha tardado " + (t2 - t1) + " milisegundos");
				//outFile.append(10000000+","+(t2 - t1)+"\n");				
				//outFile.close();		
						
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
