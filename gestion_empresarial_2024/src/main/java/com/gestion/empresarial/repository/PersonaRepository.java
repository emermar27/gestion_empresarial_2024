package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gestion.empresarial.model.PersonaJuridica;

public interface PersonaRepository extends CrudRepository<PersonaJuridica, Long>{

	@Query(value = "select pj from PersonaJuridica pj where pj.nit = ?1")
	public PersonaJuridica buscarPersonaJuridicaPorNit(String nit);
	
	@Query(value = "select pj from PersonaJuridica pj where pj.nit = ?1")
	public List<PersonaJuridica> buscarPersonasJuridicaPorNit(String nit);
	
	@Query("select pj from PersonaJuridica pj where upper(trim(pj.razonSocial)) like %?1%")
	List<PersonaJuridica> buscarPersonaJuridicaPorRazonSocial(String desc);
	
	@Query(value = "select pj from PersonaJuridica pj where pj.email = ?1")
	public PersonaJuridica buscarPersonaJuridicaPorEmail(String nit);
}
