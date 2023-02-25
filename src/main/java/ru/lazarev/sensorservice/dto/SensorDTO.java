package ru.lazarev.sensorservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SensorDTO {
    @JsonProperty("deviceid")
    String deviceId;
    //lastseen: Временная метка, когда датчик в последний раз был замечен сервером в
    //эпохальном времени.
    @JsonProperty("lastseen")
    Long lastSeen;
    @JsonProperty("lowbattery")
    Boolean lowBattery;
    @JsonProperty("measurement")
    MeasurementDTO measurement;
}
