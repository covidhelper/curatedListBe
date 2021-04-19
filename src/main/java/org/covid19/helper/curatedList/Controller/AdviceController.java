package org.covid19.helper.curatedList.Controller;

import lombok.extern.slf4j.Slf4j;
import org.covid19.helper.curatedList.DTO.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<ResultDTO> handleGenericException(Exception ex) {
        log.error("Error: ", ex);
        ResultDTO resultDTO = new ResultDTO(ex.getMessage(), 400);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

}
