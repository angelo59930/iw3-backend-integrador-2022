package iua.kaf.Backend.controller;

import iua.kaf.Backend.integration.Cli1.model.OrdenCli1;
import iua.kaf.Backend.integration.Cli1.model.business.IOrdenCli1Business;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.util.IStandardResponseBusiness;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(Constantes.URL_ORDEN_CLI1)
public class OrdenCli1RestController {

	@Autowired
  private IOrdenCli1Business ordenCli1Business;

  @Autowired
  private IStandardResponseBusiness responseBusiness;

	@PostMapping(value = "")
	public ResponseEntity<?> add(@RequestBody OrdenCli1 orden) {

		try {

			OrdenCli1 response = ordenCli1Business.add(orden);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_ORDEN + "/" + response.getId());
			return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);

		} catch (FoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.FOUND, e, e.getMessage()), HttpStatus.FOUND);

		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> load(@PathVariable("id") String id) {

		try {

			return new ResponseEntity<>(ordenCli1Business.load(id), HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
					HttpStatus.NOT_FOUND);

		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {

		try {
			return new ResponseEntity<>(ordenCli1Business.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}





  @PostMapping(value = "/b2b")	
	public ResponseEntity<?> addExternal( HttpEntity<String> httpEntity){
		try {
			OrdenCli1 response = ordenCli1Business.addExternal(httpEntity.getBody());
      HttpHeaders responseHeaders = new HttpHeaders();

      responseHeaders.set("location", Constantes.URL_ORDEN_CLI1 + "/" + response.getCodeCli1());

      return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);

			} catch (FoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.FOUND, e, e.getMessage()), HttpStatus.FOUND);

		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
