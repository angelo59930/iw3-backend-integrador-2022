package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Orden;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public class OrdenBusiness implements IOrdenBusiness{

  @Override
  public Orden load(long id) throws NotFoundException, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Orden> list() throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Orden add(Orden product) throws FoundException, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Orden update(Orden product) throws NotFoundException, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(long id) throws NotFoundException, BusinessException {
    // TODO Auto-generated method stub
    
  }
  
}
