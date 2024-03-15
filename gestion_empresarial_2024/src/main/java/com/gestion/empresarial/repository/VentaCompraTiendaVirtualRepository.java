package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.FacturaCompra;
import com.gestion.empresarial.model.VentaCompraTiendaVirtual;

@Transactional
public interface VentaCompraTiendaVirtualRepository extends JpaRepository<VentaCompraTiendaVirtual, Long>{
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from factura_compra where upper(trim(descripcion_factura_compra)) = upper(trim(?1)) and empresa_id = ?2")
	public boolean existePorNombre(String dato, Long empresaId);
	
	@Query("select tbl from FacturaCompra tbl where tbl.nroFacturaCompra = ?1")
	public List<VentaCompraTiendaVirtual> buscarPorNroFactura(String factura);
	
	@Query("select tbl from FacturaCompra tbl where upper(trim(tbl.descripcionFacturaCompra)) like %?1%")
	public List<VentaCompraTiendaVirtual> buscarPorNombre(String dato);
	
	@Query("select tbl from FacturaCompra tbl where tbl.persona.id = ?1")
	public List<VentaCompraTiendaVirtual> buscarPorPersona(Long personaId);
	
	@Query("select tbl from FacturaCompra tbl where tbl.cuentaPagar.id = ?1")
	public List<FacturaCompra> buscarPorProveedor(Long id);

}
