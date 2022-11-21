package org.ar.colegio.escribanos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@Getter
@Setter
public class AppProperties {

    private String issuer;
    private String subject;
    private String secret;
    private String audience;
    private String role;
    private String endpointEscribanos;

}
