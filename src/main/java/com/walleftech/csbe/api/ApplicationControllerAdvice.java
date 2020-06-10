package com.walleftech.csbe.api;

import com.walleftech.csbe.exceptions.ErroAPI;
import com.walleftech.csbe.exceptions.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

        @ExceptionHandler(RecursoNaoEncontradoException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErroAPI handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
            return new ErroAPI(ex.getMessage());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErroAPI handleMethodNotValidException(MethodArgumentNotValidException ex) {
            List<String> erros = ex.getBindingResult().getAllErrors()
                    .stream()
                    .map(
                            erro -> erro.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ErroAPI(erros);
        }
}
