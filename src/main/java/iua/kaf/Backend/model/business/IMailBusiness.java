package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Mail;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IMailBusiness {

	public Mail load(long id) throws NotFoundException, BusinessException;

	  public Mail load(String nombre) throws NotFoundException, BusinessException;

	  public List<Mail> list() throws BusinessException;

	  public Mail add(Mail mail) throws FoundException, BusinessException;

	  public void delete(long id) throws NotFoundException, BusinessException;

	  public Mail update(Mail mail) throws NotFoundException, BusinessException;
}
