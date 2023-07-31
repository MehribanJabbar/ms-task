package az.ingress.ms.dao.repository;

import az.ingress.ms.dao.entity.CustomerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {


//    @Query(value = "SELECT c from Customer c join fetch c.cards where c.username=:username")
//    @EntityGraph(attributePaths = {"cards"})
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "customer_entity_graph")
    List<CustomerEntity> findByUsernameContaining(String username);

}
