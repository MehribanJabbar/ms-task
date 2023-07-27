package az.ingress.ms.dao.entity;

import az.ingress.ms.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;
    private int age;
    private String birthPlace;
    private LocalDate birthDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(STRING)
    private Status status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = ALL)
    @ToStringExclude
    private List<CardEntity> cards;

    @OneToOne(
            mappedBy = "customer",
            cascade = ALL,
            fetch = LAZY
    )
    @ToStringExclude
    private AddressEntity address;


    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "customer_product",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")

    )
    @ToStringExclude
    private List<ProductEntity> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return age == that.age && Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(birthPlace, that.birthPlace) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, age, birthPlace, createdAt, updatedAt, status);
    }
}
