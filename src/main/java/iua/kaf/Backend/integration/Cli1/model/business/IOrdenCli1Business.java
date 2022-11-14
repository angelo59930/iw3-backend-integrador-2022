package iua.kaf.Backend.integration.Cli1.model.business;

import iua.kaf.Backend.integration.Cli1.model.OrdenCli1;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

import java.util.List;

public interface IOrdenCli1Business {

    public OrdenCli1 load(String code) throws NotFoundException, BusinessException;

    public List<OrdenCli1> list() throws BusinessException;

    public OrdenCli1 add(OrdenCli1 ordenCli1) throws FoundException, BusinessException;

    public OrdenCli1 addExternal(String json) throws FoundException, BusinessException;

}
