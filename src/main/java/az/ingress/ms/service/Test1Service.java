//package az.ingress.ms.service;
//
//import az.ingress.ms.dao.entity.AddressEntity;
//import az.ingress.ms.dao.entity.CardsEntity;
//import az.ingress.ms.dao.entity.CustomerEntity;
//import az.ingress.ms.dao.repository.CardsRepository;
//import az.ingress.ms.dao.repository.CustomerRepository;
//import az.ingress.ms.exception.NotFoundException;
//import az.ingress.ms.model.enums.CardStatus;
//import az.ingress.ms.model.enums.Status;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//@Service
//@RequiredArgsConstructor
//public class Test1Service {
//    private final CardsRepository cardsRepository;
//    private final CustomerRepository customerRepository;
//
//
//    @Transactional
//    public void test() throws Exception{
//        test4();;
//    }
//    @Transactional // (rollbackFor=Exception.class), (noRollbackFor=NotFoundException.class)
//    public void test3() throws Exception{
//
//        cardsRepository.save(new CardsEntity(5L, "pan" , "cvv",
//                LocalDate.now(), "Cards holder", null, null, CardStatus.BLOCKED, new CustomerEntity()));
//
//        customerRepository.save(new CustomerEntity(null, "User name", 25, "Birth Place",
//                null, null, Status.ACTIVE, new ArrayList<>(), new AddressEntity(), new ArrayList<>()));
//
//        test2();
//    }
//
//    @Transactional
//    public void test4(){
//        var card=cardsRepository.findById(1L).get();
//        card.setCardsHolder("New card holder");
//
//        var card2=new CardsEntity(null, "pan", "cvv", LocalDate.now(),
//                "CardsHolder", null, null, CardStatus.ACTIVE, new CustomerEntity());
//        card2.setCardsHolder("new Cards Holder");
//    }
//
//    public void test2() throws  Exception{
//        throw new NotFoundException("Not found");
//    }
//}
