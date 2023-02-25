package ru.lazarev.sensorservice.data;

import lombok.experimental.UtilityClass;
import ru.lazarev.sensorservice.dto.DevicesDTO;
import ru.lazarev.sensorservice.dto.MeasurementDTO;
import ru.lazarev.sensorservice.dto.SensorDTO;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TestsData {
    public static DevicesDTO getDevicesDTO() {
        List<SensorDTO> sensorDTOList = new ArrayList<>();

        var measurement = new MeasurementDTO();
        measurement.setTs(1L);
        measurement.setH2(11.1);
        measurement.setC(2L);
        measurement.setH(11.2);
        measurement.setT1(12.1);
        measurement.setT2(12.2);

        var sensorDTO = new SensorDTO();
        sensorDTO.setLastSeen(3L);
        sensorDTO.setDeviceId("test_device_id");
        sensorDTO.setMeasurement(measurement);
        sensorDTOList.add(sensorDTO);

        var devicesDTO = new DevicesDTO();
        devicesDTO.setSuccess(true);
        devicesDTO.setDevices(sensorDTOList);
        return devicesDTO;
    }
}
