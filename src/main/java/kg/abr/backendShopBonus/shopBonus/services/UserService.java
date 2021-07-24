package kg.abr.backendShopBonus.shopBonus.services;

import kg.abr.backendShopBonus.shopBonus.dto.UserDTO;
import kg.abr.backendShopBonus.shopBonus.entity.User;
import kg.abr.backendShopBonus.shopBonus.entity.enums.ERole;
import kg.abr.backendShopBonus.shopBonus.entity.enums.EStatus;
import kg.abr.backendShopBonus.shopBonus.exceptions.UserExistException;
import kg.abr.backendShopBonus.shopBonus.payload.request.SignUpRequest;
import kg.abr.backendShopBonus.shopBonus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(SignUpRequest userIn) {
        if (userRepository.existsUserByEmailOrUsername(userIn.getEmail(), userIn.getUsername())) {
            throw new UserExistException("The user " + userIn.getUsername() + " already exist. Please check credentials");
        } else {
            User user = new User();
            user.setEmail(userIn.getEmail());
            user.setUsername(userIn.getUsername());
            user.setPassword(passwordEncoder.encode(userIn.getPassword()));
            user.getRoles().add(ERole.ROLE_USER);
            user.setStatus(EStatus.ACTIVE);

            log.info("Saving User {}", userIn.getEmail());
            userRepository.save(user);
        }
    }

    public User updateStatus(UserDTO userDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        user.setStatus(userDTO.getStatus());


        return userRepository.save(user);
    }


    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
