package co.edu.icesi.arqui.implementation;

import co.edu.icesi.arqui.interfaces.DistSort;
import co.edu.icesi.arqui.interfaces.MergerSort;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class Merger implements MergerSort , Runnable{

	@Reference(name="distSort")
	private DistSort s;
	
	public Merger() {
		System.out.println("Merger inicializado");
	}
	
	@Init
	public final void init() {
		System.out.println("Se inicializó Merger");
	}
	
	@Override
	public String[] sort(String[] arr) {
		// TODO Auto-generated method stub
		
		//Parto el arreglo a la mitad
		
		
		
		
		int mitad = (arr.length/2);
		
			
		ArrayList<String> lista = (ArrayList<String>) Arrays.asList(arr);

		ArrayList<String> lis = (ArrayList<String>) lista.subList(0, mitad);
		ArrayList<String> lis2 = (ArrayList<String>) lista.subList(mitad, lista.size());
		
		 String [] arreglo1 = lis.toArray(new String[lis.size()]);
		 String [] arreglo2 = lis2.toArray(new String[lis2.size()]);
		 
		 String[] respuesta1 = new String [arreglo1.length];
		 String[] respuesta2 = new String [arreglo2.length];
		 Thread t = new Thread() {
	            @Override 
	            public void run() {
	            	System.out.println("Se empieza a ordenar la primera mitad");
	               respuesta1 = s.sort(arreglo1);
	            }
	      };
	      
	      Thread z = new Thread() {
	          @Override 
	          public void run() {
	           	System.out.println("Se empieza ordenar la segunda mitad");
	             respuesta2 = s.sort(arreglo2);
	          }};
	          
	          t.start();
	          z.start();
	          
	          while (t.isAlive()  ) {
				
				
			}
	          while(z.isAlive()) {
	        	  
	          }
	         
	          String[] result = Stream.of(respuesta1, respuesta2).flatMap(Stream::of).toArray(String[]::new);

	     return s.sort(result);
	          
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
	   
  
     System.out.println("Se está ejecutando");
      
      
		
	}

}
