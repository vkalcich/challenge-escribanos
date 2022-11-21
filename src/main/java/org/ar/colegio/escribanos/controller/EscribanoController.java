package org.ar.colegio.escribanos.controller;


import lombok.Getter;
import lombok.Setter;
import org.ar.colegio.escribanos.EscribanoResponse;
import org.ar.colegio.escribanos.dto.RequestDTO;
import org.ar.colegio.escribanos.service.EscribanoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST controller relacionado a manejar operaciones relacionadas a escribanos
 */
@Controller
@RequestMapping("api/escribanos")
@Getter
@Setter
public class EscribanoController {

    private final Logger log = LoggerFactory.getLogger(EscribanoController.class);

    private EscribanoService escribanoService;

    public EscribanoController(EscribanoService escribanoService) {
        this.escribanoService = escribanoService;
    }

    /**
     * GET  / : Obtener todos los escribanos
     *
     * @param model contiene el modelo de thymeleaf.
     * @param request contiene el cuit del escribano a buscar.
     * @return String con el nombre de la pagina a mostrarse con el resultado.
     */
    @RequestMapping
    public String getEscribanos(Model model, RequestDTO request) throws Exception {
        log.debug("Recuperamos todos los escribanos");

        EscribanoResponse escribanoResponse = escribanoService.getEscribano(request.getCuit());
        model.addAttribute(escribanoResponse);
        return "principal";
    }

    /**
     * Url por donde se ingresa a la aplicacion. Se visualizara una pagina para buscar un escribano por cuit.
     * @param model
     * @param request
     * @return string con el nombre de la pagina a visualizarse.
     */
    @RequestMapping("/index")
    public String index(Model model, RequestDTO request) {
        return "principal";
    }
}
