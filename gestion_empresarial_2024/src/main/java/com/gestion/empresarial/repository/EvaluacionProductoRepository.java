package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.EvaluacionProducto;

@Transactional
public interface EvaluacionProductoRepository extends JpaRepository<EvaluacionProducto, Long>{
	
	@Query("select tbl from EvaluacionProducto tbl where tbl.producto.id = ?1 and tbl.empresa.id = ?2")
	public List<EvaluacionProducto> buscarPorProducto(Long productoId, Long empresaId);
	
	@Query("select tbl from EvaluacionProducto tbl where tbl.producto.id = ?1 and tbl.persona.id = ?2 and tbl.empresa.id = ?3")
	public List<EvaluacionProducto> buscarPorProductoPersona(Long productoId, Long personaId, Long empresaId);
	
}
