package com.celebrate.backend.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import com.celebrate.backend.exception.CepNotFoundException;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            return new CepNotFoundException("CEP não encontrado ou inválido");
        }
        // Delegar ao decoder padrão do Feign para outros erros
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
