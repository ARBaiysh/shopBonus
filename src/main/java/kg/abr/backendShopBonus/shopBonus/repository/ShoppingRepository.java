package kg.abr.backendShopBonus.shopBonus.repository;

import kg.abr.backendShopBonus.shopBonus.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping,String> {
}
