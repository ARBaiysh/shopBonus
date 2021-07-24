package kg.abr.backendShopBonus.shopBonus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageBytes;

    @JsonIgnore
    private String StoreId;

    @JsonIgnore
    private String cardId;

    @JsonIgnore
    private String advertId;
}
