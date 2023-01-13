package org.springframework.samples.petclinic.usuario;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AutoridadServiceTest {

    @Mock
    AutoridadRepository administradoRepository;

    @Mock
    UsuarioRepository usuarioRepository;

    AutoridadService autoridadService;

    Autoridad aut;

    Usuario user;

    Optional<Usuario> optUser;

    @BeforeEach
    public void config(){
        aut = new Autoridad();
        user = new Usuario();
        optUser = Optional.of(user);
        autoridadService = new AutoridadService(administradoRepository, usuarioRepository);

    }

    @Test
    public void saveAdministradores1Test(){
        autoridadService.saveAdministradores(aut);
        Mockito.verify(administradoRepository).save(aut);
    }


}
