package iua.kaf.Backend.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findOneByUsernameOrEmail(String username, String email);
}