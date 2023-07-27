package az.ingress.ms.dao.repository;

import az.ingress.ms.dao.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByUsernameContaining(String username);
}
