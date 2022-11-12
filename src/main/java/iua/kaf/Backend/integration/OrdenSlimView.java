package iua.kaf.Backend.integration;

import iua.kaf.Backend.model.Camion;
import iua.kaf.Backend.model.Chofer;
import iua.kaf.Backend.model.Cliente;
import iua.kaf.Backend.model.Producto;

public interface OrdenSlimView {

	String getPassword();

	String getFechaInicioCarga();

	double preset();

	Camion getCamion();

	Chofer getChofer();

	Cliente getCliente();

	Producto getProducto();

	interface getProducto {
		String getNombre();		
	}

	interface getCliente {
		String nombre();
	}

	interface getChofer {
		long getDni();
	}

	interface getCamion {
		String getPatente();
	}

}