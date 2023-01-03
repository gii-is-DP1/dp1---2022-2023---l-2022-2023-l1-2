package org.springframework.samples.petclinic.logro;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.usuario.Autoridad;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logros")
public class LogroController {
    private final LogroService logroService;
    private final UsuarioService usuarioService;
    private static final String LOGRO_CREATE_UPDATE = "logros/createLogros";

    @Autowired
    public LogroController(LogroService logroService, UsuarioService usuarioService, AutoridadService autoridadService) {
        this.logroService = logroService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/create")
    public String initCrearLogro(Map<String, Object> model) {
        Logro logro = new Logro();
        model.put("logro", logro);
        model.put("tipoLogro", Arrays.asList(TipoLogro.values()));
        return LOGRO_CREATE_UPDATE;
    }

    @PostMapping("/create")
    public String processCrearLogro(Principal principal, @Valid Logro logro, BindingResult result) {
        if (result.hasErrors() || logroService.findAllLogros().contains(logro)) {
            return LOGRO_CREATE_UPDATE;
        } else {
            Set<Jugador> set = new HashSet<>();
            logro.setJugadores(set);
            this.logroService.save(logro);
            return "redirect:/logros/list";
        }
    }

    @GetMapping("/edit/{logroId}")
    public String initEditarLogro(@PathVariable("logroId") int logroId, Map<String, Object> model) {
        Logro logro = logroService.findLogroById(logroId).get();
        model.put("logro", logro);
        model.put("tipoLogro",Arrays.asList(TipoLogro.values()));
        return LOGRO_CREATE_UPDATE;
    }

    @PostMapping("/edit/{logroId}")
    public String processEditarLogro(@PathVariable("logroId") int logroId, Principal principal, @Valid Logro logro,
            BindingResult result) {
        if (result.hasErrors()) {
            return LOGRO_CREATE_UPDATE;
        } else {
            logro.setId(logroId);
            logro.setNombreLogro(logro.getNombreLogro());
            logro.setDescripcion(logro.getDescripcion());
            logro.setObjetivo(logro.getObjetivo());
            logro.setTipoLogro(logro.getTipoLogro());
            logro.setJugadores(logro.getJugadores());
            this.logroService.save(logro);
            return "redirect:/logros/list";
        }
    }

    @GetMapping(value = "/delete/{logroId}")
    public String deleteLogro(@PathVariable("logroId") int logroId){
        Optional<Logro> opt = logroService.findLogroById(logroId);
        if(opt.isPresent()){
            Logro logro = opt.get();
            logroService.delete(logro);
        }
        return "redirect:/logros/list";
    }

    @GetMapping("/list")
    public ModelAndView listLogros(Principal principal){
		ModelAndView mav = new ModelAndView("logros/listLogros");
        Usuario user = usuarioService.findUserByNombreUsuario(principal.getName()).get();
		List<Logro> logros = logroService.findAllLogros();
        Optional<Autoridad> autoridad = user.getAdministrador().stream().filter(x -> x.getAutoridad().equals("admin")).findAny();
		mav.addObject("logros", logros);
        mav.addObject("autoridad", autoridad);
		return mav;
	}

}
