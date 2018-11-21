package co.edu.icesi.arqui.implementation;

import java.util.ArrayList;

public class Cliente {
	
	public static void main(String[] args) {
		Merger serv = new Merger();
		
		String[] arr = {"2","9","5","6","4","6","5","7","7","9","3","2","5","7","7","9","3","2","1","3","2","1"};		
		System.out.println("arr size: "+arr.length);
		
		String[] res =  serv.sort(arr);
		
		//ArrayList<ArrayList<String>> aux =serv.spliter(arr, 9);
		
		
		for(int i = 0; i < res.length; i++) {
			System.out.print(res[i]);
		}
		
		
	}
}
