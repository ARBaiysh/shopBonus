package kg.abr.backendShopBonus.shopBonus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    private int number;

    private double accumulationAmount;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "card")
    private List<Shopping> shoppingSet = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;



    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
