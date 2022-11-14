package iua.kaf.Backend.integration.Cli1.model.persistence;

import iua.kaf.Backend.integration.Cli1.model.OrdenCli1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdenCli1Repository extends JpaRepository<OrdenCli1, Long> {

    public Optional<OrdenCli1> findByCodeCli1(String codeCli1);
}
