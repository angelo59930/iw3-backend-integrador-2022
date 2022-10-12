package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Chofer;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public class ChoferBusiness implements IChoferBusiness{

  @Override
  public List<Chofer> list() throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Chofer add(Chofer product) throws FoundException, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Chofer update(Chofer product) throws NotFoundException, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(long id) throws NotFoundException, BusinessException {
    // TODO Auto-generated method stub
    
  }
  
}
