package ejercicio2;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import _datos.DatosCursos;
import _datos.DatosCursos.Curso;
import ejercicio1.Ejercicio1PLE;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {
	
	public static Integer maxCentros;
	public static List<Curso> cursos;
	public static List<Integer> tematicas;
	
	public static Integer getNumCursos() {
		return cursos.size();
	}
	
	public static Integer getNumTematicas() {
		return tematicas.size();
	}
	
	public static Integer getNumCentros() {
		Set<Integer> centro = new HashSet<>();
		for(int i = 0; i<cursos.size(); i++) {
			centro.add(cursos.get(i).centro());
		}
		return centro.size();
	}
	
	public static Integer getMaxCentro() {
		return maxCentros;
	}
	
	public static Integer getTratada(Integer i, Integer j) {
		Integer res = 0;
		if(cursos.get(i).temas().contains(j+1)) {
			res = 1;
		}
		return res;
		
	}
	
	public static Double getPrecio(Integer i) {
		return cursos.get(i).costes();
	}
	
	public static Integer getImpartida(Integer i, Integer k) {
		Integer res = 0;
		if(cursos.get(i).centro()==k) {
			res=1;
		}
		return res;
	}
	
	public static void ejercicio2_model() throws IOException{
		DatosCursos.iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
		
		maxCentros = DatosCursos.maxCentros;
		cursos = DatosCursos.cursos;
		tematicas = DatosCursos.tematicas;

		
		AuxGrammar.generate(Ejercicio2PLE.class,"lsi_models/Ejercicio2.lsi","gurobi_models/Ejercicio2-1.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio2-1.lp");
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
	
	public static void main(String[] args) throws IOException{
		ejercicio2_model();
	}

}
