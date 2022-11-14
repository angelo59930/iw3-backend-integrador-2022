
package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iua.kaf.Backend.model.Camion;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.CamionRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CamionBusiness implements ICamionBusiness {

    @Autowired
    private CamionRepository camionDAO;

    @Override
    public Camion load(long id) throws NotFoundException, BusinessException {
        Optional<Camion> r;
        try {
            r = camionDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra el cliente id=" + id).build();
        }

        return r.get();
    }

    @Override
    public Camion load(String patente) throws NotFoundException, BusinessException {
        Optional<Camion> r;
        try {
            r = camionDAO.findByPatente(patente);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra el Camion patente=" + patente).build();
        }

        return r.get();
    }

    @Override
    public List<Camion> list() throws BusinessException {
        try {
            return camionDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Camion add(Camion camion) throws FoundException, BusinessException {
        try {
            load(camion.getId());
            throw FoundException.builder().message("Se encuentró el camion con id=" + camion.getId()).build();
        } catch (NotFoundException e) {
        }

        try {
            return camionDAO.save(camion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public Camion update(Camion camion) throws NotFoundException, BusinessException {
        load(camion.getId());
        try {
            return camionDAO.save(camion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public void delete(long id) throws NotFoundException, BusinessException {
        load(id);
        try {
            camionDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

}
