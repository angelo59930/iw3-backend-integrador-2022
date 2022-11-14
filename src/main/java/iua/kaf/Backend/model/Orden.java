package iua.kaf.Backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ordenes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false)
	private long numeroOrden;

	// Generar cuando le llama al endpoint del codigo externo
	@Column(nullable = true)
	private Date fechaRecepcionExt;


	// se genera cuando se llama al endpoint de la tara
	@Column(nullable = true)
	private Date fechaRecepcionPesaje;


	// se genera con el endpoint del sistema externo
	@Column(nullable = true)
	private Date fechaCargaPrevista;


	// se genera con el primer detalle ( con el post )
	@Column(nullable = true)
	private Date fechaInicioCarga;


	// se genera en el endpoint de actualizacion del detalle cuando
	// el atributo de ultmasaacumulada = preset
	@Column(nullable = true)
	private Date fechaFinCarga;

	
	// se genera en el endpoint de cierre de orden
	@Column(nullable = true)
	private Date fechaPesajeFinal;

	@Column(nullable = false)
	private double preset;

	@Column(nullable = true)
	private double tara;
	
	@Column(nullable = true)
	private double pesajeFinal;
	
	@Column(nullable = true)
	private int estado;

	//Solo 5 digitos como maximo
	@Column(nullable = true)
	private long password;

	@ManyToOne
	@JoinColumn(name = "id_camion", nullable = false)
	private Camion camion;

	@ManyToOne
	@JoinColumn(name = "id_chofer", nullable = false)
	private Chofer chofer;

	@ManyToOne
	@JoinColumn(name = "id_cliente",nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false)
	private Producto producto;

}
