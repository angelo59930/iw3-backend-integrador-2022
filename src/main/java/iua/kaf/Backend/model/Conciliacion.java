package iua.kaf.Backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conciliaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conciliacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private double pesajeInicial;
	
	@Column(nullable = false)
	private double pesajeFinal;
	
	//ultimo valor de masa acumulada
	@Column(nullable = true)
	private double productoCargado;
	
	//pesajeFinal - pesajeInicial
	@Column(nullable = true)
	private double netoPorBalanza;
	
	//netoPorBalanza - productoCargado
	@Column(nullable = true)
	private double diferenciaBalanzaCaudalimetro;
	
	//calculado en base al detalle de orden
	@Column(nullable = true)
	private double promedioTemperatura;
	
	//calculado en base al detalle de orden
	@Column(nullable = true)
	private double promedioDensidad;
	
	//calculado en base al detalle de orden
	@Column(nullable = true)
	private double promedioCaudal;

	@OneToOne()
	@JoinColumn(name = "id_detalle", nullable = false)
	private Detalle detalle;



}
