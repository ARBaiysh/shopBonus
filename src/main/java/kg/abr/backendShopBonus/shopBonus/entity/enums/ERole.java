package kg.abr.backendShopBonus.shopBonus.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public enum ERole {
    ROLE_USER, ROLE_MANAGER,
    ROLE_MODERATOR, ROLE_ADMIN
}
