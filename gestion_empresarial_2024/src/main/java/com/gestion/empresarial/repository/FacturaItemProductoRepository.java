package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.FacturaItemProducto;

@Transactional
public interface FacturaItemProductoRepository extends JpaRepository<FacturaItemProducto, Long>{
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from factura_item_producto where factura_compra_id = ?1 and producto_id = ?2 and empresa_id = ?3")
	public boolean existeUnRegistro(Long facturaId, Long productoId, Long empresaId);
	
	@Query("select fip from FacturaItemProducto fip where fip.facturaCompra = ?1")
	public List<FacturaItemProducto> buscarPorNroFactura(Long factura);
	
	@Query("select fip from FacturaItemProducto fip where fip.id = ?1")
	public FacturaItemProducto buscarPorID(Long id);
	
	@Query("select fip from FacturaItemProducto fip where fip.producto.id = ?1")
	public List<FacturaItemProducto> buscarPorProducto(Long productoId);
	
}
