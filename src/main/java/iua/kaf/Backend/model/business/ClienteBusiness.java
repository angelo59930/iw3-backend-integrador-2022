package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iua.kaf.Backend.model.Cliente;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.ClienteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteBusiness implements IClienteBusiness {

  @Autowired
  private ClienteRepository clienteDAO;

  @Override
  public Cliente load(long id) throws NotFoundException, BusinessException {
    Optional<Cliente> r;
    try {
      r = clienteDAO.findById(id);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw BusinessException.builder().ex(e).build();
    }
    if (r.isEmpty()) {
      throw NotFoundException.builder().message("No se encuentra el cliente id=" + id).build();
    }

    return r.get();
  }

  public Cliente load(String nombre) throws NotFoundException, BusinessException {
    Optional<Cliente> r;
    try {
      r = clienteDAO.findByNombre(nombre);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw BusinessException.builder().ex(e).build();
    }
    if (r.isEmpty()) {
      throw NotFoundException.builder().message("No se encuentra el cliente nombre=" + nombre).build();
    }

    return r.get();
  }
  @Override
  public List<Cliente> list() throws BusinessException {
    try {
      return clienteDAO.findAll();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw BusinessException.builder().ex(e).build();
    }
  }

  @Override
  public Cliente add(Cliente cliente) throws FoundException, BusinessException {
    try {
      load(cliente.getId());
      throw FoundException.builder().message("Se encuentró el cliente id=" + cliente.getId()).build();
    } catch (NotFoundException e) {
    }

    try {
      return clienteDAO.save(cliente);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw BusinessException.builder().ex(e).build();
    }
  }

  @Override
  public void delete(long id) throws NotFoundException, BusinessException {
    load(id);
    try {
      clienteDAO.deleteById(id);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw BusinessException.builder().ex(e).build();
    }
  }

}
