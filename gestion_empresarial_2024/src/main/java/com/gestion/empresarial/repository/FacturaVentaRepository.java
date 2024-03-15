package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.FacturaVenta;

@Transactional
public interface FacturaVentaRepository extends JpaRepository<FacturaVenta, Long> {

	@Query("select tbl from FacturaVenta tbl where upper(trim(tbl.nroFacturaCompra)) like %?1%")
	List<FacturaVenta> buscarPorNroFactura(String desc);
	
}
