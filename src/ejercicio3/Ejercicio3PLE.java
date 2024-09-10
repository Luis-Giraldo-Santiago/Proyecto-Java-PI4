package ejercicio3;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import _datos.DatosTrabajo;
import _datos.DatosTrabajo.*;
import ejercicio2.Ejercicio2PLE;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {
	
	public static List<Investigador> investigadores;
	public static List<Trabajo> trabajos;
	
	public static Integer getNumInvestigadores() {
		return investigadores.size();
	}
	
	public static Integer getNumEspecialidades() {
		return trabajos.get(0).especialidadDias().keySet().size();
	}
	
	public static Integer getNumTrabajos() {
		return trabajos.size();
	}
	
	public static Integer getEspecialidad(Integer i, Integer k) {
		Integer res = 0;
		if(investigadores.get(i).especialidad()==k) {
			res = 1;
		}
		return res;
	}
	
	public static Integer getDiasDisponibles(Integer i) {
		return investigadores.get(i).capacidad();
	}
	
	public static Integer getDiasNecesarios(Integer j, Integer k) {
		return trabajos.get(j).especialidadDias().get(k);
	}
	
	public static Integer getCalidad(Integer j) {
		return trabajos.get(j).calidad();
	}
	
	public static void ejercicio3_model() throws IOException{
		DatosTrabajo.iniDatos("ficheros/Ejercicio3DatosEntrada3.txt");
		
		investigadores = DatosTrabajo.investigadores;
		trabajos = DatosTrabajo.trabajos;
		
		AuxGrammar.generate(Ejercicio3PLE.class,"lsi_models/Ejercicio3.lsi","gurobi_models/Ejercicio3-3.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio3-3.lp");
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.)); 
		
	}
	

	public static void main(String[] args) throws IOException{
		ejercicio3_model();

	}

}
