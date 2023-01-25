package iua.kaf.Backend.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import iua.kaf.Backend.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long>{
	public Optional<Alerta> findById(long id);

	@Query(value = "SELECT temp_umbral FROM alertas WHERE alertas.id_orden = ? ORDER BY alertas.id DESC LIMIT 1;", nativeQuery = true)
	public Long getLastByOrden(long id);
}
