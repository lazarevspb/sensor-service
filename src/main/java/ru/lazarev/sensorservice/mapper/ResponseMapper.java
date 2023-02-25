package ru.lazarev.sensorservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lazarev.sensorservice.dto.SensorDTO;
import ru.lazarev.sensorservice.dto.DeviceResponseDTO;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    @Mapping(expression = "java(sensor.getLastSeen())", target = "lastSeen")
    @Mapping(expression = "java(sensor.getMeasurement().getC())", target = "serverReceivedTime")
    @Mapping(expression = "java(sensor.getMeasurement().getTs())", target = "measurementTime")
    @Mapping(expression = "java(sensor.getMeasurement().getT1())", target = "TIn")
    @Mapping(expression = "java(sensor.getMeasurement().getT2())", target = "TOut")
    @Mapping(expression = "java(sensor.getMeasurement().getH())", target = "HIn")
    @Mapping(expression = "java(sensor.getMeasurement().getH2())", target = "HOut")
    DeviceResponseDTO mapToResponse(SensorDTO sensor);
}
