package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import iua.kaf.Backend.model.Chofer;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.ChoferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ChoferBusiness implements IChoferBusiness {
    @Autowired
    private ChoferRepository choferDAO;

    @Override
    public Chofer load(long id) throws NotFoundException, BusinessException {
        Optional<Chofer> r;
        try {
            r = choferDAO.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra el chofer con id=" + id).build();
        }
        return r.get();
    }

    @Override
    public Chofer loadDNI(long dni) throws NotFoundException, BusinessException {
        Optional<Chofer> r;
        try {
            r = choferDAO.findByDni(dni);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra el chofer con dni=" + dni).build();
        }
        return r.get();
    }

    @Override
    public List<Chofer> list() throws BusinessException {
        try {
            return choferDAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

    }

    @Override
    public Chofer add(Chofer chofer) throws FoundException, BusinessException {
        try {
            load(chofer.getId());
            throw FoundException.builder().message("Se encontró el chofer con id=" + chofer.getId()).build();
        } catch (NotFoundException e) {

        }

        try {
            return choferDAO.save(chofer);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

    }

    @Override
    public Chofer update(Chofer chofer) throws NotFoundException, BusinessException {
        load(chofer.getId());
        try {
            return choferDAO.save(chofer);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public void delete(long id) throws NotFoundException, BusinessException {
        load(id);
        try {
            choferDAO.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

    }

}
