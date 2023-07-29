package az.ingress.ms.service

import az.ingress.ms.dao.entity.Address
import az.ingress.ms.dao.repository.AddressRepository
import az.ingress.ms.exception.NotFoundException
import az.ingress.ms.model.request.SaveAddressRequest
import az.ingress.ms.model.request.UpdateAddressRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class AddressServiceTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private AddressService addressService
    private AddressRepository addressRepository

    def setup(){
        addressRepository = Mock()
        addressService = new AddressService(addressRepository)
    }

    def "TestGetAddressById success"(){
        given:
        def id = random.nextObject(Long)
        def  entity = random.nextObject(Address)

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

    def "TestGetAddressById entity not found"(){
        given:
        def id = random.nextObject(Long)

        when:
        addressService.getAddressById(id)

        then:
        1 * addressRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "ADDRESS_NOT_FOUND"

    }

//    def "TestCreateAddress success"(){
//        given:
//        def request = new SaveAddressRequest()
//        def address = AddressMapper.buildToEntity(request)
//
//        when:
//        addressService.createAddress(request)
//
//        then:
//        1 * addressRepository.save(address)
//    }

    def "TestCreateAddress success"(){
        given:
        def request = new SaveAddressRequest()
        def address = new Address()

        when:
        addressService.createAddress(request)

        then:
        1 * addressRepository.save(address)
        address.street == request.street
        address.state == request.state
        address.postalCode == request.postalCode
        address.city == request.city
    }

    def "TestUpdateAddress succes"(){
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateAddressRequest)
        def address = random.nextObject(Address)

        when:
        addressService.updateAddress(id, request)

        then:
        1 * addressRepository.findById(id) >> Optional.of(address)
        1 * addressRepository.save(address)
    }

    def "TestUpdateAddress entity not found"(){
        given:
        def id = random.nextObject(Long)
        def request = random.nextObject(UpdateAddressRequest)

        when:
        addressService.updateAddress(id,request)

        then:
        1 * addressRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.message == "ADDRESS_NOT_FOUND"
    }

    def "TestDeleteAddress success"(){
        given:
        def id = random.nextObject(Long)
        def address = random.nextObject(Address)

        when:
        addressService.deleteAddress(id)

        then:
        1 * addressRepository.findById(id) >> Optional.of(address)
        1 * addressRepository.save(address)
    }

    def "TestDeleteAddress entity not found"(){
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
