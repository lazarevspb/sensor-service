package ru.lazarev.sensorservice.cache.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.lazarev.sensorservice.data.TestsData;
import ru.lazarev.sensorservice.dto.DevicesDTO;
import ru.lazarev.sensorservice.exception.ServiceException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class TemperatureCacheImplTest {

    public static final String ERROR_MESSAGE = "Temperature data cache is missing data";
    @InjectMocks
    TemperatureCacheImpl temperatureCache;

    @Test
    void save_measurement() {
        var expected = TestsData.getDevicesDTO();
        temperatureCache.saveTemperature(expected);

        var actual = temperatureCache.getTemperature();

        assertEquals(expected, actual);
    }

    @Test
    void get_measurement() {
        var expected = TestsData.getDevicesDTO();
        temperatureCache.saveTemperature(expected);

        var actual = temperatureCache.getTemperature();

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void throw_exception_if_cache_is_empty() {
        ServiceException exception = assertThrows(ServiceException.class, () -> temperatureCache.getTemperature());

        assertEquals(ERROR_MESSAGE, exception.getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getErrorCode());
    }

    @Test
    void only_the_last_device_is_saved() {
        var expected = TestsData.getDevicesDTO();
        temperatureCache.saveTemperature(new DevicesDTO());
        temperatureCache.saveTemperature(expected);

        DevicesDTO actual = temperatureCache.getTemperature();

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}