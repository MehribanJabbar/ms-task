package az.ingress.ms.dao.repository;

import az.ingress.ms.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
