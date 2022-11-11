package iua.kaf.Backend.model.business;

import java.util.List;

import iua.kaf.Backend.model.Cliente;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IClienteBusiness {

    public Cliente load(long id) throws NotFoundException, BusinessException;

    public Cliente load(String nombre) throws NotFoundException, BusinessException;
    public List<Cliente> list() throws BusinessException;

    public Cliente add(Cliente cliente) throws FoundException, BusinessException;

    public void delete(long id) throws NotFoundException, BusinessException;

}
