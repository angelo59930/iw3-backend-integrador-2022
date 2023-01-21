package iua.kaf.Backend.model.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import iua.kaf.Backend.model.Mail;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long>{
	public Optional<Mail> findByMail(String nombreMail);
	
	public List<Mail> findAll();
}
