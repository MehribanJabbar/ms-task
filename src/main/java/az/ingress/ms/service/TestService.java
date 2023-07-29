package az.ingress.ms.service;

import az.ingress.ms.dao.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final CustomerRepository customerRepository;

    @PostConstruct
    public void test(){
        var customer = customerRepository.findByUsernameContaining("mehribanjabbar");
        customer.get(0).getCards().forEach(it -> System.out.println(it.getId()));
    }

}
