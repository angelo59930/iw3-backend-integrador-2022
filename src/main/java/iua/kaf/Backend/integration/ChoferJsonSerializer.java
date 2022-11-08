package iua.kaf.Backend.integration;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import iua.kaf.Backend.model.Chofer;

public class ChoferJsonSerializer extends StdSerializer<Chofer>{

	private static final long serialVersionUID = 5967899631084726312L;

	protected ChoferJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(Chofer value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("dni", value.getDni());
		gen.writeStringField("nombre", value.getNombre());
		gen.writeEndObject();
		
	}

}
