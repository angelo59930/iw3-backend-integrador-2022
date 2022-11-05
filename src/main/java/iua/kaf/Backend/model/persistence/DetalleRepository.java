package iua.kaf.Backend.model.persistence;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import iua.kaf.Backend.model.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle,Long> {
/*  @Transactional
  @Modifying
  @Query(value = "UPDATE detalles SET estado = ? WHERE id = ?", nativeQuery = true)
  public Optional<Detalle> setEstado(int estado, long id);
*/
}
