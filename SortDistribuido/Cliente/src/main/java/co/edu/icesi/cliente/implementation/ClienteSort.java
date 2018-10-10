package co.edu.icesi.cliente.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

import co.edu.icesi.cliente.interfaces.DistSort;

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

	public final void run() {
		System.out.println("Funciona hombre");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		try {
			//for (long s = 200000; s <= 1000000; s += 200000) {
				String[] arr = readFile("Datos/datos_10.txt");
				s.distSort(arr);
				/*
				ArrayList<String> orderArr = 
				for (int i = 0; i < 10; i++) {
					out.write(orderArr.get(i) + "\n");
				}	
				*/			
			//}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
