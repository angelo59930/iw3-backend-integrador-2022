package iua.kaf.Backend;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import iua.kaf.Backend.controller.CamionRestController;
import iua.kaf.Backend.controller.ChoferRestController;
import iua.kaf.Backend.controller.OrdenRestController;
import iua.kaf.Backend.model.business.CamionBusiness;
import iua.kaf.Backend.model.business.ChoferBusiness;
import iua.kaf.Backend.model.business.OrdenBusiness;

@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private CamionBusiness camionBusiness;

  @Mock
  private ChoferBusiness choferBusiness;

  @Mock
  private OrdenBusiness ordenBusiness;

  @InjectMocks
  private CamionRestController camionRestController;

  @InjectMocks
  private ChoferRestController choferRestController;

  @InjectMocks
  private OrdenRestController ordenRestController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testGetCamion() throws Exception {
    String token = obtainAccessToken();

    when(camionBusiness.list()).thenReturn(null);

    mockMvc.perform(get("/api/v1/camion")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentType("application/json"));

                         
  }

  @Test
  void testGetChofer() throws Exception {
    String token = obtainAccessToken();

    when(choferBusiness.list()).thenReturn(null);

    mockMvc.perform(get("/api/v1/camion")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentType("application/json"));

  }
  
  @Test
  void testGetOrden() throws Exception {
    String token = obtainAccessToken();

    when(ordenBusiness.list()).thenReturn(null);

    mockMvc.perform(get("/api/v1/camion")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentType("application/json"));

  }
  

  private String obtainAccessToken() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("username", "admin");
    params.add("password", "admin");
    params.add("json", "false");

    ResultActions result = mockMvc.perform(post("/api/v1/login")
        .params(params))
        .andExpect(status().isOk());

    String resultString = result.andReturn().getResponse().getContentAsString();

    return resultString;

  }

}