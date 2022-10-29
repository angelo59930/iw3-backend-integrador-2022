package iua.kaf.Backend.controller;

import java.util.ArrayList;
import java.util.Date;
import iua.kaf.Backend.auth.User;
import iua.kaf.Backend.auth.UserSlimJsonSerializer;
import iua.kaf.Backend.auth.custom.CustomAuthenticationManager;
import iua.kaf.Backend.auth.filters.AuthConstants;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.util.JsonUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@RestController
public class AuthRestController extends BaseRestController {

  @Autowired
  private AuthenticationManager authManager;

  @PostMapping(value = Constantes.URL_LOGIN, produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<?> loginExternalOnlyToken(@RequestParam(value = "username") String username,
      @RequestParam(value = "password") String password,
      @RequestParam(value = "json") Boolean json) throws NotFoundException, BusinessException {

    Authentication auth = null;
    try {
      auth = authManager.authenticate(((CustomAuthenticationManager) authManager).AuthWrap(username, password));
    } catch (AuthenticationServiceException e0) {
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (AuthenticationException e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    User user = (User) auth.getPrincipal();
    String token = JWT.create().withSubject(user.getUsername())
        .withClaim("internalId", user.getIdUser())
        .withClaim("roles", new ArrayList<String>(user.getAuthoritiesStr()))
        .withClaim("email", user.getEmail())
        .withClaim("version", "1.0.0")
        .withExpiresAt(new Date(System.currentTimeMillis() + AuthConstants.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(AuthConstants.SECRET.getBytes()));

    if (json) {
      user.setToken(token);
      StdSerializer<User> ser = null;
      String result;
      try {
        ser = new UserSlimJsonSerializer(User.class, false);
        result = JsonUtiles.getObjectMapper(User.class, ser, null)
            .writeValueAsString(user);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error al momento de crear el json del user", HttpStatus.INTERNAL_SERVER_ERROR);
      }

      return new ResponseEntity<>(result, HttpStatus.OK);

    } else {
      return new ResponseEntity<String>(token, HttpStatus.OK);
    }
  }
}