package iua.kaf.Backend.integration;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import iua.kaf.Backend.model.Camion;

public class CamionJsonSerializer extends StdSerializer<Camion>{

	private static final long serialVersionUID = -4447574594608019366L;

	protected CamionJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(Camion value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("patente", value.getPatente());
		gen.writeNumberField("cisternado", value.getCisternado());
		gen.writeStringField("descripcion", value.getDescripcion());
		gen.writeEndObject();
		
	}

}
