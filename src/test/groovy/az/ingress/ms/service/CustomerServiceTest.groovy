package az.ingress.ms.service

import az.ingress.ms.dao.entity.Customer
import az.ingress.ms.dao.repository.CustomerRepository
import az.ingress.ms.exception.NotFoundException
import az.ingress.ms.mapper.CustomerMapper
import az.ingress.ms.model.request.SaveCustomerRequest
import az.ingress.ms.model.request.UpdateCustomerRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CustomerServiceTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private CustomerService customerService
    private CustomerRepository customerRepository

    def setup(){
        customerRepository = Mock()
        customerService = new CustomerService(customerRepository)
    }

    def "TestGetCustomerById success"(){
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(Customer)

        when:
        def actual = customerService.getCustomerById(id)

        then:
        1 * customerRepository.findById(id) >> Optional.of(entity)
        actual.id == entity.id
        actual.age == entity.age
        actual.username == entity.username
        actual.birthDate == entity.birthDate
        actual.birthPlace == entity.birthPlace
    }

    def "TestGetCardById entity not found"(){
        given:
        def id = random.nextObject(Long)

        when:
        customerService.getCustomerById(id)

        then:
        1 * customerRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "CUSTOMER_NOT_FOUND"
    }

//    def "TestCreateCustomer success"(){
//        given:
//        def request = new SaveCustomerRequest()
//        def customer = CustomerMapper.buildToEntity(request)
//
//        when:
//        customerService.createCustomer(request)
//
//        then:
//        1 * customerRepository.save(customer)
//
//    }

    def "TestCreateCustomer success"(){
        given:
        def request = new SaveCustomerRequest()
        def entity = new Customer()

        when:
        customerService.createCustomer(request)

        then:
        1 * customerRepository.save(entity)
        entity.birthDate == request.birthDate
        entity.birthPlace == request.birthPlace
        entity.age == request.age
        entity.username == request.username
    }

    def "TestUpdateCustomer success"(){
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateCustomerRequest)
        def customer = random.nextObject(Customer)

        when:
        customerService.updateCustomer(id, request)

        then:
        1 * customerRepository.findById(id) >> Optional.of(customer)
        1 * customerRepository.save(customer)
    }

    def "TestUpdateCustomer entity not found"(){
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateCustomerRequest)

        when:
        customerService.updateCustomer(id, request)

        then:
        1 * customerRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "CUSTOMER_NOT_FOUND"
    }

    def "TestDeleteCustomer success"(){
        given:
        def id = random.nextObject(Long)
        def customer = random.nextObject(Customer)

        when:
        customerService.deleteCustomer(id)

        then:
        1 * customerRepository.findById(id) >> Optional.of(customer)
        1 * customerRepository.save(customer)
    }

    def "TestDeleteCustomer entity not found"(){
        given:
        def id = random.nextObject(Long)

        when:
        customerService.deleteCustomer(id)

        then:
        1 * customerRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "CUSTOMER_NOT_FOUND"
    }
}
