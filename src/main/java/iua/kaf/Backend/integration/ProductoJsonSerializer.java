package iua.kaf.Backend.integration;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import iua.kaf.Backend.model.Producto;

public class ProductoJsonSerializer extends StdSerializer<Producto>{

	private static final long serialVersionUID = 2134088251469992321L;

	protected ProductoJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(Producto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		
		gen.writeStartObject();
		gen.writeStringField("nombre", value.getNombre());
		gen.writeStringField("descripcion", value.getDescripcion());
		gen.writeEndObject();
		
	}

}
