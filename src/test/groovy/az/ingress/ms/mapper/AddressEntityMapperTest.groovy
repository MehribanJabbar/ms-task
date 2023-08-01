package az.ingress.ms.mapper

import az.ingress.ms.dao.entity.AddressEntity

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class AddressEntityMapperTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private AddressMapper addressMapper

    def "TestBuildToResponse"(){
        given:
        def address = random.nextObject(AddressEntity)

        when:
        def actual = AddressMapper.buildToResponse(address)

        then:
        actual.id == address.id
        actual.city == address.city
        actual.postalCode == address.postalCode
        actual.state == address.state
        actual.street == address.street
    }

    def "TestBuildToEntity"(){
        given:
        def address = random.nextObject(SaveAddressRequest)

        when:
        def actual = AddressMapper.buildToEntity(address)

        then:
        actual.street == address.street
        actual.state == address.state
        actual.postalCode == address.postalCode
        actual.city == address.city
    }
}
