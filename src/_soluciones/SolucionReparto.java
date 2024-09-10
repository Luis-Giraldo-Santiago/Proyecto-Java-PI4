package _soluciones;

import java.util.ArrayList;
import java.util.List;

import _datos.DatosReparto;

public class SolucionReparto {
	
	public static SolucionReparto of_Range(List<Integer> ls) {
		return new SolucionReparto(ls);
	}
	
	public static SolucionReparto empty() {
		return new SolucionReparto();
	}
	
	private Double kmsTotal;
	private Double beneficioTotal;
	private List<Integer> solucion;
	
	private SolucionReparto(List<Integer> ls) {
		solucion.addAll(ls);
		for(int n = 0; n<ls.size() - 1; n++) {
			kmsTotal += DatosReparto.getKilometro(ls.get(n), ls.get(n+1));
			beneficioTotal += DatosReparto.getBeneficio(ls.get(n));
		}
	}
	
	private SolucionReparto() {
		kmsTotal = 0.;
		beneficioTotal = 0.;
		solucion = new ArrayList<>();
	}

	public void obtenerSolución(List<Integer> ls) {
		solucion = ls;
	}
	
	public void obtenerKms(Double i) {
		kmsTotal = i;
	}
	
	public void obtenerBeneficio(Double i) {
		beneficioTotal = i;
	}
	
	public String toString() {
		return "Camino desde "+ DatosReparto.getOrigen().idCliente()+" hasta "+DatosReparto.getOrigen().idCliente()
				+"\n"+solucion+"\nKilometros: "+ kmsTotal+"\nBeneficio: "+ beneficioTotal;
	}

}
