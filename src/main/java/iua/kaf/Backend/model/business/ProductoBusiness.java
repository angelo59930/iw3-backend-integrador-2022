package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iua.kaf.Backend.model.Producto;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.ProductoRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductoBusiness implements IProductoBusiness {

  @Autowired
  private ProductoRepository productoDAO;

  @Override
  public Producto load(long id) throws NotFoundException, BusinessException {
    Optional<Producto> r;
    try {
      r = productoDAO.findById(id);
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
  public Producto load(String nombre) throws NotFoundException, BusinessException {
    Optional<Producto> r;
    try {
      r = productoDAO.findByNombre(nombre);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw BusinessException.builder().ex(e).build();
    }
    if (r.isEmpty()) {
      throw NotFoundException.builder().message("No se encuentra el Producto nombre=" + nombre).build();
    }

    return r.get();
  }

  @Override
  public List<Producto> list() throws BusinessException {
    try {
      return productoDAO.findAll();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw BusinessException.builder().ex(e).build();
    }
  }

  @Override
  public Producto add(Producto product) throws FoundException, BusinessException {
    try {
      load(product.getId());
      throw FoundException.builder().message("Se encuentró el Producto id=" + product.getId()).build();
    } catch (NotFoundException e) {
    }

    try {
      return productoDAO.save(product);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw BusinessException.builder().ex(e).build();
    }
  }

  @Override
  public void delete(long id) throws NotFoundException, BusinessException {
    load(id);
    try {
      productoDAO.deleteById(id);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw BusinessException.builder().ex(e).build();
    }
  }

}
