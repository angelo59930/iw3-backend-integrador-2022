package iua.kaf.Backend.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iua.kaf.Backend.integration.OrdenSlimView;
import iua.kaf.Backend.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

  public Optional<OrdenSlimView> findByNumeroOrden(long numOrden);

}
