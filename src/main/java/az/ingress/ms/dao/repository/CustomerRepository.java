package az.ingress.ms.dao.repository;

import az.ingress.ms.dao.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


//    @Query(value = "SELECT c from Customer c join fetch c.cards where c.username=:username")
//    @EntityGraph(attributePaths = {"cards"})
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "customer_entity_graph")
    List<Customer> findByUsernameContaining(String username);

}
