package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iua.kaf.Backend.model.Alerta;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.AlertaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlertaBusiness implements IAlertaBusiness{
	
	@Autowired
	private AlertaRepository alertaDAO;

	public Alerta load(long id) throws NotFoundException, BusinessException {
        Optional<Alerta> r;
        try {
            r = alertaDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra la alerta con id=" + id).build();
        }

        return r.get();
	}

	@Override
	public List<Alerta> list() throws BusinessException {
		 try {
	            return alertaDAO.findAll();
	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	            throw BusinessException.builder().ex(e).build();
	        }	}

	@Override
	public Alerta add(Alerta alerta) throws FoundException, BusinessException {
		try {
            load(alerta.getId());
            throw FoundException.builder().message("Se encontró la alerta con id=" + alerta.getId()).build();
        } catch (NotFoundException e) {
        }

        try {
            return alertaDAO.save(alerta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

	}

	@Override
	public Alerta update(Alerta alerta) throws NotFoundException, BusinessException {
		load(alerta.getId());
        try {
            return alertaDAO.save(alerta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
	}

	@Override
	public void delete(long id) throws NotFoundException, BusinessException {
		load(id);
        try {
            alertaDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

    }

}
