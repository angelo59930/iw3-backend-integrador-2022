package iua.kaf.Backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false)
	private long numeroOrden;

	// fecha de recepcion del sistema externo
	@Column(nullable = false)
	private Date fechaRecepcionExt;

	@Column(nullable = false)
	private Date fechaRecepcionPesaje;

	@Column(nullable = false)
	private Date fechaInicioCarga;

	@Column(nullable = false)
	private Date fechaFinCarga;

	@Column(nullable = false)
	private Date fechaPesajeFinal;

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

	// FIXME: Asignar la columna correspondiente
	private int estado;

	// TODO: IMPLEMENTAR DATOS DE CARGA

}
