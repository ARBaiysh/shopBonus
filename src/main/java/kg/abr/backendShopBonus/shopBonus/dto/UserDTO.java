package kg.abr.backendShopBonus.shopBonus.dto;

import kg.abr.backendShopBonus.shopBonus.entity.enums.ERole;
import kg.abr.backendShopBonus.shopBonus.entity.enums.EStatus;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String id;
    private String username;
    private EStatus status;
    private Set<ERole> roles;

}
