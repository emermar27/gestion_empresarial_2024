package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.FormaPago;

@Transactional
public interface FormaPagoRepository extends JpaRepository<FormaPago, Long>{
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from forma_pago where upper(trim(descripcion_forma_pago)) = upper(trim(?1)) and empresa_id = ?2")
	public boolean existeUnaFormaDePago(String dato, Long empresaId);
	
	@Query("select tbl from FormaPago tbl where upper(trim(tbl.descripcionFormaPago)) like %?1%")
	List<FormaPago> buscarNombre(String dato);

}
