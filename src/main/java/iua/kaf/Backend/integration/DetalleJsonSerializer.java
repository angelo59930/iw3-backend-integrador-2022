package iua.kaf.Backend.integration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import iua.kaf.Backend.model.Detalle;


import java.io.IOException;

public class DetalleJsonSerializer extends StdSerializer<Detalle> {
    private static final long serialVersionUID = 3606570055948116240L;

    protected DetalleJsonSerializer(Class<?> t, boolean dummy) {
        super(t , dummy);

    }

    @Override
    public void serialize(Detalle value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("MasaAcumulada" , value.getUltMasaAcumulada());
        gen.writeNumberField("DesidadProducto" , value.getDesidadProducto());
        gen.writeNumberField("TemperaturaProducto" , value.getDesidadProducto());
        gen.writeNumberField("Caudal" , value.getCaudal());
        gen.writeEndObject();
    }

}
