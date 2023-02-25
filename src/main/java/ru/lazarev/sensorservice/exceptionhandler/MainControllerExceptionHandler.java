package ru.lazarev.sensorservice.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.lazarev.sensorservice.dto.ErrorDto;
import ru.lazarev.sensorservice.exception.ServiceException;

@ControllerAdvice
public class MainControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<Object> handleHttpClientErrorException(ServiceException ex) {
        var message = ex.getMessage();
        var errorDto = new ErrorDto(ex.getErrorCode(), message);
        return ResponseEntity.ok(errorDto);
    }
}
