package iua.kaf.Backend.model;

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
@Table(name = "detalles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Detalle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = true)
  private double ultMasaAcumulada;
  
  @Column(nullable = true)
  private double desidadProducto;
  
  @Column(nullable = true)
  private double tempProducto;
  
  @Column(nullable = true)
  private double caudal;

  @ManyToOne()
  @JoinColumn(name = "id_orden", nullable = false)
  private Orden orden;

}