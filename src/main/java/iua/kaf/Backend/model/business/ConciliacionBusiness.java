package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iua.kaf.Backend.model.Conciliacion;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.ConciliacionRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConciliacionBusiness implements IConciliacionBusiness{
	
	@Autowired
	private ConciliacionRepository conciliacionDAO;
	
	@Override
	public Conciliacion load(long id) throws NotFoundException, BusinessException {
		
		Optional<Conciliacion> r;
		
		try {
			r = conciliacionDAO.findById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		
		if(r.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra la conciliacion con Id " + id).build();
		}
		
		return r.get();
	}

	@Override
	public List<Conciliacion> list() throws BusinessException {
		try {
			return conciliacionDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Override
	public Conciliacion add(Conciliacion conciliacion) throws FoundException, BusinessException {
		try {
			load(conciliacion.getId());
			throw FoundException.builder().message("Se encontro la conciliacion con Id " + conciliacion.getId()).build();
		} catch (NotFoundException e) {
		}
		
		try {
			return conciliacionDAO.save(conciliacion);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		
	}

	@Override
	public Conciliacion update(Conciliacion conciliacion) throws NotFoundException, BusinessException {
		
		load(conciliacion.getId());
		
		try {
			return conciliacionDAO.save(conciliacion);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Override
	public void delete(long id) throws NotFoundException, BusinessException {
		
		load(id);
		
		try {
			conciliacionDAO.deleteById(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw BusinessException.builder().ex(e).build();
		}
		
	}
	
	public void calculos(long id) {
		
		Conciliacion conc = conciliacionDAO.getById(id);
		
		conc.setNetoPorBalanza(conc.getPesajeFinal() - conc.getPesajeInicial());
		
		conc.setDiferenciaBalanzaCaudalimetro(conc.getNetoPorBalanza() - conc.getProductoCargado());
		
		//TODO promedioDensidad y promedioCaudal
	}

}
