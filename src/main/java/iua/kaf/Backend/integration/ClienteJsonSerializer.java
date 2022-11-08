package iua.kaf.Backend.integration;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import iua.kaf.Backend.model.Cliente;

public class ClienteJsonSerializer extends StdSerializer<Cliente>{

	private static final long serialVersionUID = -377064097938386503L;

	protected ClienteJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(Cliente value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("nombre", value.getNombre());
		gen.writeStringField("razonSocial", value.getRazonSocial());
		gen.writeStringField("contacto",value.getContacto());
		gen.writeEndObject();
		
	}

}
