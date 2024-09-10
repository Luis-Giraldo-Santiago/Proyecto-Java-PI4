package _soluciones;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import _datos.DatosCursos;
import _datos.DatosCursos.Curso;
import us.lsi.common.List2;

public class SolucionCursos {
	
	public static SolucionCursos create(List<Integer> ls) {
		return new SolucionCursos(ls);
	}
	
	private Double precio;
	private List<Curso> cursos;
	
	private SolucionCursos(List<Integer> ls) {
		precio = 0.;
		cursos = List2.empty();
		for(int i=0; i<ls.size(); i++) {
			if(ls.get(i)>0) {
				precio += DatosCursos.getPrecio(i);
				cursos.add(DatosCursos.cursos.get(i));
			}
		}
	}
	
	public String toString() {
		String s = cursos.stream().map(e -> "S"+e.id())
		.collect(Collectors.joining(", ", "Cursos elegidos: {", "}\n"));
		return String.format("%sCoste Total: %.1f", s, precio);	
	}

}
