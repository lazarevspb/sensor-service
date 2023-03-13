package ru.lazarev.sensorservice.assertions;

import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import ru.lazarev.sensorservice.data.TestsData;
import ru.lazarev.sensorservice.dto.DeviceResponseDTO;
import ru.lazarev.sensorservice.dto.MeasurementDTO;
import ru.lazarev.sensorservice.dto.ResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@UtilityClass
public class AssertResponse {

    public void assertResponse(ResponseEntity<ResponseDto> response) {
        assertNotNull(response.getBody());

        var body = response.getBody();
        var expectedSensor = TestsData.getDevicesDTO().getDevices().get(0);
        var actual = body.getDevices().get(0);

        assertEquals(expectedSensor.getLastSeen(), actual.getLastSeen());

        var measurement = expectedSensor.getMeasurement();

        assertMeasurement(actual, measurement);
    }

    private static void assertMeasurement(DeviceResponseDTO actual, MeasurementDTO measurement) {
        assertEquals(measurement.getT1(), actual.getTIn());
        assertEquals(measurement.getT2(), actual.getTOut());
        assertEquals(measurement.getH(), actual.getHIn());
        assertEquals(measurement.getH2(), actual.getHOut());
        assertEquals(measurement.getC(), actual.getServerReceivedTime());
        assertEquals(measurement.getTs(), actual.getMeasurementTime());
    }
}
