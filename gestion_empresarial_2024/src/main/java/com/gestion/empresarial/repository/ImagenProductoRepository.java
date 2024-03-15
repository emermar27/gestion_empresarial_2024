package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.ImagenProducto;

@Transactional
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long>{
	
//	@Query(nativeQuery = true, value = "select count(1) > 0 from imagen_producto where upper(trim(descripcion_marca)) = upper(trim(?1)) and empresa_id = ?2")
//	public boolean existeMarcaPorNombre(String dato, Long empresaId);
	
	@Query("select tbl from ImagenProducto tbl where tbl.producto.id = ?1 and tbl.empresa.id = ?2")
	public List<ImagenProducto> buscarPorProductoId(Long productoId, Long empresaId);
	
	@Transactional
	@Modifying(flushAutomatically = true)
	@Query(nativeQuery = true, value = "delete from imagen_producto where producto_id = ?1 and tbl.empresa.id = ?2")
	public boolean eliminaTodasLasImagenesDeUnProducto(Long productoId, Long empresaId);

}
