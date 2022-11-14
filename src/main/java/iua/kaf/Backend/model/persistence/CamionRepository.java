package iua.kaf.Backend.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import iua.kaf.Backend.model.Camion;

import java.util.Optional;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long>{
	public Optional<Camion> findByPatente(String patente);
}
