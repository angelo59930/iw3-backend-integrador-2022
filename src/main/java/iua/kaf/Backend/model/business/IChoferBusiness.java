package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Chofer;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IChoferBusiness {

  public List<Chofer> list() throws BusinessException;

  public Chofer add(Chofer product) throws FoundException, BusinessException;

  public Chofer update(Chofer product) throws NotFoundException, BusinessException;

  public void delete(long id) throws NotFoundException, BusinessException;
}
