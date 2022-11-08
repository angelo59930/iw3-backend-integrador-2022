package iua.kaf.Backend.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import iua.kaf.Backend.model.Camion;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long>{
	
}
