package az.ingress.ms.scheduler.service;

import az.ingress.ms.service.CardService;
import az.ingress.ms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerService {

    private final CardService cardService;
    private final CustomerService customerService;

    @Scheduled(fixedDelay = 5000)
    @SchedulerLock(name = "cardsServiceRunning", lockAtLeastFor = "PT1M", lockAtMostFor = "PT5M")
    public void cardsServiceRunning(){
        log.info("m");
        cardService.cardServiceRunning();
    }

    @Scheduled(cron = "0 * * * * ?")
    @SchedulerLock(name = "customerServiceRunning")
    public void customerServiceRunning(){
        customerService.customerServiceRunning();
    }


}
