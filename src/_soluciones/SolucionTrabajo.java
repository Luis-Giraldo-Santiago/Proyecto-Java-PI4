package _soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import _datos.DatosTrabajo;
import _datos.DatosTrabajo.Trabajo;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class SolucionTrabajo {
	
	public static SolucionTrabajo of_Range(List<Integer> ls) {
		return new SolucionTrabajo(ls);
	}
	
	public static SolucionTrabajo empty() {
		return new SolucionTrabajo();
	}
	
	private Integer calidadTotal;
	private SortedMap<Integer, List<Integer>> solucion;
	
	private SolucionTrabajo(List<Integer> ls) {
		calidadTotal = 0;
		solucion = new TreeMap<>();
		List<Integer> diasTrabajo = List2.ofTam(0, DatosTrabajo.getNumTrabajos());
		for(int n = 0; n < ls.size(); n++) {
			int i = n%DatosTrabajo.getNumInvestigadores();
			int j = n/DatosTrabajo.getNumInvestigadores();
			diasTrabajo.set(j, diasTrabajo.get(j) + ls.get(n));
			if(solucion.containsKey(i)) {
				solucion.get(i).add(ls.get(n));
			}else {
				solucion.put(i, List2.of(ls.get(n)));
			}
		}
		for(int j = 0; j<diasTrabajo.size(); j++) {
			if(diasTrabajo.get(j)>0) {
				calidadTotal += DatosTrabajo.trabajos.get(j).calidad();
			}
		}
	}
	
	private SolucionTrabajo() {
		calidadTotal = 0;
		solucion = new TreeMap<>();
	}
	
	public void add(Integer i, Integer j) {
		if(solucion.containsKey(i)) {
			solucion.get(i).add(j);
		}else {
			solucion.put(i, List2.of(j));
		}
	}
	
	public void obtener(Integer i) {
		calidadTotal = i;
	}
	
	public String toString() {
		String s = String.format("\nSUMA DE LAS CALIDADES DE LOS TRABAJOS REALIZADOS: %s", calidadTotal);
		return solucion.entrySet().stream().map(e -> "INV"+(e.getKey()+1)+": "+ e.getValue()).collect(
				Collectors.joining("\n", "Reparto obtenido (días trabajados por cada investigador en cada\r\n"
						+ "trabajo):\n", s));
	}

}
