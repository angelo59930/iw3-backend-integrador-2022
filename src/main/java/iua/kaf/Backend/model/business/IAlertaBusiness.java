package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Alerta;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IAlertaBusiness {

	public Alerta load(long id) throws NotFoundException, BusinessException;

    public List<Alerta> list() throws BusinessException;

    public Alerta add(Alerta alerta) throws FoundException, BusinessException;

    public Alerta update(Alerta alerta) throws NotFoundException, BusinessException;

    public void delete(long id) throws NotFoundException, BusinessException;

	
}
