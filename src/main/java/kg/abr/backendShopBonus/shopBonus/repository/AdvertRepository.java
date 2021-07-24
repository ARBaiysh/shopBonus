package kg.abr.backendShopBonus.shopBonus.repository;

import kg.abr.backendShopBonus.shopBonus.entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, String> {
}
