package co.edu.icesi.arqui.implementation;

import java.util.ArrayList;
import org.osoa.sca.annotations.*;

import co.edu.icesi.arqui.interfaces.DistSort;

public class BubbleSort implements DistSort {

	public BubbleSort() {
		System.out.println("Bubble Sort Server Created!");
	}

	public final String[] sort(final String[] arr) {

		int n = arr.length;
		String temp = "";
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1].compareTo(arr[j]) > 0) {
					// swap elements
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}

			}
		}
		return arr;
	}

}
