package kg.abr.backendShopBonus.shopBonus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import kg.abr.backendShopBonus.shopBonus.entity.enums.ERole;
import kg.abr.backendShopBonus.shopBonus.entity.enums.EStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true)
    private String id;

    @Column(unique = true, updatable = false)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(length = 3000)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_card",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "card_id") })
    private Set<Card> cards = new HashSet<>();

    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private EStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;


    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    public User(String id, String username, String email, String password, EStatus status, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return status.equals(EStatus.ACTIVE);
    }
}
