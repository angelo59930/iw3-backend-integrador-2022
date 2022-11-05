package iua.kaf.Backend.model.business;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iua.kaf.Backend.model.Detalle;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.DetalleRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DetalleBusiness implements IDetalleBusiness {

	@Autowired
	private DetalleRepository detalleDAO;

	@Override
	public Detalle add(Detalle detalle) throws FoundException, BusinessException {
		try {
			load(detalle.getId());
			throw FoundException.builder().message("Se encuentró el detalle id=" + detalle.getId()).build();
		} catch (NotFoundException e) {
		}
		try {
			return detalleDAO.save(detalle);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}

	}

	@Override
	public List<Detalle> list() throws BusinessException {
		try {
			return detalleDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	@Override
	public Detalle load(long id) throws NotFoundException, BusinessException {
		Optional<Detalle> r;
		try {
			r = detalleDAO.findById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if (r.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el detalle id=" + id).build();
		}

		return r.get();
	}

	@Override
	public Detalle update(Detalle detalle) throws NotFoundException, BusinessException {
		load(detalle.getId());
		try {
			return detalleDAO.save(detalle);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	// TODO:rev
	@Override
	public Detalle closDetalle(long id) throws NotFoundException, BusinessException {
		Detalle detalle = load(id);

		detalle.setEstado(3);

		return update(detalle);
	}

}
