package iua.kaf.Backend.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iua.kaf.Backend.model.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle,Long> {
  
}
