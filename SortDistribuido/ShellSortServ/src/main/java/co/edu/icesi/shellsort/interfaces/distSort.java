package co.edu.icesi.shellsort.interfaces;

import java.util.ArrayList;
import org.osoa.sca.annotations.*;

@Service
public interface distSort<E> {	
	public ArrayList<E> distSort(ArrayList<E> arr);
}
