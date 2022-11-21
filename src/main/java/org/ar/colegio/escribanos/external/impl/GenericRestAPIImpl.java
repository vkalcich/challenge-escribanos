package org.ar.colegio.escribanos.external.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ar.colegio.escribanos.external.GenericRestAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class GenericRestAPIImpl implements GenericRestAPI {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    private static Logger log = LoggerFactory.getLogger(GenericRestAPIImpl.class);


    @Override
    public <T> T makeGetRequest(String url, Class<T> anyClass, HttpHeaders headers) throws Exception {
        T resultado = null;
        ResponseEntity<String> responseFromProvider = executeGetRequest(url, headers);
        JavaType type = objectMapper.getTypeFactory().constructType(anyClass);
        resultado = readValue(responseFromProvider, type);

        return resultado;
    }


    private ResponseEntity<String> executeGetRequest(String url, HttpHeaders headers) throws Exception {
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> response = null;
        response = restTemplate.exchange(new URI(url), HttpMethod.GET, entity, new ParameterizedTypeReference<String>() {
        });
        return response;
    }

    private <T> T readValue(ResponseEntity<String> response, JavaType javaType) throws Exception {
        T result = null;
        if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
            result = objectMapper.readValue(response.getBody(), javaType);

        } else {
            log.info("No data found {}", response.getStatusCode());
            throw new Exception("Sin datos");
        }
        return result;
    }

}
