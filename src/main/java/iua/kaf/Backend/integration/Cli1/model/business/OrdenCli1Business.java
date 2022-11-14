package iua.kaf.Backend.integration.Cli1.model.business;

import iua.kaf.Backend.integration.Cli1.model.OrdenCli1;
import iua.kaf.Backend.integration.Cli1.model.OrdenCli1JsonDeserializer;
import iua.kaf.Backend.integration.Cli1.model.persistence.OrdenCli1Repository;
import iua.kaf.Backend.model.business.CamionBusiness;
import iua.kaf.Backend.model.business.ChoferBusiness;
import iua.kaf.Backend.model.business.ClienteBusiness;
import iua.kaf.Backend.model.business.ProductoBusiness;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.util.JsonUtiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrdenCli1Business implements IOrdenCli1Business {

    @Autowired
    private OrdenCli1Repository ordenCli1DAO;

    @Override
    public OrdenCli1 load(String code) throws NotFoundException, BusinessException {
        Optional<OrdenCli1> r;
        try {
            r = ordenCli1DAO.findByCodeCli1(code);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
        if (r.isEmpty()) {
            throw NotFoundException.builder().message("No se encuentra la ordenCli1 con code=" + code).build();
        }

        return r.get();
    }

    @Override
    public List<OrdenCli1> list() throws BusinessException {
        try {
            return ordenCli1DAO.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Override
    public OrdenCli1 add(OrdenCli1 ordenCli1) throws FoundException, BusinessException {
        try {
            load(ordenCli1.getCodeCli1());
            throw FoundException.builder().message("Se encontró la ordenCli1 con codigo=" + ordenCli1.getCodeCli1())
                    .build();
        } catch (NotFoundException e) {
        }

        try {
            return ordenCli1DAO.save(ordenCli1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }
    }

    @Autowired
    private CamionBusiness camionBusiness;
    @Autowired
    private ChoferBusiness choferBusiness;
    @Autowired
    private ProductoBusiness productoBusiness;
    @Autowired
    private ClienteBusiness clienteBusiness;

    @Override
    public OrdenCli1 addExternal(String json) throws FoundException, BusinessException {
        ObjectMapper mapper = JsonUtiles.getObjectMapper(OrdenCli1.class, new OrdenCli1JsonDeserializer(OrdenCli1.class, 
                camionBusiness , choferBusiness, clienteBusiness, productoBusiness));
        OrdenCli1 orden = null;

        try {
            orden = mapper.readValue(json, OrdenCli1.class);
            orden.setFechaRecepcionExt(new Date());
            orden.setEstado(1);
        }catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw BusinessException.builder().ex(e).build();
        }

        return add(orden);

    }
}
