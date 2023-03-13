package ru.lazarev.sensorservice.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;
import ru.lazarev.sensorservice.assertions.AssertResponse;
import ru.lazarev.sensorservice.data.TestsData;
import ru.lazarev.sensorservice.dto.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.yaml")
public class ControllerIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @TestConfiguration
    static class Config {
        @Bean
        public RestTemplate restTemplate() {
            var restTemplate = Mockito.mock(RestTemplate.class);
            var devicesDTO = TestsData.getDevicesDTO();
            when(restTemplate.postForEntity(anyString(), any(), Mockito.<Class<DevicesDTO>>any()))
                    .thenReturn(ResponseEntity.ok(devicesDTO));
            return restTemplate;
        }
    }

    @Test
    void getRequestsCount() {
    }

    @Test
    void getTemperature() {
        var response = testRestTemplate.getForEntity("/temperature", ResponseDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        AssertResponse.assertResponse(response);
    }
}
