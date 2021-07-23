package kg.abr.backendShopBonus.shopBonus.repository;


import kg.abr.backendShopBonus.shopBonus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findById (String email);

    boolean existsUserByEmailOrUsername(String email, String username);

}
