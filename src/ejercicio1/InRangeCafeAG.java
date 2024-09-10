package ejercicio1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import _datos.DatosCafe;
import _soluciones.SolucionCafe;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.solve.AuxGrammar;

public class InRangeCafeAG implements ValuesInRangeData<Integer,SolucionCafe> {
	
	public InRangeCafeAG(String linea) {
		DatosCafe.iniDatos(linea);
	}
	
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}
	
	public Integer size() {
		return DatosCafe.getM();
	}
	
	public Integer min(Integer i) {
		return 0;
	}
	
	public Integer max(Integer i) {
		Integer res = 1;
		for(int j = 0; j < DatosCafe.getN(); j++) {
			if(DatosCafe.getPorcentajeTipo(i, j) > 0.0) {
				res += DatosCafe.getCantidad(j);
			}
		}
		return res;
	}
	
	public SolucionCafe solucion(List<Integer> ls) {
		return SolucionCafe.of_Range(ls);
	}
	
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0, error = 0, suma = 0;
		for(int i  = 0; i < size(); i++) {
			if(ls.get(i)>0) {
				goal += ls.get(i) * DatosCafe.getBeneficio(i); 
				
			}			
		}
		for(int j = 0; j < DatosCafe.getN(); j++) {
			suma = 0.;
			for(int i = 0; i < size(); i++) {
				suma += ls.get(i)*DatosCafe.getPorcentajeTipo(i, j);
			}
			if(suma>DatosCafe.getCantidad(j)) {
				error += suma - DatosCafe.getCantidad(j);
			}
		}
		
		
		return goal - 1000*error*error;
	}

}
