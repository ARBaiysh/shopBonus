package kg.abr.backendShopBonus.shopBonus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true)
    private String id;

    @Column(unique = true)
    private String name;

    private String address;

    @Column(columnDefinition = "text")
    private String description;

    @OneToOne
    private ImageModel imageModel;

    private String geo;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "store")
    private Set<ProductAdvertising> productAdvertisements = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "store")
    private Set<Advert> adverts = new HashSet<>();

    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }


}
