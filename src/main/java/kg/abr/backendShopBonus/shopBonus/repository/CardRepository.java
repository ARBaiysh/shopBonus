package kg.abr.backendShopBonus.shopBonus.repository;

import kg.abr.backendShopBonus.shopBonus.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository  extends JpaRepository<Card, String> {
}
