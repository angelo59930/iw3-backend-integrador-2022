package iua.kaf.Backend.integration;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import iua.kaf.Backend.model.Orden;

public class OrdenSlimView extends StdSerializer<Orden>{

	private static final long serialVersionUID = -6736573222054945232L;

	protected OrdenSlimView(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(Orden value, JsonGenerator gen, SerializerProvider provider) throws IOException {

		gen.writeStartObject();
		gen.writeNumberField("numero", value.getNumeroOrden());
		gen.writeNumberField("Tara", value.getTara());
		gen.writeStringField("Patente Camion", value.getCamion().getPatente());
		gen.writeNumberField("DNI Chofer", value.getChofer().getDni());
		gen.writeStringField("Cliente", value.getCliente().getNombre());
		gen.writeStringField("Producto", value.getProducto().getNombre());
		gen.writeEndObject();
	}
}