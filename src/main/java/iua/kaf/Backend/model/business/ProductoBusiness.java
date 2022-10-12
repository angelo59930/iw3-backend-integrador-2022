package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Producto;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public class ProductoBusiness implements IProductoBusiness{

  @Override
  public List<Producto> list() throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Producto add(Producto product) throws FoundException, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(long id) throws NotFoundException, BusinessException {
    // TODO Auto-generated method stub
    
  }
  
}
