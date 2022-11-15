package org.springframework.samples.petclinic.usuario;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;




public interface UsuarioRepository extends CrudRepository<Usuario, String>{

}
