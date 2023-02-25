package ru.lazarev.sensorservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeviceResponseDTO {
    Double tIn;
    Double tOut;
    Double hIn;
    Double hOut;
    Long measurementTime;
    Long serverReceivedTime;
    Long lastSeen;
}
