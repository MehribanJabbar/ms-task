package az.ingress.ms.dao.repository;

import az.ingress.ms.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
