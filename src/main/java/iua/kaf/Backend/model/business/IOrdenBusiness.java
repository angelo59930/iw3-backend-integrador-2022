package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import iua.kaf.Backend.integration.OrdenSlimView;
import iua.kaf.Backend.model.Conciliacion;
import iua.kaf.Backend.model.Orden;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotAcceptableException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IOrdenBusiness {
    public Orden load(long id) throws NotFoundException, BusinessException;

    public List<Orden> list() throws BusinessException;

    public Orden add(Orden orden) throws FoundException, BusinessException;

    public Orden update(Orden orden) throws NotFoundException, BusinessException;

    public void delete(long id) throws NotFoundException, BusinessException;

    public Optional<OrdenSlimView> listSlim(long numOrden) throws BusinessException;

    public Orden closeOrden(long id) throws NotFoundException, BusinessException;

    public Orden pesajeInicial(long id, double tara) throws NotFoundException, BusinessException;

	public Conciliacion pesajeFinal(long id, double ultimoPeso) throws NotFoundException, BusinessException, NotAcceptableException;

	public Conciliacion conciliacion(long id) throws NotFoundException, BusinessException, NotAcceptableException;
}
