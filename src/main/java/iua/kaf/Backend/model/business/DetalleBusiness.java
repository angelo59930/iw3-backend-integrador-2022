package iua.kaf.Backend.model.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iua.kaf.Backend.model.Detalle;
import iua.kaf.Backend.model.Orden;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.ForbiddenException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotAcceptableException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.model.persistence.AlertaRepository;
import iua.kaf.Backend.model.persistence.DetalleRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DetalleBusiness implements IDetalleBusiness {

	@Autowired
	private DetalleRepository detalleDAO;

	@Autowired 
	private OrdenBusiness ordenDAO;

	@Autowired
	private MailBusiness mailBusiness;
	
	@Autowired
	private AlertaRepository alertaDAO;
	
	@Override
	public Detalle add(Detalle detalle, long password) throws FoundException, BusinessException,ForbiddenException {
		
		if(detalle.getTempProducto() > alertaDAO.getLastByOrden(detalle.getOrden().getId())) {
			mailBusiness.sendSimpleMessageToAll(detalle.getTempProducto()+"");
		}
		
		try {
			load(detalle.getId());
			throw FoundException.builder().message("Se encuentró el detalle id=" + detalle.getId()).build();
		} catch (NotFoundException e) {
		}

		if (detalleDAO.existPassword(detalle.getOrden().getId(), password).isEmpty()) {
			throw ForbiddenException.builder().build();
		}

		try {

			Orden o = ordenDAO.load(detalle.getOrden().getId());
			o.setFechaInicioCarga(new Date());
			ordenDAO.update(o);

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
	public Detalle update(Detalle detalle, long id) throws NotFoundException, BusinessException, NotAcceptableException {
		Detalle d = load(id);

		if(d.getOrden().getEstado() >= 3){
			throw NotAcceptableException.builder().message("El detalle se encuentra en estado 3 o 4").build();
		}	
		
		if(detalle.getCaudal() <= 0) {
			throw NotAcceptableException.builder().message("El caudal es menor o igual a 0").build();
		}
		
		//masa acumulada anterior > masa acumulada actual
		if(d.getUltMasaAcumulada() > detalle.getUltMasaAcumulada()) {
			throw NotAcceptableException.builder().message("La ultima masa acumulada es menor a la masa acumulada anterior").build();
		}

		try {

			Orden o = ordenDAO.load(detalle.getOrden().getId());
			o.setFechaFinCarga(new Date());
			ordenDAO.update(o);

			return detalleDAO.save(detalle);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

}
