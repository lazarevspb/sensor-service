package ru.lazarev.sensorservice.templates.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.lazarev.sensorservice.dto.DevicesDTO;
import ru.lazarev.sensorservice.templates.WeatherClient;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PropertySource("classpath:application.yaml")
public class WeatherClientImpl implements WeatherClient {
    public static final String DEVICE_IDS_PARAMETER = "deviceids";
    RestTemplate restTemplate;

    @Value("${temperature.service.url}")
    @NonFinal
    String url;

    @Value("${temperature.service.device.id}")
    @NonFinal
    String deviceId;

    @Override
    public DevicesDTO getTemperature() {
        var request = getRequest();
        var response = restTemplate.postForEntity(url, request, DevicesDTO.class);
        return response.getBody();
    }

    private HttpEntity<MultiValueMap<String, String>> getRequest() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(DEVICE_IDS_PARAMETER, deviceId);
        return new HttpEntity<>(map, headers);
    }
}
