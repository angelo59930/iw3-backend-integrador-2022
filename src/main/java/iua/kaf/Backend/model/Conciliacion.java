package iua.kaf.Backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private long pesajeInicial;
	
	@Column(nullable = false)
	private long pesajeFinal;
	
	//ultimo valor de masa acumulada
	@Column(nullable = true)
	private long productoCargado;
	
	//pesajeFinal - pesajeInicial
	@Column(nullable = true)
	private long netoPorBalanza;
	
	//netoPorBalanza - productoCargado
	@Column(nullable = true)
	private long diferenciaBalanzaCaudalimetro;
	
	//calculado en base al detalle de orden
	@Column(nullable = true)
	private long promedioTemperatura;
	
	//calculado en base al detalle de orden
	@Column(nullable = true)
	private long promedioDensidad;
	
	//calculado en base al detalle de orden
	@Column(nullable = true)
	private long promedioCaudal;

	@OneToOne()
	@JoinColumn(name = "id_detalle", nullable = false)
	private Detalle detalle;



}
