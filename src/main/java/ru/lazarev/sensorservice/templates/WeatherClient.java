package ru.lazarev.sensorservice.templates;

import ru.lazarev.sensorservice.dto.DevicesDTO;

public interface WeatherClient {
    DevicesDTO getTemperature();
}
