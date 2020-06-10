package com.walleftech.csbe.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class ErroAPI {

    private List<String> erros;

    public ErroAPI(String mensagem) {
        this.erros = Arrays.asList(mensagem);
    }
}
