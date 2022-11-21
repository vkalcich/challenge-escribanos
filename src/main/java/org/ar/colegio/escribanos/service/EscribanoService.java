package org.ar.colegio.escribanos.service;


import org.ar.colegio.escribanos.EscribanoResponse;

public interface EscribanoService {

    EscribanoResponse getEscribano(String cuit) throws Exception;
}
