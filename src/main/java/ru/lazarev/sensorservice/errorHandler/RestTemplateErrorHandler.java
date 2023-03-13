package ru.lazarev.sensorservice.errorHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.lazarev.sensorservice.exception.ServiceException;

import java.io.IOException;

@Slf4j
@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().is4xxClientError() || httpResponse.getStatusCode().is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        log.error("Error: {}, status code {}",  httpResponse.getStatusCode(), httpResponse.getStatusCode().value());
        throw new ServiceException(httpResponse.getStatusText(), httpResponse.getStatusCode().value());
    }
}