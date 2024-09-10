package _datos;

import java.util.ArrayList;
import java.util.List;
import us.lsi.common.Files2;
import us.lsi.common.String2;


public class DatosCafe {
	
	public static List<String> tiposCafes = new ArrayList<>();
	public static List<Integer> cantidad = new ArrayList<>();
	public static List<String> variedades = new ArrayList<>();
	public static List<Integer> beneficio = new ArrayList<>();
	public static List<List<Double>> porcentaje = new ArrayList<>();
	
	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		lineas.remove("// TIPOS");
		lineas.remove("// VARIEDADES");
		List<String> tipos = new ArrayList<>();
		List<String> var = new ArrayList<>();
		for(String l:lineas) {
			if(l.startsWith("C")==true) {
				tipos.add(l);
			}else {
				var.add(l);
			}
		}
		for(int i = 0; i<tipos.size(); i++) {
			String[] tipo = tipos.get(i).split(":");
			String peso = tipo[1].trim().replace("kgdisponibles=", "");
			tiposCafes.add(tipo[0].trim());
			cantidad.add(Integer.valueOf(peso.replace(";", "")));
		}
		for(int i = 0; i<var.size(); i++) {
			String[] variedad = var.get(i).split(" -> ");
			String[] propiedades = variedad[1].split(";");
			variedades.add(variedad[0]);
			String ben = propiedades[0].trim().replace("beneficio=", "");
			beneficio.add(Integer.valueOf(ben));
			String comp = propiedades[1].trim().replace("comp=", "");
			String[] compo = comp.split(",");
			List<Double> componentes = new ArrayList<>();
			for(int j = 0; j<cantidad.size(); j++) {
				componentes.add(0.0);
			}
			for(String c:compo) {
				String[] compon = c.split(":");
				Integer num = Integer.valueOf(compon[0].replace("(C", ""));
				componentes.remove(num-1);
				componentes.add(num-1, Double.valueOf(compon[1].trim().replace(")", "")));
				
			}
			porcentaje.add(componentes);
		}
		toConsole();
		
	}

	public static List<String> getTipos(){
		return tiposCafes;
	}
	
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
	
	public static List<Double> getPorcentaje(Integer i){
		return porcentaje.get(i);
	}
	
	public static void toConsole() {
		String2.toConsole("Tipos de cafés: %s",tiposCafes);
		String2.toConsole("Cantidad de cada café: %s", cantidad);
		String2.toConsole("Variedades de cafés: %s", variedades);
		String2.toConsole("Beneficio de cada variedad de café: %s", beneficio);
		String2.toConsole("Porcentaje de cada café en cada variedad: %s", porcentaje);
		String2.toConsole(String2.linea());
		
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");

	}

}
