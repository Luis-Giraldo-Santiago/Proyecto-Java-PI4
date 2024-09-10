package ejercicio2;

import java.util.List;
import java.util.Set;

import _datos.DatosCursos;
import _soluciones.SolucionCursos;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.BinaryData;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public class BinCursosAG implements BinaryData<SolucionCursos>{
	
	public BinCursosAG(String fichero) {
		DatosCursos.iniDatos(fichero);
	}
	
	public Integer size() {
		return DatosCursos.getNumCursos();
	}
	
	public SolucionCursos solucion(List<Integer> ls) {
		return SolucionCursos.create(ls);
	}
	
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0, error = 0, error2 = 0;
		Set<Integer> tematicas = Set2.empty();
		Set<Integer> centros = Set2.empty();
		for(int i = 0; i < size(); i++) {
			if(ls.get(i) > 0) {
				goal += DatosCursos.getPrecio(i);
				tematicas.addAll(DatosCursos.cursos.get(i).temas());
				centros.add(DatosCursos.cursos.get(i).centro());
			}
		}
		error = Math.abs(DatosCursos.getNumTematicas() - tematicas.size());
		error2 = AuxiliaryAg.distanceToGeZero((double)DatosCursos.maxCentros - centros.size());
		return -goal -1000*error*error -1000*error2;
		
	}

}
