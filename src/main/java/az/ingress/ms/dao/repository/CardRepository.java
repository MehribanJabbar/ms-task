package az.ingress.ms.dao.repository;

import az.ingress.ms.dao.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
