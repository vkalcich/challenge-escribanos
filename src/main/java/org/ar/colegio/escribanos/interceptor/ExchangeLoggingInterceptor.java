package org.ar.colegio.escribanos.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class ExchangeLoggingInterceptor implements ClientHttpRequestInterceptor {
 
    private final Logger log = LoggerFactory.getLogger(this.getClass());
 
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        Long before=System.currentTimeMillis();
        ClientHttpResponse response = execution.execute(request, body);
        Long after=System.currentTimeMillis();
		long timeElapsed = after - before;
		logElapsedTime(request,timeElapsed);
        logResponse(response);
        return response;
    }
 
    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        if (log.isDebugEnabled()) {
            log.debug("===========================request begin================================================");
            log.debug("URI         : {}", request.getURI());
            log.debug("Method      : {}", request.getMethod());
            log.debug("Headers     : {}", request.getHeaders());
            log.debug("Request body: {}", new String(body, "UTF-8"));
            log.debug("==========================request end================================================");
        }
    }
    private void logElapsedTime(HttpRequest request,Long time) throws IOException {
        if (log.isDebugEnabled()) {
            log.debug("===========================Elapsed Time================================================");
            log.debug("URI         : {}", request.getURI());
            log.debug("duration      : {}", time);
    
            log.debug("==========================Elapsed Time================================================");
        }
    }
    private void logResponse(ClientHttpResponse response) throws IOException {
        if (log.isDebugEnabled()) {
            log.debug("============================response begin==========================================");
            log.debug("Status code  : {}", response.getStatusCode());
            log.debug("Status text  : {}", response.getStatusText());
            log.debug("Headers      : {}", response.getHeaders());
            log.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
            log.debug("=======================response end=================================================");
        }
    }
}
