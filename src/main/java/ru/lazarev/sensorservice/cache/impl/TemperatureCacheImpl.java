package ru.lazarev.sensorservice.cache.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.lazarev.sensorservice.cache.TemperatureCache;
import ru.lazarev.sensorservice.dto.DevicesDTO;
import ru.lazarev.sensorservice.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TemperatureCacheImpl implements TemperatureCache {
    List<DevicesDTO> data = new ArrayList<>();

    @Override
    public DevicesDTO getTemperature() {
        isEmptyThrow();
        return data.get(0);
    }

    @Override
    public void saveTemperature(DevicesDTO device) {
        addNewMeasurement(device);
    }

    private void addNewMeasurement(DevicesDTO device) {
        if (data.isEmpty()) {
            data.add(device);
        } else {
            data.set(0, device);
        }
    }

    private void isEmptyThrow() {
        if (data.isEmpty()) {
            throw new ServiceException("Temperature data cache is missing data", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
