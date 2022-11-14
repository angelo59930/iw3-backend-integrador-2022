package iua.kaf.Backend.model.persistence;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import iua.kaf.Backend.model.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {

  @Query(value = "SELECT * FROM ordenes as o WHERE o.id = ? AND o.password = ? ", nativeQuery = true)
  public Optional<Object> existPassword(long id, long password);
  
  @Query(value = "SELECT ult_masa_acumulada FROM detalles WHERE detalles.id_orden = ? ORDER BY detalles.id DESC LIMIT 1;", nativeQuery = true)
  public double ultimaMasaAcumulada(long id);
  
  @Query(value = "SELECT AVG(temp_producto) FROM detalles WHERE detalles.id_orden = ?", nativeQuery = true)
  public double promedioTemperatura(long id);
  
  @Query(value = "SELECT AVG(caudal) FROM detalles WHERE detalles.id_orden = ?", nativeQuery = true)
  public double promedioCaudal(long id);
  
  @Query(value = "SELECT AVG(desidad_producto) FROM detalles WHERE detalles.id_orden = ?", nativeQuery = true)
  public double promedioDensidad(long id);
}
