package iua.kaf.Backend;

import org.junit.jupiter.api.Test;
import org.hibernate.jdbc.Expectations;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import iua.kaf.Backend.controller.OrdenRestController;

@SpringBootTest
@WebMvcTest(OrdenRestController.class)
class OrdenControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testOrdenList() throws Exception {
    mvc.perform(MockMvcRequestBuilders
        .get("api/v1/orden")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

}
