package co.edu.icesi.shellsort.interfaces;

import java.util.ArrayList;
import org.osoa.sca.annotations.*;

@Service
public interface sort<E> {	
	public ArrayList<E> sort(ArrayList<E> arr);
}
