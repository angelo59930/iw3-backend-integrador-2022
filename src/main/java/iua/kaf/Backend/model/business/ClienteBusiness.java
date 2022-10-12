package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Cliente;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public class ClienteBusiness implements IClienteBusiness{

  @Override
  public List<Cliente> list() throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Cliente add(Cliente product) throws FoundException, BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(long id) throws NotFoundException, BusinessException {
    // TODO Auto-generated method stub
    
  }
  
}
