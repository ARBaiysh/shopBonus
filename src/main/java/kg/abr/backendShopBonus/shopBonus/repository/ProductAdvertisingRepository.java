package kg.abr.backendShopBonus.shopBonus.repository;

import kg.abr.backendShopBonus.shopBonus.entity.ProductAdvertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAdvertisingRepository extends JpaRepository<ProductAdvertising, String> {
}
