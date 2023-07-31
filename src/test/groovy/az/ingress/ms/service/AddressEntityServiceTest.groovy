package az.ingress.ms.service

import az.ingress.ms.dao.entity.AddressEntity
import az.ingress.ms.dao.repository.AddressRepository
import az.ingress.ms.exception.NotFoundException
import az.ingress.ms.model.request.SaveAddressRequest
import az.ingress.ms.model.request.UpdateAddressRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class AddressEntityServiceTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private AddressService addressService
    private AddressRepository addressRepository

    def setup() {
        addressRepository = Mock()
        addressService = new AddressService(addressRepository)
    }

    def "TestGetAllAddress"() {
        given:
        def address = random.nextObject(AddressEntity)

        when:
        def actual = addressService.getAllAddress()

        then:
        1 * addressRepository.findAll() >> List.of(address)
        actual[0].id == address.id
        actual[0].city == address.city
        actual[0].postalCode == address.postalCode
        actual[0].state == address.state
        actual[0].street == address.street
    }

    def "TestGetAddressById success"() {
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(AddressEntity)

        when:
        def actual = addressService.getAddressById(id)

        then:
        addressRepository.findById(id) >> Optional.of(entity)
        actual.id == entity.id
        actual.city == entity.city
        actual.postalCode == entity.postalCode
        actual.state == entity.state
        actual.street == entity.street
    }

    def "TestGetAddressById entity not found"() {
        given:
        def id = random.nextObject(Long)

        when:
        addressService.getAddressById(id)

        then:
        1 * addressRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "ADDRESS_NOT_FOUND"

    }

    def "TestCreateAddress success"() {
        given:
        def request = random.nextObject(SaveAddressRequest)
        def address = AddressEntity.builder()
                .city(request.city)
                .state(request.state)
                .street(request.street)
                .postalCode(request.postalCode)
                .build()

        when:
        addressService.createAddress(request)

        then:
        1 * addressRepository.save(address)
        address.street == request.street
        address.state == request.state
        address.postalCode == request.postalCode
        address.city == request.city
    }

    def "TestUpdateAddress succes"() {
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateAddressRequest)
        def address = random.nextObject(AddressEntity)

        when:
        addressService.updateAddress(id, request)

        then:
        1 * addressRepository.findById(id) >> Optional.of(address)
        1 * addressRepository.save(address)
        request.postalCode == address.postalCode
        request.street == address.street
        request.state == address.state
        request.street == address.street
    }

    def "TestUpdateAddress entity not found"() {
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateAddressRequest)

        when:
        addressService.updateAddress(id, request)

        then:
        1 * addressRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "ADDRESS_NOT_FOUND"
    }

    def "TestDeleteAddress success"() {
        given:
        def id = random.nextObject(Long)
        def address = random.nextObject(AddressEntity)

        when:
        addressService.deleteAddress(id)

        then:
        1 * addressRepository.findById(id) >> Optional.of(address)
        1 * addressRepository.save(address)
    }

    def "TestDeleteAddress entity not found"() {
        given:
        def id = random.nextObject(Long)

        when:
        addressService.deleteAddress(id)

        then:
        1 * addressRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "ADDRESS_NOT_FOUND"
    }

}
