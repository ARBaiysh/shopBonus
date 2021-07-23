package kg.abr.backendShopBonus.shopBonus.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class MessageResponse {
   private final String message;
}
