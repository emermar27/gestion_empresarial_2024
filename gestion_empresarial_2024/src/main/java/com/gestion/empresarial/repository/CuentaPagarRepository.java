package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.CuentaPagar;

@Transactional
public interface CuentaPagarRepository extends JpaRepository<CuentaPagar, Long>{
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from cuenta_pagar where upper(trim(des_cuenta_pagar)) = upper(trim(?1)) and empresa_id = ?2")
	public boolean existePorNombre(String dato, Long empresaId);
	
	@Query("select cp from CuentaPagar cp where upper(trim(cp.desCuentaPagar)) like %?1%")
	List<CuentaPagar> buscarPorNombre(String dato);
	
	@Query("select cp from CuentaPagar cp where cp.persona.id = ?1")
	List<CuentaPagar> buscarPorPersona(Long personaId);
	
	@Query("select cp from CuentaPagar cp where cp.proveedor.id = ?1")
	List<CuentaPagar> buscarPorProveedor(Long personaId);

}
