package kg.abr.backendShopBonus.shopBonus.controller;

import kg.abr.backendShopBonus.shopBonus.dto.UserDTO;
import kg.abr.backendShopBonus.shopBonus.entity.User;
import kg.abr.backendShopBonus.shopBonus.entity.enums.ERole;
import kg.abr.backendShopBonus.shopBonus.facade.UserFacade;
import kg.abr.backendShopBonus.shopBonus.services.UserService;
import kg.abr.backendShopBonus.shopBonus.validations.ResponseErrorValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;
    private final ResponseErrorValidation responseErrorValidation;


    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        User user = userService.getCurrentUser(principal);
        UserDTO userDTO = userFacade.userToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        UserDTO userDTO = userFacade.userToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        UserDTO userDTO = userFacade.userToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/editStatus")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> editStatus(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.updateStatus(userDTO, principal);


        UserDTO userUpdated = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @PostMapping("/addRoleToUser/{userId}:{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addRoleToUser(@PathVariable String userId, @PathVariable String role) {

        User user;
        if (role.equals(ERole.ROLE_ADMIN.name())) {
            user = userService.addRoleToUser(userId, ERole.ROLE_ADMIN);
        } else if (role.equals(ERole.ROLE_MANAGER.name())) {
            user = userService.addRoleToUser(userId, ERole.ROLE_MANAGER);
        } else if (role.equals(ERole.ROLE_MODERATOR.name())) {
            user = userService.addRoleToUser(userId, ERole.ROLE_MODERATOR);
        } else {
            return new ResponseEntity<>(String.format("There is no %s role in the database", role), HttpStatus.BAD_REQUEST);
        }

        UserDTO userUpdated = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }
}
