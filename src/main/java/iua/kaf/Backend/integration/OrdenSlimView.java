package iua.kaf.Backend.integration;

import iua.kaf.Backend.model.Camion;
import iua.kaf.Backend.model.Chofer;
import iua.kaf.Backend.model.Cliente;
import iua.kaf.Backend.model.Producto;

public interface OrdenSlimView {

	String getPassword();

	String getFechaInicioCarga();

	double getPreset();

	Camion getCamion();

	Chofer getChofer();
	interface getChofer {
		long getDni();
	}

	Cliente getCliente();
	interface getCamion {
		String getPatente();
	}
	interface getCliente {
		String getNombre();
	}

	Producto getProducto();
	interface getProducto {
		String getNombre();
	}

}

