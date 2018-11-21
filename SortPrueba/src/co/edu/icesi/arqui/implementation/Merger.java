package co.edu.icesi.arqui.implementation;

import co.edu.icesi.arqui.interfaces.DistSort;
import co.edu.icesi.arqui.interfaces.MergerSort;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Merger implements MergerSort {

	private DistSort sort1;
	private DistSort sort2;
	private DistSort sort3;
	private DistSort sort4;
	private DistSort sort5;
	private DistSort sort6;
	
	private int div = 6;

	public Merger() {
		sort1 = new Sorter();
		sort2 = new Sorter();
		sort3 = new Sorter();
		sort4 = new Sorter();
		sort5 = new Sorter();
		sort6 = new Sorter();
		
		System.out.println("Merger inicializado");
	}
	
	//El splitter se comporta muy mal para un k > (tamañoArreglo /2)
	public ArrayList<ArrayList<String>> spliter(String[] arr, int k) {
		int lon = (arr.length/k);
		ArrayList<String> lista = new ArrayList<>(Arrays.asList(arr));
		ArrayList<ArrayList<String>> aux = new ArrayList<ArrayList<String>>(k);
		int i=0;
		for(int j = 1; j < k;i+=lon, j++) {
			aux.add(new ArrayList<>(lista.subList(i, i+lon)));
		}
		aux.add(new ArrayList<>(lista.subList(i, arr.length)));
		return aux;
	}
	
	private void kMerger(String[] arr, int k) {
		int lon = (arr.length/k);
		int m=lon, f = m+lon;
		for(int j = 1; j < k-1;m=f, f+=lon, j++) {			
			merge(arr, 0, m-1, f-1);			
		}
		f = arr.length-1;		
		merge(arr, 0, m-1, f);		
	}

	public final void init() {
		System.out.println("Se inicializó Merger");
	}

	@Override
	public String[] sort(String[] arr) {
		
		String[] r1 = null;
		String[] r2 = null;
		String[] r3 = null;
		String[] r4 = null;
		String[] r5 = null;
		String[] r6 = null;
	
		ArrayList<ArrayList<String>> arreglo = spliter(arr, div);
				
		// 0 1 2 | 3 4 5 
		
		HiloSorter h1 = new HiloSorter(r1, arreglo.get(0).toArray(new String[arreglo.get(0).size()]), sort1);
		HiloSorter h2 = new HiloSorter(r2, arreglo.get(1).toArray(new String[arreglo.get(1).size()]), sort2);
		HiloSorter h3 = new HiloSorter(r3, arreglo.get(2).toArray(new String[arreglo.get(2).size()]), sort3);
		HiloSorter h4 = new HiloSorter(r4, arreglo.get(3).toArray(new String[arreglo.get(3).size()]), sort4);
		HiloSorter h5 = new HiloSorter(r5, arreglo.get(4).toArray(new String[arreglo.get(4).size()]), sort5);
		HiloSorter h6 = new HiloSorter(r6, arreglo.get(5).toArray(new String[arreglo.get(5).size()]), sort6);
		
		//Define resultado
		String[] result = null;

		try {
			//Inicia hilos.
			h1.start();
			h2.start();
			h3.start();
			h4.start();
			h5.start();
			h6.start();
			
			//Espera terminen ejecución
			while(h1.isAlive()) {}
			while(h2.isAlive()) {}
			while(h3.isAlive()) {}
			while(h4.isAlive()) {}
			while(h5.isAlive()) {}
			while(h6.isAlive()) {}
			
			//Asigna respuesta
			r1 = h1.respuesta;
			r2 = h2.respuesta;
			r3 = h3.respuesta;
			r4 = h4.respuesta;
			r5 = h5.respuesta;
			r6 = h6.respuesta;
						
			//Junta respuestas	
			ArrayList<String> aux = new ArrayList<String>(Arrays.asList(r1));
			aux.addAll(new ArrayList<String>(Arrays.asList(r2)));
			aux.addAll(new ArrayList<String>(Arrays.asList(r3)));
			aux.addAll(new ArrayList<String>(Arrays.asList(r4)));
			aux.addAll(new ArrayList<String>(Arrays.asList(r5)));
			aux.addAll(new ArrayList<String>(Arrays.asList(r6)));
			
			result = aux.toArray(new String[aux.size()]);			
						
			//Hacemos merge para retornar la lista ordenada
			kMerger(result, div);
						
			
		} catch (Exception e) {
			e.printStackTrace();
		}

			

		return result;

	}
	
	private void merge(String[] arr, int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		String[] L = new String[n1];
		String[] R = new String[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i].compareTo(R[j]) <= 0) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}		
	}

	class HiloSorter extends Thread {

		public String[] respuesta;
		public String[] arreglo;
		private DistSort sorter;

		public HiloSorter(String[] respuesta, String[] arreglo, DistSort sorter) {
			this.respuesta = respuesta;
			this.arreglo = arreglo;
			this.sorter = sorter;
		}

		@Override
		public void run() {
			respuesta = sorter.sort(arreglo);			
		}
	}
	
	


}
