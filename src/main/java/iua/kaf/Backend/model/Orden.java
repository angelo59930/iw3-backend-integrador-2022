package iua.kaf.Backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	//fecha de recepcion del sistema externo
	private Date fechaRecepcionExt;
	
	private Date fechaRecepcionPesaje;
	
	private Date fechaInicioCarga;
	
	private Date fechaFinCarga;
	
	private Date fechaPesajeFinal;
	
	private int estado;
	
	

}
