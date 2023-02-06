package iua.kaf.Backend;

import iua.kaf.Backend.controller.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest(controllers = AuthRestController.class)
public class AuthTest {
  // test de autenticacion de un usuario admin
  // username = admin, password = admin, json = true
  // ruta del controlador = /api/v1/login
  // metodo del controlador = POST
  // resultado esperado = 200 OK

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthRestController authRestController;

  @Test
  public void testLoginAdmin() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("username", "admin");
    params.add("password", "admin");
    params.add("json", "true");

    this.mockMvc.perform(get("http://localhost:8080/api/v1/login?username=admin&password=admin?json=true")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("token")));
  }

}
