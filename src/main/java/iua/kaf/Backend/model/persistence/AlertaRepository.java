package iua.kaf.Backend.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iua.kaf.Backend.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long>{
	public Optional<Alerta> findById(long id);
}
