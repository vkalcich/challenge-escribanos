package org.ar.colegio.escribanos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EscribanoResponse {

    private String apellido;
    private String cargo;
    private String celular;
    private String dias;
    private String domicilio;
    private String email;
    private String estado;
    private String fechaDestitucion;
    private String horario;
    private String imagen;
    private Integer matricula;
    private Boolean mostrarImagen;
    private String nombre;
    private Integer registro;
    private String sexo;
    private String telefono;
}
