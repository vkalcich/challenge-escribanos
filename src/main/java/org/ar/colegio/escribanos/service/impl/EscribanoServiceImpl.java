package org.ar.colegio.escribanos.service.impl;

import org.ar.colegio.escribanos.EscribanoResponse;
import org.ar.colegio.escribanos.external.APIEscribano;
import org.ar.colegio.escribanos.service.EscribanoService;
import org.springframework.stereotype.Service;

@Service
public class EscribanoServiceImpl implements EscribanoService {

    private APIEscribano ApiEscribano;

    public EscribanoServiceImpl(APIEscribano ApiEscribano) {
        this.ApiEscribano = ApiEscribano;
    }

    @Override
    public EscribanoResponse getEscribano(String cuit) throws Exception {
        EscribanoResponse response = ApiEscribano.getEscribanos(cuit);
        return response;
    }
}
