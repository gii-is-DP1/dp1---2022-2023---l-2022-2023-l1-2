package org.springframework.samples.petclinic.usuario;

import java.util.List;
import java.util.Optional;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String>{
    List<Usuario> findAll();

    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1")
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}
