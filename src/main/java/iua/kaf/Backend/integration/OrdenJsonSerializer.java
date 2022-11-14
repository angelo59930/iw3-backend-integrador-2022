package iua.kaf.Backend.integration;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import iua.kaf.Backend.model.Orden;

public class OrdenJsonSerializer extends StdSerializer<Orden>{

	private static final long serialVersionUID = 197140801755756463L;

	protected OrdenJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(Orden value, JsonGenerator gen, SerializerProvider provider) throws IOException {

		gen.writeStartObject();
		gen.writeNumberField("numero", value.getNumeroOrden());
		gen.writeObjectField("recepcionExt", value.getFechaRecepcionExt());
		gen.writeObjectField("recepcionPesaje", value.getFechaRecepcionPesaje());
		gen.writeObjectField("inicioCarga", value.getFechaInicioCarga());
		gen.writeObjectField("finCarga", value.getFechaFinCarga());
		gen.writeObjectField("pesajeFinal", value.getFechaPesajeFinal());
		gen.writeNumberField("Estado", value.getEstado());
		gen.writeNumberField("Tara", value.getTara());
		gen.writeObjectField("Camion", value.getCamion());
		gen.writeObjectField("Chofer", value.getChofer());
		gen.writeObjectField("Cliente", value.getCliente());
		gen.writeObjectField("Producto", value.getProducto());
		gen.writeNumberField("Password", value.getPassword());
		gen.writeEndObject();
	}
}