package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Producto;

public interface IProductoBusiness {

  public List<Producto> list() throws BusinessException;

  public Producto add(Producto product) throws FoundException, BusinessException;

  public void delete(long id) throws NotFoundException, BusinessException;

}
