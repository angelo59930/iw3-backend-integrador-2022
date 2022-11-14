package iua.kaf.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Conciliacion {
	
	double pesajeInicial;
	double pesajeFinal;
	
	double productoCargado;
	double netoPorBalanza;
	double diferenciaEntreBalanzaCaudalimetro;
	
	double promedioTemperatura;
	double promedioDensidad;
	double promedioCaudal;
	
}
