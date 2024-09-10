package ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import _datos.DatosReparto;
import _datos.DatosReparto.Camino;
import _soluciones.SolucionReparto;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Set2;

public class InRangeRepartoAG implements ValuesInRangeData<Integer, SolucionReparto>{
	
	public InRangeRepartoAG(String fichero) {
		DatosReparto.iniDatos(fichero);
	}
	
	public Integer size() {
		return DatosReparto.getNumVertices() + 1;
	}
	
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}
	
	public Integer max(Integer i) {
		return DatosReparto.getNumVertices();
	}
	
	public Integer min(Integer i) {
		return 0;
	}
	
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0, error = 0, km = 0;
		Set<Integer> camino = Set2.empty();
		for(int n = 0; n<ls.size() - 1;n++) {
			if(DatosReparto.existeArista(ls.get(n), ls.get(n+1))) {
				km = DatosReparto.getKilometro(ls.get(n), ls.get(n+1));
				goal += DatosReparto.getBeneficio(ls.get(n)) - km;
			}else{
				error++;
			}
			
		}
		if(!ls.get(0).equals(0)) {
			error++;
		}
		if(!ls.get(ls.size()-1).equals(0)) {
			error++;
		}
		camino.addAll(ls);
		int a = ls.size()-camino.size();
		if(a>0) {
			error += Math.abs(a);
		}
		return goal -1000*error*error;
		
		
	}

	@Override
	public SolucionReparto solucion(List<Integer> ls) {
		SolucionReparto res = SolucionReparto.empty();
		Double beneficio = 0.0;
		Double beneficioTeo = 0.0;
		Double km = 0.0;
		res.obtenerSolución(ls);
		for(int n = 0; n<ls.size()-1;n++) {
			beneficioTeo = DatosReparto.getBeneficio(ls.get(n));
			km += DatosReparto.getKilometro(ls.get(n), ls.get((n+1)));
			km += DatosReparto.getKilometro(ls.get((n+1)), ls.get(n));
			beneficio += beneficioTeo - km;
		}
		res.obtenerBeneficio(beneficio);
		res.obtenerKms(km);
		return res;
	}
	

}
