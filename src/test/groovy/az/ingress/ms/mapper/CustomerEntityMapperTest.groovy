package az.ingress.ms.mapper

import az.ingress.ms.dao.entity.CustomerEntity
import az.ingress.ms.model.request.SaveCustomerRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CustomerEntityMapperTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private CustomerMapper customerMapper

    def "TestBuildToResponse"(){
        given:
        def customer = random.nextObject(CustomerEntity)

        when:
        def actual = CustomerMapper.buildToResponse(customer)

        then:
        actual.id == customer.id
        actual.age == customer.age
        actual.birthPlace == customer.birthPlace
        actual.username == customer.username
        actual.birthDate == customer.birthDate
    }

    def "TestBuildToEntity"(){
        given:
        def customer = random.nextObject(SaveCustomerRequest)

        when:
        def actual = CustomerMapper.buildToEntity(customer)

        then:
        actual.username == customer.username
        actual.birthPlace == customer.birthPlace
        actual.birthDate == customer.birthDate
        actual.age == customer.age
    }
}
