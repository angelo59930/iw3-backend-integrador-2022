package iua.kaf.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import iua.kaf.Backend.model.Conciliacion;
import iua.kaf.Backend.model.Orden;
import iua.kaf.Backend.model.business.IOrdenBusiness;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotAcceptableException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.util.IStandardResponseBusiness;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(Constantes.URL_ORDEN)
public class OrdenRestController {

	@Autowired
	private IStandardResponseBusiness responseBusiness;

	@Autowired
	private IOrdenBusiness ordenBusiness;

	@PutMapping(value = "/pesaje-final/{id}")
	public ResponseEntity<?> pesajeFinal(@PathVariable("id") long id,@RequestParam(name = "ultimoPeso",required = true) double ultimoPeso) {
		try {
			
			Conciliacion response = ordenBusiness.pesajeFinal(id,ultimoPeso);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
					HttpStatus.NOT_FOUND);

		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotAcceptableException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/conciliacion/{id}")
	public ResponseEntity<?> obtenerConciliacion(@PathVariable("id") long id){
		
		Conciliacion response = new Conciliacion();
		try {
			
			response = ordenBusiness.conciliacion(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
					HttpStatus.NOT_FOUND);
		
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotAcceptableException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/pesaje-inicial/{id}")
	public ResponseEntity<?> pesajeInicial(@PathVariable("id") long id,@RequestParam(name = "tara",required = true) double tara) {
		try {
			ordenBusiness.pesajeInicial(id,tara);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
					HttpStatus.NOT_FOUND);

		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/cerrar-orden/{id}")
	public ResponseEntity<?> closeDetalle(@PathVariable("id") long id) {
		try {
			ordenBusiness.closeOrden(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
					HttpStatus.NOT_FOUND);

		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<?> add(@RequestBody Orden orden) {

		try {

			Orden response = ordenBusiness.add(orden);
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

	@PutMapping(value = "")
	public ResponseEntity<?> update(@RequestBody Orden orden) {

		try {

			ordenBusiness.update(orden);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
					HttpStatus.NOT_FOUND);

		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {

		try {

			ordenBusiness.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
					HttpStatus.NOT_FOUND);

		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> load(@PathVariable("id") long id,
			@RequestParam(name = "slim", required = true, defaultValue = "v0") String slimView,
			@RequestParam(name = "numOrden", required = false, defaultValue = "0") Long numOrden) {

		try {
			if (slimView.equals("v1")) {
				if (numOrden == 0)
					return new ResponseEntity<>(
							responseBusiness.build(HttpStatus.BAD_REQUEST, null, "no se paso el numero de orden"),
							HttpStatus.BAD_REQUEST);

				return new ResponseEntity<>(ordenBusiness.listSlim(numOrden), HttpStatus.OK);
			}

			return new ResponseEntity<>(ordenBusiness.load(id), HttpStatus.OK);

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
			return new ResponseEntity<>(ordenBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}