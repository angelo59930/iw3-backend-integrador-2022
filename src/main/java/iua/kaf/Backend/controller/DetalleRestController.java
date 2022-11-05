package iua.kaf.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iua.kaf.Backend.model.Detalle;
import iua.kaf.Backend.model.business.IDetalleBusiness;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.util.IStandardResponseBusiness;

@RestController
@RequestMapping(Constantes.URL_DETALLE)
public class DetalleRestController {

	@Autowired
	private IStandardResponseBusiness responseBusiness;
	
	@Autowired
	private IDetalleBusiness detalleBusiness;
	
	@PutMapping()
	public ResponseEntity<?> closeDetalle(@RequestBody long id){
		try {
			detalleBusiness.closDetalle(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
					HttpStatus.NOT_FOUND);

		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping()
	public ResponseEntity<?> list(){	
		try {
			return new ResponseEntity<>(detalleBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	}
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<?> load(@PathVariable("id") long id){
		try {
			return new ResponseEntity<>(detalleBusiness.load(id), HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// TODO:Preguntar si hay que cambiar con el grupo
	@PostMapping(value="")
    public ResponseEntity<?> add(@RequestBody Detalle detalle){
		
		try {
			
			Detalle response = detalleBusiness.add(detalle);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_DETALLE + "/" + response.getId());
			return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
			
		} catch (FoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.FOUND, e, e.getMessage()), HttpStatus.FOUND);
			
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping(value="")
	public ResponseEntity<?> update(@RequestBody Detalle detalle){	
		try {
			detalleBusiness.update(detalle);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
