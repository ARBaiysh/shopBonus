package kg.abr.backendShopBonus.shopBonus;

import kg.abr.backendShopBonus.shopBonus.entity.User;
import kg.abr.backendShopBonus.shopBonus.entity.enums.ERole;
import kg.abr.backendShopBonus.shopBonus.payload.request.SignUpRequest;
import kg.abr.backendShopBonus.shopBonus.repository.UserRepository;
import kg.abr.backendShopBonus.shopBonus.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class ShopBonusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBonusApplication.class, args);
    }


    @Bean
    CommandLineRunner run(UserService userService, UserRepository userRepository) {
        return args -> {
            SignUpRequest userAdmin = new SignUpRequest();
            userAdmin.setEmail("admin@mail.ru");
            userAdmin.setUsername("admin");
            userAdmin.setPassword("123456789");
            userAdmin.setConfirmPassword("123456789");
            userService.createUser(userAdmin);

            User admin = userService.getUserByName("admin");
            log.info(">>>>> " + admin.getUsername() + " : " + admin.getId());
            userService.addRoleToUser(admin.getId(), ERole.ROLE_ADMIN);


            SignUpRequest userClain = new SignUpRequest();
            userClain.setEmail("test@mail.ru");
            userClain.setUsername("test");
            userClain.setPassword("123456789");
            userClain.setConfirmPassword("123456789");
            userService.createUser(userClain);

            User test = userService.getUserByName("test");
            log.info(">>>>> " + test.getUsername() + " : " + test.getId());

        };
    }
}
