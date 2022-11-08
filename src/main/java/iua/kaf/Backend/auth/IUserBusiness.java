package iua.kaf.Backend.auth;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.NotFoundException;

public interface IUserBusiness {

  public List<User> list() throws BusinessException;

  public User load(String usernameOrEmail) throws NotFoundException, BusinessException;

  public void changePassword(String usernameOrEmail, String oldPassword, String newPassword, PasswordEncoder pEncoder)
      throws BadPasswordException, NotFoundException, BusinessException;

  public void disable(String usernameOrEmail) throws NotFoundException, BusinessException;

  public void enable(String usernameOrEmail) throws NotFoundException, BusinessException;
}