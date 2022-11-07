package iua.kaf.Backend.model.persistence;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import iua.kaf.Backend.model.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {
  @Transactional
  @Modifying
  @Query(value = "UPDATE detalles SET estado = ? WHERE id = ?", nativeQuery = true)
  public Optional<Detalle> setEstado(int estado, long id);

  @Transactional
  @Modifying
  @Query(value = "SELECT * FROM detalle as d INNER JOIN ordenes as o ON d.id_orden = o.id WHERE d.id = ? AND o.password = ?", nativeQuery = true)
  public Boolean existPassword(long id, long password);

}
