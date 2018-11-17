package co.edu.icesi.arqui.interfaces;


import org.osoa.sca.annotations.*;

@Service
public interface DistSort {	
	public String[] sort(String[] arr);
}
