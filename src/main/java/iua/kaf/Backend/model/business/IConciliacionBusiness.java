package iua.kaf.Backend.model.business;

import java.util.List;
import iua.kaf.Backend.model.Conciliacion;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IConciliacionBusiness {

	public Conciliacion load(long id) throws NotFoundException, BusinessException;
	
	 public List<Conciliacion> list() throws BusinessException;

	 public Conciliacion add(Conciliacion conciliacion) throws FoundException, BusinessException;

	 public Conciliacion update(Conciliacion conciliacion) throws NotFoundException, BusinessException;

	 public void delete(long id) throws NotFoundException, BusinessException;
	
}
