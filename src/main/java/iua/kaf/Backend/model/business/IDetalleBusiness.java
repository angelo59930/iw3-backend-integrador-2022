package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Detalle;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.ForbiddenException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotAcceptableException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IDetalleBusiness {
  
  public Detalle add(Detalle detalle, long password) throws FoundException, BusinessException, ForbiddenException;

  public List<Detalle> list() throws BusinessException;
  
  public Detalle load(long id) throws NotFoundException, BusinessException;

  public Detalle update(Detalle detalle, long id) throws NotFoundException, BusinessException, NotAcceptableException;

}
