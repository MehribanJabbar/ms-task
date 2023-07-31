package az.ingress.ms.dao.repository;

import az.ingress.ms.dao.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
