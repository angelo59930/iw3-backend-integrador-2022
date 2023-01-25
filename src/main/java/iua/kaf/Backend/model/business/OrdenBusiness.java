package iua.kaf.Backend.model.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iua.kaf.Backend.integration.OrdenSlimView;
import iua.kaf.Backend.model.Conciliacion;
import iua.kaf.Backend.model.Orden;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotAcceptableException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.DetalleRepository;
import iua.kaf.Backend.model.persistence.OrdenRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrdenBusiness implements IOrdenBusiness {

    @Autowired
    private OrdenRepository ordenDAO;
    
    @Autowired
    private DetalleRepository detalleDAO; 

    @Override
    public Orden load(long id) throws NotFoundException, BusinessException {

        Optional<Orden> r;
        try {
            r = ordenDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra la orden con id=" + id).build();
        }

        return r.get();
    }

    @Override
    public List<Orden> list() throws BusinessException {
        try {
            return ordenDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    public static char randomCharacter() {
        int rand = (int) (Math.random() * 10);
        int number = rand + 48;
        return (char) (number);
    }

    @Override
    public Orden add(Orden orden) throws FoundException, BusinessException {

        try {
            load(orden.getId());
            throw FoundException.builder().message("Se encontró la orden con id=" + orden.getId()).build();
        } catch (NotFoundException e) {
        }

        try {
            return ordenDAO.save(orden);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Orden update(Orden orden) throws NotFoundException, BusinessException {
        load(orden.getId());
        try {
            return ordenDAO.save(orden);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public void delete(long id) throws NotFoundException, BusinessException {
        load(id);
        try {
            ordenDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

    }

    @Override
    public Optional<OrdenSlimView> listSlim(long numOrden) throws BusinessException {
        try {
            return ordenDAO.findByNumeroOrden(numOrden);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Orden closeOrden(long id) throws NotFoundException, BusinessException {

        try {
            Orden d = load(id);
            d.setEstado(3);
            return ordenDAO.save(d);
        } catch (NotFoundException e) {
            log.error(e.getMessage(), e);
            throw NotFoundException.builder().ex(e).build();
        } catch (BusinessException e) {
            throw NotFoundException.builder().ex(e).build();
        }
    }

    @Override
    public Orden pesajeInicial(long id, double tara) throws NotFoundException, BusinessException {
        
        try {
            Orden orden = load(id);
            orden.setTara(tara);

            orden.setEstado(2);
            String randomPassword = "";
            for (int j = 0; j < 5; j++) {
                randomPassword += randomCharacter();
            }
            orden.setPassword(Long.parseLong(randomPassword));

            orden.setFechaRecepcionPesaje(new Date());
            
            return ordenDAO.save(orden);
        } catch (NotFoundException e) {
            log.error(e.getMessage(), e);
            throw NotFoundException.builder().ex(e).build();
        } catch (BusinessException e) {
            throw NotFoundException.builder().ex(e).build();
        }
        
    }
    
    @Override
    public Conciliacion pesajeFinal(long id, double ultimoPeso) throws NotFoundException, BusinessException, NotAcceptableException {
    	Orden o = load(id);
    	
    	if(o.getEstado() == 3) {

        	o.setFechaPesajeFinal(new Date());
        	
        	o.setEstado(4);
        	
        	this.update(o);
        	
    		return this.conciliacion(id);
        		
    	}
    	
    	throw NotAcceptableException.builder().message("La orden no esta en estado 3").build();
    	
    }
    
    @Override
    public Conciliacion conciliacion(long id) throws NotFoundException, BusinessException, NotAcceptableException {
    	Conciliacion c = new Conciliacion();
    	
    	Orden o = load(id);
    	
    	if(o.getEstado() == 4) {
    		c.setPesajeInicial(o.getTara());
        	c.setPesajeFinal(o.getPesajeFinal());
        	
        	c.setProductoCargado(detalleDAO.ultimaMasaAcumulada(id));
        	c.setNetoPorBalanza(c.getPesajeFinal() - c.getPesajeInicial());
        	c.setDiferenciaEntreBalanzaCaudalimetro(c.getNetoPorBalanza() - c.getProductoCargado());
        	
        	c.setPromedioTemperatura(detalleDAO.promedioTemperatura(id));
        	c.setPromedioCaudal(detalleDAO.promedioCaudal(id));
        	c.setPromedioDensidad(detalleDAO.promedioDensidad(id));
        	
    		return c;
	
    	}
    	
    	throw NotAcceptableException.builder().message("La orden no esta en estado 4").build();
    	
     }

}
