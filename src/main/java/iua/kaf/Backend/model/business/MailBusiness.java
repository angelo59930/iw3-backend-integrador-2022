package iua.kaf.Backend.model.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import iua.kaf.Backend.model.Mail;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.MailRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailBusiness implements IMailBusiness{
	
	@Autowired
	private MailRepository mailDAO;
	
	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, String text) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@kaf.com.ar");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		try {
			emailSender.send(message);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	public void sendSimpleMessageToAll(String temperatura) {
		
		List<Mail> list = mailDAO.findAll();
		
		for (Mail mail : list) {
		
			sendSimpleMessage(mail.getMail(), "Aviso de temperatura", "La temperatura se ha excedido, temp = "+ temperatura);
			
		}
			
	}

	@Override
	public Mail load(long id) throws NotFoundException, BusinessException {
		Optional<Mail> r;
        try {
            r = mailDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra la orden con id=" + id).build();
        }

        return r.get();
	}

	@Override
	public List<Mail> list() throws BusinessException {
		 try {
	            return mailDAO.findAll();
	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	            throw BusinessException.builder().ex(e).build();
	        }
	}

	@Override
	public Mail add(Mail mail) throws FoundException, BusinessException {
		try {
            load(mail.getId());
            throw FoundException.builder().message("Se encontró el mail con id=" + mail.getId()).build();
        } catch (NotFoundException e) {
        }

        try {
            return mailDAO.save(mail);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
	}

	@Override
	public void delete(long id) throws NotFoundException, BusinessException {
		load(id);
        try {
            mailDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
		
	}
}
