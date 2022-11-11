package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Producto;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IProductoBusiness {

  public Producto load(long id) throws NotFoundException, BusinessException;

  public Producto load(String nombre) throws NotFoundException, BusinessException;

  public List<Producto> list() throws BusinessException;

  public Producto add(Producto product) throws FoundException, BusinessException;

  public void delete(long id) throws NotFoundException, BusinessException;

}
