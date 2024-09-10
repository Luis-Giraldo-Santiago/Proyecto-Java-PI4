package _soluciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _datos.DatosCafe;
import us.lsi.common.Multiset;

public class SolucionCafe {
	
	public static SolucionCafe of_Range(List<Integer> ls) {
		return new SolucionCafe(ls);
	}
	
	private Integer beneficio;
	private Map<String,Integer> solucion;
	
	private SolucionCafe() {
		beneficio = 0;
		solucion = new HashMap<>();
	}
	
	private SolucionCafe(List<Integer> ls) {
		beneficio = 0;
		solucion = new HashMap<>();
		for (int i = 0; i<ls.size(); i++) {
			if(ls.get(i)>0) {
				String v = DatosCafe.variedades.get(i);
				solucion.put(v, ls.get(i));
			}

			beneficio += ls.get(i)*DatosCafe.getBeneficio(i);
		}
	}
	
	public static SolucionCafe empty() {
		return new SolucionCafe();
	}
	
	public String toString() {
		return String.format("Cantidad de cada variedad = %s; Beneficio total = %s;", solucion, beneficio);
	}

}
