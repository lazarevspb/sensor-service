package ru.lazarev.sensorservice.cache;

import ru.lazarev.sensorservice.dto.DevicesDTO;

public interface TemperatureCache {
    DevicesDTO getTemperature();
    void saveTemperature(DevicesDTO device);
}
