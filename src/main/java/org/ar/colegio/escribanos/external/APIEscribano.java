package org.ar.colegio.escribanos.external;


import org.ar.colegio.escribanos.EscribanoResponse;

public interface APIEscribano {

    EscribanoResponse getEscribanos(String cuit) throws Exception;
}
