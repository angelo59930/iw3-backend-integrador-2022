package iua.kaf.Backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import iua.kaf.Backend.model.Camion;
import iua.kaf.Backend.model.business.CamionBusiness;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private CamionBusiness camionBusiness;

	@Test
	void contextLoads() {

	}

	@Test
	void camionTypeTest() {
		try {
			Assertions.assertSame(new Camion().getClass(), camionBusiness.load(1).getClass());
		} catch (NotFoundException | BusinessException e) {
			e.printStackTrace();
		}
	}


}
