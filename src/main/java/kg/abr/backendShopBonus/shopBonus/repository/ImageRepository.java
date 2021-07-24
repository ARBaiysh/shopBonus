package kg.abr.backendShopBonus.shopBonus.repository;

import kg.abr.backendShopBonus.shopBonus.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {

}
