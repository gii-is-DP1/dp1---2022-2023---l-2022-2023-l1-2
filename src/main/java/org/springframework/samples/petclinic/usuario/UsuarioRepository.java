package org.springframework.samples.petclinic.usuario;


import org.springframework.data.repository.CrudRepository;



public interface UsuarioRepository extends CrudRepository<Usuario, String>{

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
    List<Usuario> findAll();   
}
