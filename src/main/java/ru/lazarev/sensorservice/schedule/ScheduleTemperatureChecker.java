package ru.lazarev.sensorservice.schedule;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.lazarev.sensorservice.cache.TemperatureCache;
import ru.lazarev.sensorservice.templates.WeatherClient;

@Slf4j
@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleTemperatureChecker {

    WeatherClient weatherClient;
    TemperatureCache cache;


    @Scheduled(fixedDelay = 300_000) // Run every 5 minutes
    public void getTemperature() {
        log.info("Scheduler task started");
        var temperature = weatherClient.getTemperature();
        log.info("Temperature dto recieved: {}", temperature);
        cache.saveTemperature(temperature);
        log.info("Scheduler task ended");
    }
}
