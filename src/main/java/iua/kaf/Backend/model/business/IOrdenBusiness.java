package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Orden;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IOrdenBusiness {
  public Orden load(long id) throws NotFoundException, BusinessException;

  public List<Orden> list() throws BusinessException;

  public Orden add(Orden product) throws FoundException, BusinessException;

  public Orden update(Orden product) throws NotFoundException, BusinessException;

  public void delete(long id) throws NotFoundException, BusinessException;
}