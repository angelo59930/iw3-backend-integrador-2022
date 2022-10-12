package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Camion;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface ICamionBusiness {

  public List<Camion> list() throws BusinessException;

  public Camion add(Camion product) throws FoundException, BusinessException;

  public Camion update(Camion product) throws NotFoundException, BusinessException;

  public void delete(long id) throws NotFoundException, BusinessException;
}
