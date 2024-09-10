package _datos;

import java.io.IOException;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosReparto {
	
	public static record Cliente(Integer idCliente, Double beneficio) {
		public static Cliente create(String s) {
			String[]v = s.split(",");
			return new Cliente(Integer.valueOf(v[0].trim()), Double.valueOf(v[1].trim()));
		}
	}
	
	public static record Camino(Integer idCliente1, Integer idCliente2, Double kilometros) {
		public static Camino create(String s) {
			String[]e = s.split(",");
			return new Camino(Integer.valueOf(e[0].trim()),Integer.valueOf(e[1].trim()),
					Double.valueOf(e[2].trim()));
		}
	}
	
	public static List<Cliente> vertices;
	public static List<Camino> aristas;
	
	public static Integer getNumVertices() {
		return vertices.size();
	}
	
	public static Integer getNumAristas() {
		return aristas.size();
	}
	
	public static Cliente getOrigen() {
		return vertices.get(0);
	}
	
	public static Double getKilometro(Integer i, Integer j) {
		Double res = 0.;
		for(Camino c: aristas) {
			if(c.idCliente1().equals(i) && c.idCliente2().equals(j)) {
				res = c.kilometros();
			}
		}
		return res;
	}
	
	public static Double getBeneficio(Integer i) {
		return vertices.get(i).beneficio();
	}
	
	public static Boolean existeArista(Integer i, Integer j) {
		Boolean res = false;
		for(Camino c: aristas) {
			if(c.idCliente1().equals(i) && c.idCliente2().equals(j)) {
				res = true;
			}
			if(c.idCliente1().equals(j) && c.idCliente2().equals(i)) {
				res = true;
			}
		}
		return res;
	}
	
	public static void iniDatos(String fichero) {
		vertices = List2.empty();
		aristas = List2.empty();
		List<String> lineas = Files2.linesFromFile(fichero);
		for(String s: lineas) {
			if(s.equals("#VERTEX#") || s.equals("#EDGE#")) {
				
			}else {
				if(s.lastIndexOf(",")>1) {
					aristas.add(Camino.create(s));
				}else {
					vertices.add(Cliente.create(s));
				}
			}
		}
		toConsole();
	}
	
	public static void toConsole() {
		String2.toConsole("Clientes: %s",vertices);
		String2.toConsole("Caminos: %s",aristas);
		String2.toConsole(String2.linea());
	}
	
	public static void main(String[] args) throws IOException{
		iniDatos("ficheros/Ejercicio4DatosEntrada2.txt");
		
	}

}
