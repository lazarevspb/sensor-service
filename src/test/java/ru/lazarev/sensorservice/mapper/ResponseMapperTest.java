package ru.lazarev.sensorservice.mapper;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lazarev.sensorservice.dto.MeasurementDTO;
import ru.lazarev.sensorservice.dto.SensorDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseMapperTest {

    @InjectMocks
    ResponseMapper responseMapper = Mappers.getMapper(ResponseMapper.class);

    @Test
    void testMapToResponse() {
        var sensorDTO = new SensorDTO();
        sensorDTO.setLastSeen(3L);
        var measurementDTO = new MeasurementDTO();
        measurementDTO.setC(2L);
        measurementDTO.setTs(1L);
        measurementDTO.setT1(20.5);
        measurementDTO.setT2(21.0);
        measurementDTO.setH(50.0);
        measurementDTO.setH2(55.0);
        sensorDTO.setMeasurement(measurementDTO);

        var deviceResponseDTO = responseMapper.mapToResponse(sensorDTO);

        assertEquals(Double.valueOf(20.5), deviceResponseDTO.getTIn());
        assertEquals(Double.valueOf(21.0), deviceResponseDTO.getTOut());
        assertEquals(Double.valueOf(50.0), deviceResponseDTO.getHIn());
        assertEquals(Double.valueOf(55.0), deviceResponseDTO.getHOut());
        assertEquals(Long.valueOf(1L), deviceResponseDTO.getMeasurementTime());
        assertEquals(Long.valueOf(2L), deviceResponseDTO.getServerReceivedTime());
        assertEquals(Long.valueOf(3L), deviceResponseDTO.getLastSeen());
    }
}