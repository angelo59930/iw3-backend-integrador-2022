package iua.kaf.Backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import iua.kaf.Backend.model.Alerta;
import iua.kaf.Backend.model.Camion;
import iua.kaf.Backend.model.Chofer;
import iua.kaf.Backend.model.Mail;
import iua.kaf.Backend.model.Producto;
import iua.kaf.Backend.model.business.AlertaBusiness;
import iua.kaf.Backend.model.business.CamionBusiness;
import iua.kaf.Backend.model.business.ChoferBusiness;
import iua.kaf.Backend.model.business.MailBusiness;
import iua.kaf.Backend.model.business.ProductoBusiness;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private CamionBusiness camionBusiness;
	
	@Autowired
	private ChoferBusiness choferBusiness;
	
	@Autowired
	private MailBusiness mailBusiness;

	@Autowired
	private AlertaBusiness alertaBusiness;
	
	@Autowired
	private ProductoBusiness productoBusiness;
	
	@Test
	void contextLoads() {

	}

	@Test
	void camionTypeTest() {
		try {
			Assertions.assertSame(new Camion().getClass(), camionBusiness.load(2).getClass());
		} catch (NotFoundException | BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void choferTypeTest() {
		try {
			Assertions.assertSame(new Chofer().getClass(), choferBusiness.load(3).getClass());
		} catch (NotFoundException | BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void mailTypeTest() {
		try {
			Assertions.assertSame(new Mail().getClass(), mailBusiness.load(1).getClass());
		} catch (NotFoundException | BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void alertaTypeTest() {
		try {
			Assertions.assertSame(new Alerta().getClass(), alertaBusiness.load(2).getClass());
		} catch (NotFoundException | BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void productoTypeTest() {
		try {
			Assertions.assertSame(new Producto().getClass(), productoBusiness.load(2).getClass());
		} catch (NotFoundException | BusinessException e) {
			e.printStackTrace();
		}
	}

}
