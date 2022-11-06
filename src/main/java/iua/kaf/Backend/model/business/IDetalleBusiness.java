package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import iua.kaf.Backend.model.Detalle;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IDetalleBusiness {
  
  public Detalle add(Detalle detalle) throws FoundException, BusinessException;

  public List<Detalle> list() throws BusinessException;
  
  public Detalle load(long id) throws NotFoundException, BusinessException;

  public Detalle update(Detalle detalle) throws NotFoundException, BusinessException;

  public Optional<Detalle> closDetalle(long id) throws NotFoundException, BusinessException;
}
