package ru.lazarev.sensorservice.services.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.lazarev.sensorservice.cache.TemperatureCache;
import ru.lazarev.sensorservice.dto.ResponseDto;
import ru.lazarev.sensorservice.mapper.ResponseMapper;
import ru.lazarev.sensorservice.services.TemperatureService;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TemperatureServiceImpl implements TemperatureService {
    TemperatureCache cache;
    ResponseMapper mapper;

    @Override
    public ResponseDto getTemperature() {
        var temperature = cache.getTemperature();
        var responseDTOList = temperature.getDevices().stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .devices(responseDTOList)
                .build();
    }

}
