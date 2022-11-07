package iua.kaf.Backend.integration;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import iua.kaf.Backend.model.Conciliacion;

public class ConciliacionJsonSerializer extends StdSerializer<Conciliacion>{

	private static final long serialVersionUID = -6786295934606599185L;

	protected ConciliacionJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(Conciliacion value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("pesajeInicial", value.getPesajeInicial());
		gen.writeNumberField("pesajeFinal", value.getPesajeFinal());
		gen.writeNumberField("productoCargado", value.getProductoCargado());
		gen.writeNumberField("netoPorBalanza", value.getNetoPorBalanza());
		gen.writeNumberField("diferenciaBalanzaCaudalimetro", value.getDiferenciaBalanzaCaudalimetro());
		gen.writeNumberField("promedioTemperatura", value.getPromedioTemperatura());
		gen.writeNumberField("promedioDensidad", value.getPromedioDensidad());
		gen.writeNumberField("promedioCaudal", value.getPromedioCaudal());
		gen.writeEndObject();
		
	}

}
