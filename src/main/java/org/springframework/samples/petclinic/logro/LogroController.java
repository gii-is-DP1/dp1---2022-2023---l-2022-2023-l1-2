package org.springframework.samples.petclinic.logro;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logros")
public class LogroController {
    private final LogroService logroService;
    private final UsuarioService usuarioService;
    private static final String LOGRO_CREATE_UPDATE = "logros/createLogros";
    private static final String LOGRO_DELETE = "logros/deleteLogros";

    @Autowired
    public LogroController(LogroService logroService, UsuarioService usuarioService) {
        this.logroService = logroService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/create")
    public String initCrearLogro(Map<String, Object> model, Principal principal) {
        Logro logro = new Logro();
        Usuario usuario = usuarioService.findUserByNombreUsuario(principal.getName()).get();
        if (usuario.getAdministrador().contains("admin")) {
            model.put("logro", logro);
            return LOGRO_CREATE_UPDATE;
        } else {
            return "redirect:/logros";
        }
    }

    @PostMapping("/create")
    public String processCrearLogro(Principal principal, @Valid Logro logro, BindingResult result) {
        if (result.hasErrors() || logroService.findAllLogros().contains(logro)) {
            return LOGRO_CREATE_UPDATE;
        } else {
            this.logroService.save(logro);
            return "redirect:/logros/" + logro.getId();
        }
    }

    @GetMapping("/edit/{logroId}")
    public String initEditarLogro(@PathVariable("logroId") int logroId, Map<String, Object> model,
            Principal principal) {
        Logro logro = logroService.findLogroById(logroId).get();
        Usuario usuario = usuarioService.findUserByNombreUsuario(principal.getName()).get();
        if (usuario.getAdministrador().contains("admin")) {
            model.put("logro", logro);
            return LOGRO_CREATE_UPDATE;
        } else {
            return "redirect:/logros";
        }
    }

    @PostMapping("/edit/{logroId}")
    public String processEditarLogro(@PathVariable("logroId") int logroId, Principal principal, @Valid Logro logro,
            BindingResult result) {
        if (result.hasErrors()) {
            return LOGRO_CREATE_UPDATE;
        } else {
            logro.setId(logroId);
            logro.setNombre(logro.getNombre());
            logro.setTipoLogro(logro.getTipoLogro());
            this.logroService.save(logro);
            return "redirect:/logros/{logroId}";
        }
    }

    @GetMapping(value = "/delete/{logroId}")
    public String deleteJugador(@PathVariable("logroId") int logroId){
        Optional<Logro> opt = logroService.findLogroById(logroId);
        if(opt.isPresent()){
            Logro logro = opt.get();
            logroService.delete(logro);
        }
        return "redirect:/logros";
    }
}
