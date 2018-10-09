package co.edu.icesi.cliente.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

import co.edu.icesi.cliente.interfaces.distSort;

public class ClienteSort implements Runnable {

	
	private distSort<String> s;

	@Reference
	public final void setPrintService(distSort<String> service) {
		this.s = service;
	}

	public ClienteSort() {
		System.out.println("Cliente inicializado!");
	}

	@Init
	public final void init() {
		System.out.println("CLIENT initialized");
	}

	private ArrayList<String> readFile(String path) {
		ArrayList<String> arr = null;
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(path));
			String ln = inFile.readLine();
			arr = new ArrayList<String>(Arrays.asList(ln.split(";")));

		} catch (Exception e) {
			System.out.println("Ocurrio un error en la lectura del archivo");
			System.out.println(e.getMessage());
		}
		return arr;
	}

	public final void run() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		try {
			String path = in.readLine();
			ArrayList<String> arr = readFile(path);
			ArrayList<String> orderArr = s.distSort(arr);
			for (int i = 0; i < 10; i++) {
				out.write(orderArr.get(i) + "\n");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
