package org.ar.colegio.escribanos.external.impl;


import org.ar.colegio.escribanos.AppProperties;
import org.ar.colegio.escribanos.EscribanoResponse;
import org.ar.colegio.escribanos.external.APIEscribano;
import org.ar.colegio.escribanos.external.GenericRestAPI;
import org.ar.colegio.escribanos.util.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class APIEscribanoImpl implements APIEscribano {

    private GenericRestAPI genericRestAPI;

    private AppProperties appProperties;

    private JwtTokenUtil jwtTokenUtil;

    public APIEscribanoImpl(GenericRestAPI genericRestAPI, AppProperties appProperties, JwtTokenUtil jwtTokenUtil) {
        this.genericRestAPI = genericRestAPI;
        this.appProperties = appProperties;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public EscribanoResponse getEscribanos(String cuit) throws Exception {
        EscribanoResponse response = genericRestAPI
                .makeGetRequest(appProperties.getEndpointEscribanos() + "/" + cuit
                        , EscribanoResponse.class, this.getHeader());

        return response;
    }

    private HttpHeaders getHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtTokenUtil.doGenerateToken());
        return headers;
    }
}
