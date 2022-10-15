package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import iua.kaf.Backend.model.Chofer;
import iua.kaf.Backend.model.Orden;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.OrdenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
