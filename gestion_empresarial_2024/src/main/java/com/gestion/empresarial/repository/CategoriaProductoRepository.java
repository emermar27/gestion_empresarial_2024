package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.CategoriaProducto;

@Transactional
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long>{
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from categoria_producto where upper(trim(descripcion_categoria)) = upper(trim(?1))")
	public boolean buscarCategoriaPorNombre(String dato);
	
	@Query("select cp from CategoriaProducto cp where upper(trim(cp.descripcionCategoria)) like %?1%")
	List<CategoriaProducto> buscarCategoriaProductoPorDescripcion(String desc);

}
