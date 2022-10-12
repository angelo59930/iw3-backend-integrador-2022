
package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Camion;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public class CamionBusiness implements ICamionBusiness{

  @Override
  public List<Camion> list() throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Camion add(Camion product) throws FoundException, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Camion update(Camion product) throws NotFoundException, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(long id) throws NotFoundException, BusinessException {
    // TODO Auto-generated method stub
    
  }
  
}
