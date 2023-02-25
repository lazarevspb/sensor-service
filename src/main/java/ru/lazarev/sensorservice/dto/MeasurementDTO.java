package ru.lazarev.sensorservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MeasurementDTO {
    //idx: Уникальный идентификатор измерения
    Integer idx;
    //ts: Временная метка измерения в эпохальном времени
    Long ts;
    //c: Временная метка, когда измерение было получено сервером
    Long c;
    Boolean lb;
    Double t1;
    Double t2;
    Double h;
    Double h2;

    /*Если датчик не был подключен, возвращается значение 43530.
• Если измерение датчика вышло за пределы диапазона, возвращается значение
65295.*/
}

