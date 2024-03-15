package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.MarcaProducto;

@Transactional
public interface MarcaProductoRepository extends JpaRepository<MarcaProducto, Long>{
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from marca_producto where upper(trim(descripcion_marca)) = upper(trim(?1)) and empresa_id = ?2")
	public boolean existeMarcaPorNombre(String dato, Long empresaId);
	
	@Query("select mp from MarcaProducto mp where upper(trim(mp.descripcionMarca)) like %?1%")
	List<MarcaProducto> buscarMarcaPorNombre(String dato);

}
