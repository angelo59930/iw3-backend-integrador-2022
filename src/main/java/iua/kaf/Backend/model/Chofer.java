package iua.kaf.Backend.model;

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
@Table(name = "choferes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chofer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = false, nullable = false)
	private String nombre;
	
	@Column(unique = false, nullable = false)
	private String apellido;
	
	@Column(unique = true, nullable = false)
	private long dni;
	
}
