package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Cliente;
import iua.kaf.Backend.model.business.exeptions.BusinessException;
import iua.kaf.Backend.model.business.exeptions.FoundException;
import iua.kaf.Backend.model.business.exeptions.NotFoundException;

public interface IClienteBusiness {

  public List<Cliente> list() throws BusinessException;

  public Cliente add(Cliente product) throws FoundException, BusinessException;

  public void delete(long id) throws NotFoundException, BusinessException;

}
