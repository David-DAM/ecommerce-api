package com.david.ecommerceapi.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {

    @Scheduled(fixedRate = 30000)
    public void sendReportOrders() {


    }

}
