package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	@Query(value = "select u from Usuario u where u.login = ?1")
	Usuario buscarPorLogin(String login);
	
	@Query(value = "select u from Usuario u where u.persona.id = ?1 or u.login = ?2") 
	Usuario buscarPorIdEmail(Long id, String email);
	
	@Query(value = "select constraint_name from information_schema.constraint_column_usage where table_name = 'usuarios_acesso' and column_name = 'acceso_id' and constraint_name <> 'unique_acceso_user';", nativeQuery = true)
	String consultaConstraintAcceso();
	
	@Query(value = "select u from Usuario u where u.fechaActualPassword <= current_date - 90")
	List<Usuario> usuariosPasswordVencido();

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "insert into usuarios_acceso(usuario_id, acceso_id) values (?1, (select id from acceso where descripcion_acceso = 'ROLE_USER'))")
	void insertarAccesoUser(Long id);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "insert into usuarios_acceso(usuario_id, acceso_id) values (?1, (select id from acceso where descripcion_acceso = 'ROLE_ADMIN'))")
	void insertarAccesoUserPf(Long id);

}
