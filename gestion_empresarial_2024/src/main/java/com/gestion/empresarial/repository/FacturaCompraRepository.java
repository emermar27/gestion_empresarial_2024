package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.FacturaCompra;

@Transactional
public interface FacturaCompraRepository extends JpaRepository<FacturaCompra, Long>{
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from factura_compra where upper(trim(descripcion_factura_compra)) = upper(trim(?1)) and empresa_id = ?2")
	public boolean existePorNombre(String dato, Long empresaId);
	
	@Query("select fc from FacturaCompra fc where fc.nroFacturaCompra = ?1")
	public List<FacturaCompra> buscarPorNroFactura(String factura);
	
	@Query("select fc from FacturaCompra fc where upper(trim(fc.descripcionFacturaCompra)) like %?1%")
	public List<FacturaCompra> buscarPorNombre(String dato);
	
	@Query("select fc from FacturaCompra fc where fc.persona.id = ?1")
	public List<FacturaCompra> buscarPorPersona(Long personaId);
	
	@Query("select fc from FacturaCompra fc where fc.cuentaPagar.id = ?1")
	public List<FacturaCompra> buscarPorProveedor(Long id);

}
