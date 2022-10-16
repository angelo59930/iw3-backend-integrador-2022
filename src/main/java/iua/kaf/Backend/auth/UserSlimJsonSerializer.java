package iua.kaf.Backend.auth;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class UserSlimJsonSerializer extends StdSerializer<User> {

  public UserSlimJsonSerializer(Class<?> t, boolean dummy) {
    super(t, dummy);
  }

  @Override
  public void serialize(User value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();
    gen.writeStringField("username", value.getUsername());

    gen.writeArrayFieldStart("role");
    for (Role role : value.getRoles())
      gen.writeString(role.getName());
    gen.writeEndArray();

    gen.writeStringField("email", value.getEmail());
    gen.writeStringField("authtoken", value.getToken());
    gen.writeEndObject();
  }

}