package iua.kaf.Backend.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iua.kaf.Backend.integration.OrdenSlimView;
import iua.kaf.Backend.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

  //FIXME:cambiar el nombre de este metodo
  public List<OrdenSlimView> findByOrdenByIdDesc();

}
