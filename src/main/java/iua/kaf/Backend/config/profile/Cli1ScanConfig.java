package iua.kaf.Backend.config.profile;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "iua.kaf.Backend", excludeFilters = {})

@EntityScan(basePackages = {
    "iua.kaf.Backend.model",
    "iua.kaf.Backend.auth",
    "iua.kaf.Backend.integration",
    "iua.kaf.Backend.model.business.IMailBusiness",
})

@Profile("cli1")
public class Cli1ScanConfig {

}
