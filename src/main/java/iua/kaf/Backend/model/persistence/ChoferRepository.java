package iua.kaf.Backend.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import iua.kaf.Backend.model.Chofer;
import java.util.Optional;

@Repository
public interface ChoferRepository extends JpaRepository<Chofer, Long>{
    public Optional<Chofer> findByDni(long dni);
}
