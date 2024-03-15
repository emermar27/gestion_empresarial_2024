package com.gestion.empresarial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.Direccion;

@Transactional
public interface DireccionRepository extends JpaRepository<Direccion, Long>{
	
//	@Query(nativeQuery = true, value = "select count(1) > 0 from categoria_producto where upper(trim(descripcion_categoria)) = upper(trim(?1))")
//	public boolean buscarCategoriaPorNombre(String dato);
	
//	@Query("select cp from CategoriaProducto cp where upper(trim(cp.descripcionCategoria)) like %?1%")
//	List<CategoriaProducto> buscarCategoriaProductoPorDescripcion(String desc);

}
