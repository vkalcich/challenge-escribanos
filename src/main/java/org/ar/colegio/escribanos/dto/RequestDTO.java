package org.ar.colegio.escribanos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    @NotNull(message = "CUIT es un campo Obligatorio")
    @Size(min = 8, max = 15, message = "El CUIT debe tener mínimo 8 caracteres")
    private String cuit;
}
