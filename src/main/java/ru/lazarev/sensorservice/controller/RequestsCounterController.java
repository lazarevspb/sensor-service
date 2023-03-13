package ru.lazarev.sensorservice.controller;

import jakarta.servlet.ServletRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lazarev.sensorservice.dto.ResponseDto;
import ru.lazarev.sensorservice.services.TemperatureService;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestsCounterController {

    TemperatureService service;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/requests")
    public Long getRequestsCount(ServletRequest request) {
        log.info("Request incoming: " + request);
        return counter.incrementAndGet();
    }

    @GetMapping("/temperature")
    public ResponseDto getTemperature() {
        return service.getTemperature();
    }
}