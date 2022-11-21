package org.ar.colegio.escribanos.external;

import org.springframework.http.HttpHeaders;

public interface GenericRestAPI {

    <T> T makeGetRequest(String url, Class<T> anyClass, HttpHeaders headers) throws Exception;

}