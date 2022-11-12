package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iua.kaf.Backend.integration.OrdenSlimView;
import iua.kaf.Backend.model.Orden;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.OrdenRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrdenBusiness implements IOrdenBusiness {

    @Autowired
    private OrdenRepository ordenDAO;

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
            //orden.getDetalle().setEstado(1);
            String randomPassword = "";
            for (int j = 0; j < 5; j++) {
                randomPassword += randomCharacter();
            }
            orden.setPassword(Long.parseLong(randomPassword));

            return ordenDAO.save(orden);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Orden update(Orden orden) throws NotFoundException, BusinessException {
        //int estado = orden.getDetalle().getEstado();
        //if (estado > 0 && estado < 3) {

            load(orden.getId());
            try {
          //      orden.getDetalle().setEstado(2);
                return ordenDAO.save(orden);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw BusinessException.builder().ex(e).build();
            }
        //}

        //throw BusinessException.builder().build();

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

    
    //TODO completar la implementacion de loadSlimView, por alguna razon no te deja devolver un OrdenSlimView, sino que te obliga a que sea una Orden si o si
    @Override
    public Optional<OrdenSlimView> loadSlimView(long id) {
    	return ordenDAO.findById(id);
    }
    
}
