package ejercicio1;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import _datos.DatosCafe;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {
	
	public static List<String> tiposCafes;
	public static List<Integer> cantidad;
	public static List<String> variedades;
	public static List<Integer> beneficio;
	public static List<List<Double>> porcentaje;
	
	
	public static Double getPorcentajeTipo(Integer i, Integer j) {
		Double res = 0.0;
		if(porcentaje.size()>i) {
			if(porcentaje.get(i).size()>j) {
				res = porcentaje.get(i).get(j);
			}
		}
		return res;
	}
	
	public static Integer getCantidad(Integer i) {
		return cantidad.get(i);
	}
	
	public static Integer getBeneficio(Integer j) {
		return beneficio.get(j);
	}
	
	public static Integer getN() {
		return tiposCafes.size();
	}
	
	public static Integer getM() {
		return variedades.size();
	}
	
	public static void ejercicio1_model() throws IOException{
		DatosCafe.iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");
		
		tiposCafes = DatosCafe.tiposCafes;
		variedades = DatosCafe.variedades;
		porcentaje = DatosCafe.porcentaje;
		cantidad = DatosCafe.cantidad;
		beneficio = DatosCafe.beneficio;
		
		AuxGrammar.generate(Ejercicio1PLE.class,"lsi_models/Ejercicio1.lsi","gurobi_models/Ejercicio1-3.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio1-3.lp");
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
		System.out.println(solution.values);
	}
	
	public static void main(String[] args) throws IOException {	
		ejercicio1_model();
		
	}

}
