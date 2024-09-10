package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import _datos.DatosTrabajo;
import _soluciones.SolucionTrabajo;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public class InRangeTrabajoAG implements ValuesInRangeData<Integer, SolucionTrabajo>{
	
	public InRangeTrabajoAG(String fichero) {
		DatosTrabajo.iniDatos(fichero);
	}
	
	public Integer size() {
		return DatosTrabajo.getNumInvestigadores()*DatosTrabajo.getNumTrabajos();
	}
	
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}
	
	public Integer max(Integer n) {
		int i = n%DatosTrabajo.getNumInvestigadores();
		return (DatosTrabajo.investigadores.get(i).capacidad() + 1);
	}
	
	public Integer min(Integer i) {
		return 0;
	}
	
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0, error = 0, error2 = 0;
		Integer numInv = DatosTrabajo.getNumInvestigadores();
		Integer numTra = DatosTrabajo.getNumTrabajos();
		Integer numEsp = DatosTrabajo.getNumEspecialidades(); 
		List<Integer> diasInvestigador = List2.ofTam(0, numInv);
		for(int j = 0; j<numTra; j++) {
			Integer jj = j*numInv;
			List<Integer> trab = ls.subList(jj, jj+numInv);
			Boolean realiza = true;
			for(int k = 0; k<numEsp; k++) {
				Integer suma = 0;
				for(int i = 0; i<numInv; i++) {
					suma += trab.get(i)*DatosTrabajo.getEspecialidad(i, k);
					diasInvestigador.set(i, diasInvestigador.get(i) + trab.get(i));
				}
				if(suma != DatosTrabajo.getDiasNecesarios(j, k)) {
					realiza = false;
					error += Math.abs(suma - DatosTrabajo.getDiasNecesarios(j, k));
				}
			}
			if(realiza) {
				goal += DatosTrabajo.getCalidad(j);
			}
		}
		for(int i = 0; i<diasInvestigador.size(); i++) {
			int a = DatosTrabajo.getDiasDisponibles(i) - diasInvestigador.get(i);
			if(a<0) {
				error2 += Math.abs(a);
			}
		}
		return goal -10000000*error*error -1000*error2*error2;
	}
	
	public SolucionTrabajo solucion(List<Integer> ls) {
		SolucionTrabajo res = SolucionTrabajo.empty();
		Set<Integer> calidad = Set2.empty();
		Integer calidadTotal =0;
		for(int n = 0; n < ls.size(); n++) {
			int i = n%DatosTrabajo.getNumInvestigadores();
			int j = n/DatosTrabajo.getNumInvestigadores();
			res.add(i, ls.get(n));
			calidad.add(DatosTrabajo.trabajos.get(j).calidad());
		}
		calidadTotal = calidad.stream().collect(Collectors.summingInt( x -> x));
		res.obtener(calidadTotal);
		return res;
	}

}
