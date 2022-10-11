package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Cliente;

public interface IClienteBusiness {

  public List<Cliente> list() throws BusinessException;

  public Cliente add(Cliente product) throws FoundException, BusinessException;

  // TODO:PREGUNTAR SI SE PUEDE BORRAR
  public void delete(long id) throws NotFoundException, BusinessException;

}
