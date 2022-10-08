package iua.kaf.Backend.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import iua.kaf.Backend.model.Chofer;

@Repository
public interface ChoferRepository extends JpaRepository<Chofer, Long>{

}
