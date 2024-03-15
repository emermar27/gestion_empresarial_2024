package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.Acceso;

@Transactional
public interface AccesoRepository extends JpaRepository<Acceso, Long> {

	@Query("select a from Acceso a where upper(trim(a.descripcionAcceso)) like %?1%")
	List<Acceso> buscarAccesoPorDescripcion(String desc);
	
}
