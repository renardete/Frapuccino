package co.edu.icesi.arqui.implementation;

import co.edu.icesi.arqui.interfaces.Merge42;
import co.edu.icesi.arqui.interfaces.DistSort1;
import co.edu.icesi.arqui.interfaces.DistSort2;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.osoa.sca.annotations.*;


public class Merger implements Merge42{
	

	@Reference(name="sortIzq")
	private DistSort1 sortIzq;

	@Reference(name="sortDer")
	private DistSort2 sortDer;

	public Merger() {
		System.out.println("Merger inicializado");
	}

	public final void init() {
		System.out.println("Se inicializO Merger");
	}

	@Override
	public String[] sort(String[] arr) {

		// Parto el arreglo a la mitad
		int mitad = (arr.length / 2);
				
		//Divide arreglo original
		ArrayList<String> lista = new ArrayList<String>(Arrays.asList(arr));
		ArrayList<String> lis = new ArrayList<String>(lista.subList(0, mitad)); //0-7
		ArrayList<String> lis2 = new ArrayList<String>(lista.subList(mitad, lista.size())); //8-15
				
		//Arreglos nuevos para ordenar
		String[] arreglo1 = lis.toArray(new String[lis.size()]);
		String[] arreglo2 = lis2.toArray(new String[lis2.size()]);
		
		//Definicion lista respuestas
		String[] respuesta1 = new String[arreglo1.length];
		String[] respuesta2 = new String[arreglo2.length];

		Hilo1 l = new Hilo1(respuesta1, arreglo1);
		Hilo2  r = new Hilo2(respuesta2,arreglo2);
		
		
		//Define resultado
		String[] result = null;

		try {
			//Inicia hilos.
                        l.start();
			            r.start();
			
			
			//Espera que terminen ejecucion
                        
			while(l.isAlive()){}
			while(r.isAlive()){}
		


			
			//Asigna respuesta
			respuesta1 = l.respuesta;
			respuesta2 = r.respuesta;
						
			//Junta respuestas
			ArrayList<String> aux = new ArrayList<String>(Arrays.asList(respuesta1));
			aux.addAll(new ArrayList<String>(Arrays.asList(respuesta2)));
			
			result = aux.toArray(new String[aux.size()]);
			
				//Hacemos merge para retornar la lista ordenada
                       merge(result, 0, mitad-1, result.length-1);
			
			
			//Hacemos merge para retornar la lista ordenada
			
						
			
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

	class Hilo1 extends Thread {

		public String[] respuesta;
		public String[] arreglo;
               

		public Hilo1(String[] respuesta, String[] arreglo) {
			this.respuesta = respuesta;
			this.arreglo = arreglo;
                       

		}

		@Override
		public void run() {
			respuesta = sortIzq.sort(arreglo);
                 

		}
	}
	class Hilo2 extends Thread {

		public String[] respuesta;
		public String[] arreglo;
             

		public Hilo2(String[] respuesta, String[] arreglo) {
			this.respuesta = respuesta;
			this.arreglo = arreglo;
                   
 

		}

		@Override
		public void run() {
			respuesta = sortDer.sort(arreglo);

		}
	}

	
}
